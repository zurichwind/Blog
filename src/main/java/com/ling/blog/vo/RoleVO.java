package com.ling.blog.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色
 *
 * @author yezhiqiu
 * @date 2021/08/03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "角色")
public class RoleVO {

    /**
     * id
     */
    @Schema(name = "id", description = "用户id", type = "Integer")
    private Integer id;

    /**
     * 标签名
     */
    @NotBlank(message = "角色名不能为空")
    @Schema(name = "roleName", description = "角色名", required = true, type = "String")
    private String roleName;

    /**
     * 标签名
     */
    @NotBlank(message = "权限标签不能为空")
    @Schema(name = "categoryName", description = "标签名", required = true, type = "String")
    private String roleLabel;

    /**
     * 资源列表
     */
    @Schema(name = "resourceIdList", description = "资源列表", required = true, type = "List<Integer>")
    private List<Integer> resourceIdList;

    /**
     * 菜单列表
     */
    @Schema(name = "menuIdList", description = "菜单列表", required = true, type = "List<Integer>")
    private List<Integer> menuIdList;

}
