package com.ling.blog.strategy.impl;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import com.ling.blog.enums.ArticleStatusEnum;
import com.ling.blog.exception.BizException;
import com.ling.blog.service.ArticleService;
import com.ling.blog.strategy.ArticleImportStrategy;
import com.ling.blog.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * 普通文章导入策略
 *
 * @Author : 风间离
 * @create 2023/5/28 12:51
 */
@Slf4j
@Service("normalArticleImportStrategyImpl")
public class NormalArticleImportStrategyImpl implements ArticleImportStrategy {
    @Autowired
    private ArticleService articleService;

    @Override
    public void importArticles(MultipartFile file) {
        // 获取文件名作为文章标题
        String articleTitle = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[0];
        // 获取文章内容
        StringBuilder articleContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            while (reader.ready()) {
                articleContent.append((char) reader.read());
            }
        } catch (IOException e) {
            log.error(StrUtil.format("导入文章失败, 堆栈:{}", ExceptionUtil.stacktraceToString(e)));
            throw new BizException("导入文章失败");
        }
        // 保存文章
        ArticleVO articleVO = ArticleVO.builder()
                .articleTitle(articleTitle)
                .articleContent(articleContent.toString())
                .status(ArticleStatusEnum.DRAFT.getStatus())
                .build();
        articleService.saveOrUpdateArticle(articleVO);
    }
}
