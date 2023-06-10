package com.ling.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 社交登录token
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialTokenDTO {

    /**
     * 开放id
     */
    private String openId;

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 登录类型
     */
    private Integer loginType;

}
