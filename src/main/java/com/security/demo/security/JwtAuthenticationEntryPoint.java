package com.security.demo.security;

import com.alibaba.fastjson.JSON;
import com.security.demo.pojo.Result;
import com.security.demo.pojo.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * 认证失败处理类
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        System.out.println("认证失败");
        Result r = new Result();
        r.code(ResultCode.UNAUTHORIZED).message("认证失败");
        String json =  JSON.toJSONString(r);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
