package com.ling.blog;

import com.alibaba.fastjson.JSON;
import com.ling.blog.constant.MQPrefixConst;
import com.ling.blog.constant.RedisPrefixConst;
import com.ling.blog.dto.EmailDTO;
import com.ling.blog.exception.BizException;
import com.ling.blog.mapper.UserAuthMapper;
import com.ling.blog.mapper.UserInfoMapper;
import com.ling.blog.mapper.UserRoleMapper;
import com.ling.blog.service.BlogInfoService;
import com.ling.blog.service.RedisService;
import com.ling.blog.strategy.context.SocialLoginStrategyContext;
import com.ling.blog.utils.CommonUtils;
import com.ling.blog.utils.SendMailUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 风间离
 * @Create 2023/8/2  23:25
 */

@Slf4j
@SpringBootTest
public class Send {

    @Resource
    MailSender mailSender;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private SocialLoginStrategyContext socialLoginStrategyContext;

    @Autowired
    private SendMailUtils mailUtils;

    @Test
    void contextLoads(){
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
       // System.out.println(encoder.encode("november"));
        //String username = "snow@zurish.cn";
        String username = "173528006@qq.com";
        sendCode(username);
    }

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

        mailUtils.sendValidateEmail(username,code);
        rabbitTemplate.convertAndSend(MQPrefixConst.EMAIL_EXCHANGE, "*", new Message(JSON.toJSONBytes(emailDTO), new MessageProperties()));
        // 将验证码存入redis，设置过期时间为15分钟
        redisService.set(RedisPrefixConst.USER_CODE_KEY + username, code, RedisPrefixConst.CODE_EXPIRE_TIME);
    }


}
