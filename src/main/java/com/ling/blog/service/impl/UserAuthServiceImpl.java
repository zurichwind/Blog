package com.ling.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ling.blog.constant.CommonConst;
import com.ling.blog.constant.MQPrefixConst;
import com.ling.blog.constant.RedisPrefixConst;
import com.ling.blog.dto.EmailDTO;
import com.ling.blog.dto.UserAreaDTO;
import com.ling.blog.dto.UserBackDTO;
import com.ling.blog.dto.UserInfoDTO;
import com.ling.blog.entity.UserAuth;
import com.ling.blog.entity.UserInfo;
import com.ling.blog.entity.UserRole;
import com.ling.blog.enums.LoginTypeEnum;
import com.ling.blog.enums.RoleEnum;
import com.ling.blog.enums.UserAreaTypeEnum;
import com.ling.blog.exception.BizException;
import com.ling.blog.mapper.UserAuthMapper;
import com.ling.blog.mapper.UserInfoMapper;
import com.ling.blog.mapper.UserRoleMapper;
import com.ling.blog.service.BlogInfoService;
import com.ling.blog.service.RedisService;
import com.ling.blog.service.UserAuthService;
import com.ling.blog.strategy.context.SocialLoginStrategyContext;
import com.ling.blog.utils.CommonUtils;
import com.ling.blog.utils.PageUtils;
import com.ling.blog.utils.UserUtils;
import com.ling.blog.vo.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 风间离
 * @create 2023/5/28 18:50
 */
@Slf4j
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements UserAuthService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserAuthMapper userAuthMapper;
    @Autowired
    private UserRoleMapper userroleMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BlogInfoService blogInfoService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private SocialLoginStrategyContext socialLoginStrategyContext;


    @Override
    public void sendCode(String username) {
        // 校验账号是否合法
        if (!CommonUtils.checkEmail(username)) {
            throw new BizException("请输入正确邮箱");
        }
        // 生成六位随机验证码发送
        String code = CommonUtils.getRandomCode();
        // 发送验证码
        EmailDTO emailDTO = EmailDTO.builder()
                .email(username)
                .subject("验证码")
                .content("您的验证码为 " + code + " 有效期15分钟，请不要告诉他人哦！")
                .build();
        log.info("验证码是" + code);
        rabbitTemplate.convertAndSend(MQPrefixConst.EMAIL_EXCHANGE, "*", new Message(JSON.toJSONBytes(emailDTO), new MessageProperties()));
        // 将验证码存入redis，设置过期时间为15分钟
        redisService.set(RedisPrefixConst.USER_CODE_KEY + username, code, RedisPrefixConst.CODE_EXPIRE_TIME);
    }

    @Override
    public List<UserAreaDTO> listUserAreas(ConditionVO conditionVO) {
        List<UserAreaDTO> userAreaDTOList = new ArrayList<>();
        switch (Objects.requireNonNull(UserAreaTypeEnum.getUserAreaType(conditionVO.getType()))) {
            case USER:
                // 查询注册用户区域分布
                Object userArea = redisService.get(RedisPrefixConst.USER_AREA);
                if (Objects.nonNull(userArea)) {
                    userAreaDTOList = JSON.parseObject(userArea.toString(), List.class);
                }
                return userAreaDTOList;
            case VISITOR:
                // 查询游客区域分布
                Map<String, Object> visitorArea = redisService.hGetAll(RedisPrefixConst.VISITOR_AREA);
                if (Objects.nonNull(visitorArea)) {
                    userAreaDTOList = visitorArea.entrySet().stream()
                            .map(item -> UserAreaDTO.builder()
                                    .name(item.getKey())
                                    .value(Long.valueOf(item.getValue().toString()))
                                    .build())
                            .collect(Collectors.toList());
                }
                return userAreaDTOList;
            default:
                break;
        }
        return userAreaDTOList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserVO user) {
        // 校验账号是否合法
        if (checkUser(user)) {
            throw new BizException("邮箱已被注册！");
        }
        // 新增用户信息
        UserInfo userInfo = UserInfo.builder()
                .email(user.getUsername())
                .nickname(CommonConst.DEFAULT_NICKNAME + IdWorker.getId())
                .avatar(blogInfoService.getWebsiteConfig().getUserAvatar())
                .build();
        userInfoMapper.insert(userInfo);
        // 绑定用户角色
        UserRole userRole = UserRole.builder()
                .userId(userInfo.getId())
                .roleId(RoleEnum.USER.getRoleId())
                .build();
        userroleMapper.insert(userRole);
        // 新增用户账号
        UserAuth userAuth = UserAuth.builder()
                .userInfoId(userInfo.getId())
                .username(user.getUsername())
                .password(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()))
                .loginType(LoginTypeEnum.EMAIL.getType())
                .build();
        userAuthMapper.insert(userAuth);
    }

    @Override
    public void updatePassword(UserVO user) {
        // 校验账号是否合法
        if (!checkUser(user)) {
            throw new BizException("邮箱尚未注册！");
        }
        // 根据用户名修改密码
        userAuthMapper.update(new UserAuth(), new LambdaUpdateWrapper<UserAuth>()
                .set(UserAuth::getPassword, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()))
                .eq(UserAuth::getUsername, user.getUsername()));
    }

    @Override
    public void updateAdminPassword(PasswordVO passwordVO) {
        // 查询旧密码是否正确
        UserAuth user = userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .eq(UserAuth::getId, UserUtils.getLoginUser().getId()));
        // 正确则修改密码，错误则提示不正确
        if (Objects.nonNull(user) && BCrypt.checkpw(passwordVO.getOldPassword(), user.getPassword())) {
            UserAuth userAuth = UserAuth.builder()
                    .id(UserUtils.getLoginUser().getId())
                    .password(BCrypt.hashpw(passwordVO.getNewPassword(), BCrypt.gensalt()))
                    .build();
            userAuthMapper.updateById(userAuth);
        } else {
            throw new BizException("旧密码不正确");
        }
    }

    @Override
    public PageResult<UserBackDTO> listUserBackDTO(ConditionVO condition) {
        // 获取后台用户数量
        Integer count = userAuthMapper.countUser(condition);
        if (count == 0) {
            return new PageResult<>();
        }
        // 获取后台用户列表
        List<UserBackDTO> userBackDTOList = userAuthMapper.listUsers(PageUtils.getLimitCurrent(), PageUtils.getSize(), condition);
        return new PageResult<>(userBackDTOList, count);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserInfoDTO qqLogin(QQLoginVO qqLoginVO) {
        return socialLoginStrategyContext.executeLoginStrategy(JSON.toJSONString(qqLoginVO), LoginTypeEnum.QQ);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserInfoDTO wxLogin(WxLoginVO wxLoginVO) {
        return socialLoginStrategyContext.executeLoginStrategy(JSON.toJSONString(wxLoginVO), LoginTypeEnum.WECHAT);
    }

    /**
     * 校验用户数据是否合法
     *
     * @param user 用户数据
     * @return 结果
     */
    private Boolean checkUser(UserVO user) {
        if (!user.getCode().equals(redisService.get(RedisPrefixConst.USER_CODE_KEY + user.getUsername()))) {
            throw new BizException("验证码错误！");
        }
        //查询用户名是否存在
        UserAuth userAuth = userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .select(UserAuth::getUsername)
                .eq(UserAuth::getUsername, user.getUsername()));
        return Objects.nonNull(userAuth);
    }

    /**
     * 统计用户地区
     */
    @Scheduled(cron = "0 0 * * * ?")
    public void statisticalUserArea() {
        // 统计用户地域分布
        Map<String, Long> userAreaMap = userAuthMapper.selectList(new LambdaQueryWrapper<UserAuth>().select(UserAuth::getIpSource))
                .stream()
                .map(item -> {
                    if (StringUtils.isNotBlank(item.getIpSource())) {
                        String address = item.getIpAddress();
                        if(address.contains("自治区")){
                            address.substring(0,address.indexOf("自治区")+3);
                        }else if(address.contains("省")){
                            address.substring(0,address.indexOf("省")+1);
                        }else{
                            address.substring(0,address.indexOf("市")+1);
                        }
                        return address;
                    }
                    return CommonConst.UNKNOWN;
                })
                .collect(Collectors.groupingBy(item -> item, Collectors.counting()));
        // 转换格式
        List<UserAreaDTO> userAreaList = userAreaMap.entrySet().stream()
                .map(item -> UserAreaDTO.builder()
                        .name(item.getKey())
                        .value(item.getValue())
                        .build())
                .collect(Collectors.toList());
        redisService.set(RedisPrefixConst.USER_AREA, JSON.toJSONString(userAreaList));
    }

}
