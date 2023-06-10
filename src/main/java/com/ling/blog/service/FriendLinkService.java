package com.ling.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.blog.dto.FriendLinkBackDTO;
import com.ling.blog.dto.FriendLinkDTO;
import com.ling.blog.entity.FriendLink;
import com.ling.blog.vo.ConditionVO;
import com.ling.blog.vo.FriendLinkVO;
import com.ling.blog.vo.PageResult;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * 友链服务
 *
 * @Author : 风间离
 * @create 2023/5/28 18:21
 */
@Component
public interface FriendLinkService extends IService<FriendLink> {

    /**
     * 查看友链列表
     *
     * @return 友链列表
     */
    List<FriendLinkDTO> listFriendLinks();

    /**
     * 查看后台友链列表
     *
     * @param condition 条件
     * @return 友链列表
     */
    PageResult<FriendLinkBackDTO> listFriendLinkDTO(ConditionVO condition);

    /**
     * 查看后台友链未审核列表
     *
     * @param condition 条件
     * @return 友链列表
     */
    PageResult<FriendLinkBackDTO> listFriendLinkUnCheck(ConditionVO condition);

    /**
     * 保存或更新友链
     *
     * @param friendLinkVO 友链
     */
    void saveOrUpdateFriendLink(FriendLinkVO friendLinkVO);

}