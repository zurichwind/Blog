package com.ling.blog.strategy;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 * 文章导入策略
 *
 * @Author : 风间离
 * @create 2023/5/28 12:09
 */
@Service
public interface ArticleImportStrategy {

    /**
     * 导入文章
     *
     * @param file 文件
     */
    void importArticles(MultipartFile file);
}