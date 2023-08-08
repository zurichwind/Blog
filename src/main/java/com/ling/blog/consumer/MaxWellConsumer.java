package com.ling.blog.consumer;

import com.alibaba.fastjson.JSON;
import com.ling.blog.constant.MQPrefixConst;
import com.ling.blog.dto.ArticleSearchDTO;
import com.ling.blog.dto.MaxwellDataDTO;
import com.ling.blog.entity.Article;
import com.ling.blog.mapper.ElasticsearchMapper;
import com.ling.blog.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * maxwell监听数据
 *
 * @author yezhiqiu
 * @date 2021/08/02
 */
@Slf4j
@Component
@RabbitListener(queues = MQPrefixConst.MAXWELL_QUEUE)
public class MaxWellConsumer {
    @Autowired
    private ElasticsearchMapper elasticsearchMapper;

    @RabbitHandler
    public void process(byte[] data) {
        // 获取监听信息
        MaxwellDataDTO maxwellDataDTO = JSON.parseObject(new String(data), MaxwellDataDTO.class);
        log.info("maxwellDataDTO = " + maxwellDataDTO);
        // 获取文章数据
        Article article = JSON.parseObject(JSON.toJSONString(maxwellDataDTO.getData()), Article.class);
        log.info("article = " + article);
        log.info("MaxWellConsumer");
        // 判断操作类型
        switch (maxwellDataDTO.getType()) {
            case "insert":
            case "update":
                // 更新es文章
                elasticsearchMapper.save(BeanCopyUtils.copyObject(article, ArticleSearchDTO.class));
                break;
            case "delete":
                // 删除文章
                elasticsearchMapper.deleteById(article.getId());
                break;
            default:
                break;
        }
    }

}