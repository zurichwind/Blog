package com.ling.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.blog.dto.LabelOptionDTO;
import com.ling.blog.dto.ResourceDTO;
import com.ling.blog.entity.Resource;
import com.ling.blog.vo.ConditionVO;
import com.ling.blog.vo.ResourceVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * 资源服务
 *
 * @Author : 风间离
 * @create 2023/5/28 18:25
 */
@Component
public interface ResourceService extends IService<Resource> {

    /**
     * 添加或修改资源
     *
     * @param resourceVO 资源对象
     */
    void saveOrUpdateResource(ResourceVO resourceVO);

    /***
     * 删除资源
     *
     @param resourceId 资源id
     */
    void deleteResource(Integer resourceId);

    /**
     * 查看资源列表
     *
     * @param conditionVO 条件
     * @return 资源列表
     */
    List<ResourceDTO> listResources(ConditionVO conditionVO);

    /**
     * 查看资源选项
     *
     * @return 资源选项
     */
    List<LabelOptionDTO> listResourceOption();

}
