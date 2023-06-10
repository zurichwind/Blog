package com.ling.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ling.blog.dto.CategoryBackDTO;
import com.ling.blog.dto.CategoryDTO;
import com.ling.blog.entity.Category;
import com.ling.blog.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * 分类
 *
 * @Author : 风间离
 * @create 2023/5/27 18:43
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 查询分类和对应文章数量
     *
     * @return 分类列表
     */
    List<CategoryDTO> listCategoryDTO();

    /**
     * 查询后台分类列表
     *
     * @param current   页码
     * @param size      大小
     * @param condition 条件
     * @return {@link List<CategoryBackDTO>} 分类列表
     */
    List<CategoryBackDTO> listCategoryBackDTO(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);

}
