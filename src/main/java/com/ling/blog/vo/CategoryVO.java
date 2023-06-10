package com.ling.blog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 分类VO
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "分类")
public class CategoryVO {

    /**
     * id
     */
    @Schema(name = "id", description = "分类id", type = "Integer")
    private Integer id;

    /**
     * 分类名
     */
    @NotBlank(message = "分类名不能为空")
    @Schema(name = "categoryName", description = "分类名", required = true, type = "String")
    private String categoryName;

}
