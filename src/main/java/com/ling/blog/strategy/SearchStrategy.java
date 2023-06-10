package com.ling.blog.strategy;

import com.ling.blog.dto.ArticleSearchDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * 搜素策略
 *
 * @Author : 风间离
 * @create 2023/5/28 12:11
 */
@Service
public interface SearchStrategy {

    /**
     * 搜索文章
     *
     * @param keywords 关键字
     * @return {@link List <ArticleSearchDTO>} 文章列表
     */
    List<ArticleSearchDTO> searchArticle(String keywords);

}