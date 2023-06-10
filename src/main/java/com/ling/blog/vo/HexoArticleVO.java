package com.ling.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * hexo文章
 *
 * @Author : 风间离
 * @create 2023/5/27 17:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HexoArticleVO extends ArticleVO {
    private LocalDateTime createTime;
}
