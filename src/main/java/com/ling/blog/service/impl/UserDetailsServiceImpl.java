package com.ling.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ling.blog.constant.RedisPrefixConst;
import com.ling.blog.dto.ArticleDTO;
import com.ling.blog.dto.UserDetailDTO;
import com.ling.blog.entity.UserAuth;
import com.ling.blog.entity.UserInfo;
import com.ling.blog.enums.ZoneEnum;
import com.ling.blog.exception.BizException;
import com.ling.blog.mapper.ArticleMapper;
import com.ling.blog.mapper.RoleMapper;
import com.ling.blog.mapper.UserAuthMapper;
import com.ling.blog.mapper.UserInfoMapper;
import com.ling.blog.service.RedisService;
import com.ling.blog.utils.IpUtils;
import com.ling.blog.vo.NewCommentsVO;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 风间离
 * @create 2023/5/28 18:51
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserAuthMapper userAuthDao;
    @Autowired
    private UserInfoMapper userInfoDao;
    @Autowired
    private RoleMapper roleDao;
    @Autowired
    private RedisService redisService;
    @Resource
    private HttpServletRequest request;
    @Autowired
    private ArticleMapper articleDao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            throw new BizException("用户名不能为空！");
        }
        // 查询账号是否存在
        UserAuth userAuth = userAuthDao.selectOne(new LambdaQueryWrapper<UserAuth>()
                .select(UserAuth::getId, UserAuth::getUserInfoId, UserAuth::getUsername, UserAuth::getPassword, UserAuth::getLoginType)
                .eq(UserAuth::getUsername, username));
        if (Objects.isNull(userAuth)) {
            throw new BizException("用户名不存在!");
        }
        // 封装登录信息
        return convertUserDetail(userAuth, request);
    }

    /**
     * 封装用户登录信息
     *
     * @param user    用户账号
     * @param request 请求
     * @return 用户登录信息
     */
    public UserDetailDTO convertUserDetail(UserAuth user, HttpServletRequest request) {
        // 查询账号信息
        UserInfo userInfo = userInfoDao.selectById(user.getUserInfoId());
        // 查询账号角色
        List<String> roleList = roleDao.listRolesByUserInfoId(userInfo.getId());
        // 查询账号点赞信息
        Set<Object> articleLikeSet = redisService.sMembers(RedisPrefixConst.ARTICLE_USER_LIKE + userInfo.getId());
        Set<Object> commentLikeSet = redisService.sMembers(RedisPrefixConst.COMMENT_USER_LIKE + userInfo.getId());
        Set<Object> talkLikeSet = redisService.sMembers(RedisPrefixConst.TALK_USER_LIKE + userInfo.getId());
        // 获取设备信息
        String ipAddress = IpUtils.getIpAddress(request);
        String ipSource = IpUtils.getIpSource(ipAddress);
        UserAgent userAgent = IpUtils.getUserAgent(request);
        //获取新评论合集
        Map<String, Object> map  = redisService.hGetAll(RedisPrefixConst.NEW_ARTICLE_COMMENT);
        List<NewCommentsVO> newCommentsList = new ArrayList<>();
        for (Map.Entry<String,Object> entry : map.entrySet()) {
            Integer articleId = Integer.parseInt(entry.getKey());
            Integer newCommentsCount = (Integer)entry.getValue();
            ArticleDTO article = articleDao.getArticleById(articleId);
            newCommentsList.add(NewCommentsVO.builder()
                    .id(articleId)
                    .articleTitle(article.getArticleTitle())
                    .newCommentsCount(newCommentsCount)
                    .build());
        }
        // 封装权限集合
        return UserDetailDTO.builder()
                .id(user.getId())
                .loginType(user.getLoginType())
                .userInfoId(userInfo.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(userInfo.getEmail())
                .roleList(roleList)
                .nickname(userInfo.getNickname())
                .avatar(userInfo.getAvatar())
                .intro(userInfo.getIntro())
                .webSite(userInfo.getWebSite())
                .articleLikeSet(articleLikeSet)
                .commentLikeSet(commentLikeSet)
                .newCommentsList(newCommentsList)
                .talkLikeSet(talkLikeSet)
                .ipAddress(ipAddress)
                .ipSource(ipSource)
                .isDisable(userInfo.getIsDisable())
                .browser(userAgent.getBrowser().getName())
                .os(userAgent.getOperatingSystem().getName())
                .lastLoginTime(LocalDateTime.now(ZoneId.of(ZoneEnum.SHANGHAI.getZone())))
                .build();
    }

}