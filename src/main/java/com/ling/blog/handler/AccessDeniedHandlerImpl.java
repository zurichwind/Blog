package com.ling.blog.handler;

import com.alibaba.fastjson.JSON;
import com.ling.blog.vo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.ling.blog.constant.CommonConst.APPLICATION_JSON;


/**
 * Created by IntelliJ IDEA.
 * 用户权限处理
 *
 * @Author : 风间离
 * @create 2023/5/28 15:44
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.fail("权限不足")));
    }


}
