package com.ling.blog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "文章")
public class ArticleVO {

    /**
     * 文章id
     */
    @Schema(name = "id", description = "文章id", type = "Integer")
    private Integer id;

    /**
     * 标题
     */
    @NotBlank(message = "文章标题不能为空")
    @Schema(name = "articleTitle", description = "文章标题", required = true, type = "String")
    private String articleTitle;

    /**
     * 内容
     */
    @NotBlank(message = "文章内容不能为空")
    @Schema(name = "articleContent", description = "文章内容", required = true, type = "String")
    private String articleContent;

    /**
     * 文章封面
     */
    @Schema(name = "articleCover", description = "文章缩略图", type = "String")
    private String articleCover;

    /**
     * 文章分类
     */
    @Schema(name = "category", description = "文章分类", type = "Integer")
    private String categoryName;

    /**
     * 文章标签
     */
    @Schema(name = "tagNameList", description = "文章标签", type = "List<Integer>")
    private List<String> tagNameList;

    /**
     * 文章类型
     */
    @Schema(name = "type", description = "文章类型", type = "Integer")
    private Integer type;

    /**
     * 原文链接
     */
    @Schema(name = "originalUrl", description = "原文链接", type = "String")
    private String originalUrl;

    /**
     * 是否置顶
     */
    @Schema(name = "isTop", description = "是否置顶", type = "Integer")
    private Integer isTop;

    /**
     * 文章状态 1.公开 2.私密 3.评论可见
     */
    @Schema(name = "status", description = "文章状态", type = "String")
    private Integer status;

}
