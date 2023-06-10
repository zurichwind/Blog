package com.ling.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.blog.dto.UniqueViewDTO;
import com.ling.blog.entity.UniqueView;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * 用户量统计
 *
 * @Author : 风间离
 * @create 2023/5/28 18:31
 */
@Component
public interface UniqueViewService extends IService<UniqueView> {

    /**
     * 获取7天用户量统计
     *
     * @return 用户量
     */
    List<UniqueViewDTO> listUniqueViews();

}