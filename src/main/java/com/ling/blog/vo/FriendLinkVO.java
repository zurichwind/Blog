package com.ling.blog.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 友链VO
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "友链")
public class FriendLinkVO {
    /**
     * id
     */
    @Schema(name = "categoryId", description = "友链id", type = "Integer")
    private Integer id;

    /**
     * 链接名
     */
    @NotBlank(message = "链接名不能为空")
    @Schema(name = "linkName", description = "友链名", type = "String", required = true)
    private String linkName;

    /**
     * 链接头像
     */
    @NotBlank(message = "链接头像不能为空")
    @Schema(name = "linkAvatar", description = "友链头像", type = "String", required = true)
    private String linkAvatar;

    /**
     * 链接地址
     */
    @NotBlank(message = "链接地址不能为空")
    @Schema(name = "linkAddress", description = "友链头像", type = "String", required = true)
    private String linkAddress;

    /**
     * 介绍
     */
    @NotBlank(message = "链接介绍不能为空")
    @Schema(name = "linkIntro", description = "友链头像", type = "String", required = true)
    private String linkIntro;

    /**
     * 状态
     */
    @Schema(name = "status", description = "友链状态", type = "Integer")
    private Integer status;

}
