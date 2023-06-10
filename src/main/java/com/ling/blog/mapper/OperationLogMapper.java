package com.ling.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ling.blog.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by IntelliJ IDEA.
 * 操作日志
 *
 * @Author : 风间离
 * @create 2023/5/27 17:31
 */

@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {
}
