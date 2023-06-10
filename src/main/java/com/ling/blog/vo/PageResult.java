package com.ling.blog.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页对象
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Tag(name = "分页对象")
public class PageResult<T> {

    /**
     * 分页列表
     */
    @Schema(name = "recordList", description = "分页列表", required = true, type = "List<T>")
    private List<T> recordList;

    /**
     * 总数
     */
    @Schema(name = "count", description = "总数", required = true, type = "Integer")
    private Integer count;

}
