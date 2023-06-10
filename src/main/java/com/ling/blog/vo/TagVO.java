package com.ling.blog.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * 标签VO
 *
 * @author yezhiqiu
 * @date 2021/08/03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "标签对象")
public class TagVO {

    /**
     * id
     */
    @Schema(name = "id", description = "标签id", type = "Integer")
    private Integer id;

    /**
     * 标签名
     */
    @NotBlank(message = "标签名不能为空")
    @Schema(name = "categoryName", description = "标签名", required = true, type = "String")
    private String tagName;

}
