package com.ling.blog.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 资源
 *
 * @author yezhiqiu
 * @date 2021/08/03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "资源")
public class ResourceVO {

    /**
     * 资源id
     */
    @Schema(name = "id", description = "资源id", required = true, type = "Integer")
    private Integer id;

    /**
     * 资源名
     */
    @NotBlank(message = "资源名不能为空")
    @Schema(name = "resourceName", description = "资源名", required = true, type = "String")
    private String resourceName;

    /**
     * 路径
     */
    @Schema(name = "url", description = "资源路径", required = true, type = "String")
    private String url;

    /**
     * 请求方式
     */
    @Schema(name = "url", description = "资源路径", required = true, type = "String")
    private String requestMethod;

    /**
     * 父资源id
     */
    @Schema(name = "parentId", description = "父资源id", required = true, type = "Integer")
    private Integer parentId;

    /**
     * 是否匿名访问
     */
    @Schema(name = "isAnonymous", description = "是否匿名访问", required = true, type = "Integer")
    private Integer isAnonymous;

}
