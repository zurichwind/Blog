package com.ling.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.blog.dto.TagBackDTO;
import com.ling.blog.dto.TagDTO;
import com.ling.blog.entity.Tag;
import com.ling.blog.vo.ConditionVO;
import com.ling.blog.vo.PageResult;
import com.ling.blog.vo.TagVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * 标签服务
 *
 * @Author : 风间离
 * @create 2023/5/28 18:30
 */
@Component
public interface TagService extends IService<Tag> {

    /**
     * 查询标签列表
     *
     * @return 标签列表
     */
    PageResult<TagDTO> listTags();

    /**
     * 查询后台标签
     *
     * @param condition 条件
     * @return {@link PageResult<TagBackDTO>} 标签列表
     */
    PageResult<TagBackDTO> listTagBackDTO(ConditionVO condition);

    /**
     * 搜索文章标签
     *
     * @param condition 条件
     * @return {@link List <TagDTO>} 标签列表
     */
    List<TagDTO> listTagsBySearch(ConditionVO condition);

    /**
     * 删除标签
     *
     * @param tagIdList 标签id集合
     */
    void deleteTag(List<Integer> tagIdList);

    /**
     * 保存或更新标签
     *
     * @param tagVO 标签
     */
    void saveOrUpdateTag(TagVO tagVO);

}
