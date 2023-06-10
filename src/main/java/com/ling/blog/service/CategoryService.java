package com.ling.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.blog.dto.CategoryBackDTO;
import com.ling.blog.dto.CategoryDTO;
import com.ling.blog.dto.CategoryOptionDTO;
import com.ling.blog.entity.Category;
import com.ling.blog.vo.CategoryVO;
import com.ling.blog.vo.ConditionVO;
import com.ling.blog.vo.PageResult;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * 目录服务 分类服务
 *
 * @Author : 风间离
 * @create 2023/5/28 18:20
 */
@Component
public interface CategoryService extends IService<Category> {

    /**
     * 查询分类列表
     *
     * @return 分类列表
     */
    PageResult<CategoryDTO> listCategories();

    /**
     * 查询后台分类
     *
     * @param conditionVO 条件
     * @return {@link PageResult<CategoryBackDTO>} 后台分类
     */
    PageResult<CategoryBackDTO> listBackCategories(ConditionVO conditionVO);

    /**
     * 搜索文章分类
     *
     * @param condition 条件
     * @return {@link List <CategoryOptionDTO>} 分类列表
     */
    List<CategoryOptionDTO> listCategoriesBySearch(ConditionVO condition);

    /**
     * 删除分类
     *
     * @param categoryIdList 分类id集合
     */
    void deleteCategory(List<Integer> categoryIdList);

    /**
     * 添加或修改分类
     *
     * @param categoryVO 分类
     */
    void saveOrUpdateCategory(CategoryVO categoryVO);

}
