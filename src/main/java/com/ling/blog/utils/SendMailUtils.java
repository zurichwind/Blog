package com.ling.blog.utils;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 风间离
 * @Create 2023/8/2  23:17
 */
@Service
public class SendMailUtils {
    @Value("${spring.mail.username}")
    String from;
    @Resource
    MailSender mailSender;



    public String sendValidateEmail(String email,String code) {

        Random random = new Random();
        //int code = random.nextInt(899999) + 100000;
        //log.info("code: " + code);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("您的验证邮件");
        message.setText("验证码是: " + code);
        try {
            mailSender.send(message);
            //template.opsForValue().set(key, String.valueOf(code),3, TimeUnit.MINUTES);
            return null;
        } catch (MailException e){
            e.printStackTrace();
            return "邮件发送失败";
        }
    }
}
