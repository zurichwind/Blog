package com.ling.blog.annotation;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * redis接口限流
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimit {

    /**
     * 单位时间（秒）
     *
     * @return int
     */
    int seconds();

    /**
     * 单位时间最大请求次数
     *
     * @return int
     */
    int maxCount();
}

