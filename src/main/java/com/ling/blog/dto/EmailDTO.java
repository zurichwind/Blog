package com.ling.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {

    /**
     * 邮箱号
     */
    private String email;

    /**
     * 主题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;

}
