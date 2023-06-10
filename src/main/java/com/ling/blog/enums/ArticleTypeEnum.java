package com.ling.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文章类型枚举
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Getter
@AllArgsConstructor
public enum ArticleTypeEnum {

    /**
     * 原始
     */
    ORIGINAL(1,"原创"),
    /**
     * 转载
     */
    REPRINTED(2,"转载"),

    /**
     * 翻译
     */
    TRANSLATION(3,"翻译");

    /**
     * 类型
     */
    private final Integer type;

    /**
     * 描述
     */
    private final String desc;
}
