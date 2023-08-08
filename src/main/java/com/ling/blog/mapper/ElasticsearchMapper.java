package com.ling.blog.mapper;

import com.ling.blog.dto.ArticleSearchDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * elasticsearch
 *
 * @author wind
 * @date
 */
@Mapper
public interface ElasticsearchMapper extends ElasticsearchRepository<ArticleSearchDTO,Integer> {

}
