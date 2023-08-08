package com.ling.blog;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 风间离
 * @Create 2023/8/7  8:22
 */

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.ling.blog.dto.ArticleSearchDTO;
import com.ling.blog.strategy.SearchStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.ling.blog.constant.CommonConst.FALSE;
import static com.ling.blog.constant.ElasticConstant.*;
import static com.ling.blog.enums.ArticleStatusEnum.PUBLIC;

/**
 * ES搜索策略
 *
 * @author ican
 */
@Slf4j
//@Service("esSearchStrategyImpl")
public class EsSearchStrategyImpl implements SearchStrategy {

    //@Autowired
    private ElasticsearchClient elasticsearchClient;

    @Override
    public List<ArticleSearchDTO> searchArticle(String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return new ArrayList<>();
        }
        try {
            // 条件构造
            SearchRequest searchRequest = SearchRequest.of(s -> s.index(ARTICLE_INDEX)
                    .query(query -> query
                            .bool(bool -> bool
                                    .must(must -> must.match(m -> m.field("all").query(FieldValue.of(keyword))))
                                    .must(must -> must.term(m -> m.field("isDelete").value(FieldValue.of(FALSE))))
                                    .must(must -> must.term(m -> m.field("status").value(FieldValue.of(PUBLIC.getStatus())))))
                    ).highlight(h -> h
                            .fields(ARTICLE_TITLE, f -> f.preTags(PRE_TAG).postTags(POST_TAG))
                            .fields(ARTICLE_CONTENT, f -> f.preTags(PRE_TAG).postTags(POST_TAG))
                            .requireFieldMatch(false)
                    ));
            SearchResponse<ArticleSearchDTO> search = elasticsearchClient.search(searchRequest, ArticleSearchDTO.class);
            // 解析结果
            return handleResponse(search);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    private List<ArticleSearchDTO> handleResponse(SearchResponse<ArticleSearchDTO> response) {
        // 解析结果并返回
        return response.hits().hits().stream()
                .map(hit -> {
                    if (CollectionUtils.isNotEmpty(hit.highlight().get(ARTICLE_TITLE))) {
                        Objects.requireNonNull(hit.source()).setArticleTitle(hit.highlight().get(ARTICLE_TITLE).get(0));
                    }
                    if (CollectionUtils.isNotEmpty(hit.highlight().get(ARTICLE_CONTENT))) {
                        Objects.requireNonNull(hit.source()).setArticleContent(hit.highlight().get(ARTICLE_CONTENT).get(0));
                    } else {
                        Objects.requireNonNull(hit.source()).setArticleContent(hit.source().getArticleContent().substring(0, 300));
                    }
                    return hit.source();
                })
                .collect(Collectors.toList());
    }
}