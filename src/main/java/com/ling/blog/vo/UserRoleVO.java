package com.ling.blog.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户角色vo
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "用户权限")
public class UserRoleVO {
    /**
     * 用户id
     */
    @NotNull(message = "id不能为空")
    @Schema(name = "userInfoId", description = "用户信息id", type = "Integer")
    private Integer userInfoId;

    /**
     * 用户昵称
     */
    @NotBlank(message = "昵称不能为空")
    @Schema(name = "nickname", description = "昵称", type = "String")
    private String nickname;

    /**
     * 用户角色
     */
    @NotNull(message = "用户角色不能为空")
    @Schema(name = "roleList", description = "角色id集合", type = "List<Integer>")
    private List<Integer> roleIdList;

}
