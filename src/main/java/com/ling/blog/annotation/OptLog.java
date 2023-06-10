package com.ling.blog.annotation;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * 操作日志注解
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptLog {

    /**
     * @return 操作类型
     */
    String optType() default "";

}