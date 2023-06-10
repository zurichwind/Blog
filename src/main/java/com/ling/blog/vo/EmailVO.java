package com.ling.blog.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 邮件
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Tag(name = "绑定邮箱")
public class EmailVO {

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @Schema(name = "email", description = "用户名", required = true, type = "String")
    private String email;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    @Schema(name = "code", description = "邮箱验证码", required = true, type = "String")
    private String code;

}
