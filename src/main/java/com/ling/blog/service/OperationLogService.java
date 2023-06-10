package com.ling.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.blog.dto.OperationLogDTO;
import com.ling.blog.entity.OperationLog;
import com.ling.blog.vo.ConditionVO;
import com.ling.blog.vo.PageResult;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * 操作日志服务
 *
 * @Author : 风间离
 * @create 2023/5/28 18:24
 */
@Component
public interface OperationLogService extends IService<OperationLog> {

    /**
     * 查询日志列表
     *
     * @param conditionVO 条件
     * @return 日志列表
     */
    PageResult<OperationLogDTO> listOperationLogs(ConditionVO conditionVO);

}