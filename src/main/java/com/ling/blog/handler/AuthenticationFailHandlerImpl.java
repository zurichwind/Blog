package com.ling.blog.handler;

import com.alibaba.fastjson.JSON;
import com.ling.blog.vo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.ling.blog.constant.CommonConst.APPLICATION_JSON;

/**
 * Created by IntelliJ IDEA.
 * 登录失败处理
 *
 * @Author : 风间离
 * @create 2023/5/28 15:46
 */
@Slf4j
@Component
public class AuthenticationFailHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.fail(e.getMessage())));
        log.info("用户登录失败！");
    }

}
