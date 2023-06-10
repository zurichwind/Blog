package com.ling.blog.strategy.context;

import com.ling.blog.enums.MarkdownTypeEnum;
import com.ling.blog.strategy.ArticleImportStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * 文章导入策略上下文
 *
 * @Author : 风间离
 * @create 2023/5/28 12:16
 */
@Service
public class ArticleImportStrategyContext {
    @Autowired
    private Map<String, ArticleImportStrategy> articleImportStrategyMap;

    public void importArticles(MultipartFile file, String type) {
        articleImportStrategyMap.get(MarkdownTypeEnum.getMarkdownType(type)).importArticles(file);
    }
}
