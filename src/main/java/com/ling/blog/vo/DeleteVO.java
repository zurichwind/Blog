package com.ling.blog.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 逻辑删除
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "逻辑删除")
public class DeleteVO {

    /**
     * id列表
     */
    @NotNull(message = "id不能为空")
    @Schema(name = "idList", description = "id列表", required = true, type = "List<Integer>")
    private List<Integer> idList;

    /**
     * 状态值
     */
    @NotNull(message = "状态值不能为空")
    @Schema(name = "isDelete", description = "删除状态", required = true, type = "Integer")
    private Integer isDelete;

}
