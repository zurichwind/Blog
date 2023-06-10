package com.ling.blog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 博客信息
 *
 * @author yezhiqiu
 * @date 2021/07/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Tag(name = "博客信息")
public class BlogInfoVO {

    /**
     * 关于我内容
     */
    @Schema(name = "aboutContent", description = "关于我内容", required = true, type = "String")
    private String aboutContent;

}
