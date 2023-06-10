package com.ling.blog.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 密码
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "密码")
public class PasswordVO {

    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码不能为空")
    @Schema(name = "oldPassword", description = "旧密码", required = true, type = "String")
    private String oldPassword;

    /**
     * 新密码
     */
    @Size(min = 6, message = "新密码不能少于6位")
    @NotBlank(message = "新密码不能为空")
    @Schema(name = "newPassword", description = "新密码", required = true, type = "String")
    private String newPassword;
}
