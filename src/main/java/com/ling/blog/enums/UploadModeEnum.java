package com.ling.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 上传模式枚举
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Getter
@AllArgsConstructor
public enum UploadModeEnum {
    /**
     * oss
     */
    OSS("oss", "ossUploadStrategyImpl"),
    /**
     * 本地
     */
    LOCAL("local", "localUploadStrategyImpl"),

    /**
     * cos
     */
    COS("cos", "cosUploadStrategyImpl");

    /**
     * 模式
     */
    private final String mode;

    /**
     * 策略
     */
    private final String strategy;

    /**
     * 获取策略
     *
     * @param mode 模式
     * @return {@link String} 搜索策略
     */
    public static String getStrategy(String mode) {
        for (UploadModeEnum value : UploadModeEnum.values()) {
            if (value.getMode().equals(mode)) {
                return value.getStrategy();
            }
        }
        return null;
    }

}
