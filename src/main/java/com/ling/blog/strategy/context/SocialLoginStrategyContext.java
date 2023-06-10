package com.ling.blog.strategy.context;

import com.ling.blog.dto.UserInfoDTO;
import com.ling.blog.enums.LoginTypeEnum;
import com.ling.blog.strategy.SocialLoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * 第三方登录策略上下文
 *
 * @Author : 风间离
 * @create 2023/5/28 12:18
 */
@Service
public class SocialLoginStrategyContext {

    @Autowired
    private Map<String, SocialLoginStrategy> socialLoginStrategyMap;

    /**
     * 执行第三方登录策略
     *
     * @param data          数据
     * @param loginTypeEnum 登录枚举类型
     * @return {@link UserInfoDTO} 用户信息
     */
    public UserInfoDTO executeLoginStrategy(String data, LoginTypeEnum loginTypeEnum) {
        return socialLoginStrategyMap.get(loginTypeEnum.getStrategy()).login(data);
    }

}
