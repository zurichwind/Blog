package com.ling.blog.handler;

import com.alibaba.fastjson.JSON;
import com.ling.blog.vo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.ling.blog.constant.CommonConst.APPLICATION_JSON;

/**
 * Created by IntelliJ IDEA.
 * 注销处理
 *
 * @Author : 风间离
 * @create 2023/5/28 15:53
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.ok()));
    }

}