package com.ling.blog.handler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ling.blog.utils.PageUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

import static com.ling.blog.constant.CommonConst.*;

/**
 * Created by IntelliJ IDEA.
 * 分页拦截器
 *
 * @Author : 风间离
 * @create 2023/5/28 15:55
 */
@Slf4j
public class PageableHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String s = request.toString();
        log.info("打印request请求");
        log.info(s);
        String currentPage = request.getParameter(CURRENT);
        String pageSize = Optional.ofNullable(request.getParameter(SIZE)).orElse(DEFAULT_SIZE);
        if(currentPage!=null&&currentPage!=""){
            PageUtils.setCurrentPage(new Page<>(Long.parseLong(currentPage), Long.parseLong(pageSize)));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        PageUtils.remove();
    }

}
