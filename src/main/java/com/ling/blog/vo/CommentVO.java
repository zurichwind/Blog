package com.ling.blog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 评论
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "评论")
public class CommentVO {

    /**
     * 回复用户id
     */
    @Schema(name = "replyUserId", description = "回复用户id", type = "Integer")
    private Integer replyUserId;

    /**
     * 评论主题id
     */
    @Schema(name = "topicId", description = "主题id", type = "Integer")
    private Integer topicId;

    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空")
    @Schema(name = "commentContent", description = "评论内容", required = true, type = "String")
    private String commentContent;

    /**
     * 父评论id
     */
    @Schema(name = "parentId", description = "评论父id", type = "Integer")
    private Integer parentId;

    /**
     * 类型
     */
    @NotNull(message = "评论类型不能为空")
    @Schema(name = "type", description = "评论类型", type = "Integer")
    private Integer type;

    /**
     * 用户ip
     */
    private String ipAddress;

    /**
     * ip来源
     */
    private String ipSource;

}
