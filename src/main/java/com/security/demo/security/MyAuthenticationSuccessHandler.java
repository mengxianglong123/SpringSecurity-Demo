package com.security.demo.security;

import com.alibaba.fastjson.JSON;
import com.security.demo.pojo.Result;
import com.security.demo.pojo.ResultCode;
import com.security.demo.utils.JwtTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

/**
 * 自定义认证成功处理器
 */

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //生成token
        final String realToken = jwtTokenUtil.generateToken(authentication.getName());
        HashMap<String,Object> map = new HashMap<>();
        map.put("token", realToken);
        Result r = new Result();
        r.code(ResultCode.SUCCESS).message("登录成功").data(map);

        //将生成的authentication放入容器中，生成安全的上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String json =  JSON.toJSONString(r);
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(json);

    }
}
