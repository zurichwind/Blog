package com.ling.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * 微信配置属性类
 *
 * @Author : 风间离
 * @create 2023/5/27 17:03
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wx")
public class WxConfigProperties {

    /**
     * wx appId
     */
    private String appId;

    /**
     * 校验token地址
     */
    private String checkTokenUrl;

    /**
     * QQ用户信息地址
     */
    private String userInfoUrl;

}
