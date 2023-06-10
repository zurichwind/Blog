package com.ling.blog.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Tag(name = "微信登录信息")
public class WxLoginVO {
    /**
     * code
     */
    @NotBlank(message = "code不能为空")
    @Schema(name = "code", description = "code", required = true, type = "String")
    private String code;
}
