package com.ling.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * 资源角色
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Data
public class ResourceRoleDTO {

    /**
     * 资源id
     */
    private Integer id;

    /**
     * 路径
     */
    private String url;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 角色名
     */
    private List<String> roleList;

}
