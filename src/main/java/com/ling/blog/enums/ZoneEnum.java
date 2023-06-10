package com.ling.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 时区枚举
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Getter
@AllArgsConstructor
public enum ZoneEnum {

    /**
     * 上海
     */
    SHANGHAI("Asia/Shanghai", "中国上海");

    /**
     * 时区
     */
    private final String zone;

    /**
     * 描述
     */
    private final String desc;

}
