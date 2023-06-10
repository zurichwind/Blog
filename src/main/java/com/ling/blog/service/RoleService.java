package com.ling.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.blog.dto.RoleDTO;
import com.ling.blog.dto.UserRoleDTO;
import com.ling.blog.entity.Role;
import com.ling.blog.vo.ConditionVO;
import com.ling.blog.vo.PageResult;
import com.ling.blog.vo.RoleVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * 角色服务
 *
 * @Author : 风间离
 * @create 2023/5/28 18:28
 */
@Component
public interface RoleService extends IService<Role> {

    /**
     * 获取用户角色选项
     *
     * @return 角色
     */
    List<UserRoleDTO> listUserRoles();

    /**
     * 查询角色列表
     *
     * @param conditionVO 条件
     * @return 角色列表
     */
    PageResult<RoleDTO> listRoles(ConditionVO conditionVO);

    /**
     * 保存或更新角色
     *
     * @param roleVO 角色
     */
    void saveOrUpdateRole(RoleVO roleVO);

    /**
     * 删除角色
     * @param roleIdList 角色id列表
     */
    void deleteRoles(List<Integer> roleIdList);

}
