package com.ling.blog.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * 用户信息vo
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "用户信息对象")
public class UserInfoVO {

    /**
     * 用户昵称
     */
    @NotBlank(message = "昵称不能为空")
    @Schema(name = "nickname", description = "昵称", type = "String")
    private String nickname;

    /**
     * 用户简介
     */
    @Schema(name = "intro", description = "介绍", type = "String")
    private String intro;

    /**
     * 个人网站
     */
    @Schema(name = "webSite", description = "个人网站", type = "String")
    private String webSite;
}
