package com.ling.blog.handler;

import com.alibaba.fastjson.JSON;
import com.ling.blog.annotation.AccessLimit;
import com.ling.blog.service.RedisService;
import com.ling.blog.utils.IpUtils;
import com.ling.blog.vo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import static com.ling.blog.constant.CommonConst.APPLICATION_JSON;

/**
 * Created by IntelliJ IDEA.
 *
 *
 * @Author : 风间离
 * @create 2023/5/28 15:56
 */
@Slf4j
public class WebSecurityHandler implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        log.info("调用WebSecurityHandler");
        // 如果请求输入方法
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            // 获取方法中的注解,看是否有该注解
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if (accessLimit != null) {
                long seconds = accessLimit.seconds();
                    int maxCount = accessLimit.maxCount();
                    // 关于key的生成规则可以自己定义 本项目需求是对每个方法都加上限流功能，如果你只是针对ip地址限流，那么key只需要只用ip就好
                    String key = IpUtils.getIpAddress(httpServletRequest) + hm.getMethod().getName();
                    // 从redis中获取用户访问的次数
                    try {
                        // 此操作代表获取该key对应的值自增1后的结果
                        long q = redisService.incrExpire(key, seconds);
                        if (q > maxCount) {
                        render(httpServletResponse, Result.fail("请求过于频繁，请稍候再试"));
                        log.warn(key + "请求次数超过每" + seconds + "秒" + maxCount + "次");
                        return false;
                    }
                    return true;
                } catch (RedisConnectionFailureException e) {
                    log.warn("redis错误: " + e.getMessage());
                    return false;
                }
            }
        }
        return true;
    }

    private void render(HttpServletResponse response, Result<?> result) throws Exception {
        response.setContentType(APPLICATION_JSON);
        OutputStream out = response.getOutputStream();
        String str = JSON.toJSONString(result);
        out.write(str.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }

}
