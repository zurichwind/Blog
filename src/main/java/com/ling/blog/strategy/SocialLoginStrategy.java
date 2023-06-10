package com.ling.blog.strategy;

import com.ling.blog.dto.UserInfoDTO;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * 第三方登录策略
 *
 * @Author : 风间离
 * @create 2023/5/28 12:12
 */
@Service
public interface SocialLoginStrategy {

    /**
     * 登录
     *
     * @param data 数据
     * @return {@link UserInfoDTO} 用户信息
     */
    UserInfoDTO login(String data);

}
