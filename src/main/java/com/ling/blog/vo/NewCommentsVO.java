package com.ling.blog.vo;



import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 未查看的新回复
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "未读回复")
public class NewCommentsVO {

    /**
     * 文章id
     */
    @Schema(name = "id", description = "文章id", type = "Integer")
    private Integer id;

    /**
     * 标题
     */
    @Schema(name = "articleTitle", description = "文章标题", required = true, type = "String")
    private String articleTitle;


    /**
     * 未读回复数量
     */
    @Schema(name = "newCommentsCount", description = "未读回复数量", type = "Integer")
    private Integer newCommentsCount;



}
