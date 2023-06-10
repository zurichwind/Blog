package com.ling.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.blog.dto.LabelOptionDTO;
import com.ling.blog.dto.MenuDTO;
import com.ling.blog.dto.UserMenuDTO;
import com.ling.blog.entity.Menu;
import com.ling.blog.vo.ConditionVO;
import com.ling.blog.vo.MenuVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * 菜单服务
 *
 * @Author : 风间离
 * @create 2023/5/28 18:22
 */
@Component
public interface MenuService extends IService<Menu> {

    /**
     * 查看菜单列表
     *
     * @param conditionVO 条件
     * @return 菜单列表
     */
    List<MenuDTO> listMenus(ConditionVO conditionVO);

    /**
     * 新增或修改菜单
     *
     * @param menuVO 菜单信息
     */
    void saveOrUpdateMenu(MenuVO menuVO);

    /**
     * 删除菜单
     *
     * @param menuId 菜单id
     */
    void deleteMenu(Integer menuId);

    /**
     * 查看角色菜单选项
     *
     * @return 角色菜单选项
     */
    List<LabelOptionDTO> listMenuOptions();

    /**
     * 查看用户菜单
     *
     * @return 菜单列表
     */
    List<UserMenuDTO> listUserMenus();

}
