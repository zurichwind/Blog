package com.ling.blog.service;


import com.ling.blog.dto.ArticleSearchDTO;

/**
 * es文章业务接口
 *
 * @author ican
 */
public interface ElasticsearchService {

    /**
     * 添加文章
     *
     * @param article 文章
     */
    void addArticle(ArticleSearchDTO article);

    /**
     * 更新文章
     *
     * @param article 文章
     */
    void updateArticle(ArticleSearchDTO article);

    /**
     * 删除文章
     *
     * @param id 文章id
     */
    void deleteArticle(Integer id);

}
