package com.ling.blog.handler;

import com.alibaba.fastjson.JSON;
import com.ling.blog.enums.StatusCodeEnum;
import com.ling.blog.vo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.ling.blog.constant.CommonConst.APPLICATION_JSON;

/**
 * Created by IntelliJ IDEA.
 * 用户未登录处理
 *
 * @Author : 风间离
 * @create 2023/5/28 15:45
 */
@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        log.info("*****************");
        log.warn("用户未登录处理");
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.fail(StatusCodeEnum.NO_LOGIN)));
    }

}