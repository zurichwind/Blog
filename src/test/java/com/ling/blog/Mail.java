package com.ling.blog;

import com.alibaba.fastjson.JSON;
import com.ling.blog.constant.MQPrefixConst;
import com.ling.blog.constant.RedisPrefixConst;
import com.ling.blog.dto.EmailDTO;
import com.ling.blog.exception.BizException;
import com.ling.blog.service.RedisService;
import com.ling.blog.utils.CommonUtils;
import com.ling.blog.utils.SendMailUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;

import static com.ling.blog.constant.MQPrefixConst.EMAIL_EXCHANGE;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 风间离
 * @Create 2023/8/2  21:07
 */
@Slf4j
@SpringBootTest
public class Mail {
    @Value("${spring.mail.username}")
    String from;

   @Autowired
    private SendMailUtils mailUtils;
    String email = "cloud@zurish.cn";

    String code = "2020";
    @Test
    void contextLoads(){
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //System.out.println(encoder.encode("november"));
        mailUtils.sendValidateEmail(email,code);
    }

}
