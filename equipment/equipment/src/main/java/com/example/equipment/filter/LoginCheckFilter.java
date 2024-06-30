package com.example.equipment.filter;


import com.alibaba.fastjson.JSONObject;
import com.example.equipment.pojo.Result;
import com.example.equipment.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI().toString();
        log.info("请求的url:{}",url);

        resp.addHeader("Access-Control-Allow-Credentials", "true");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,DELETE,PUT");
        resp.addHeader("Access-Control-Allow-Headers", "*");
        resp.addHeader("Access-Control-Max-Age", "3600");
        if ("OPTIONS".equals(req.getMethod())){
            servletResponse.getWriter().println("ok");
            return;
        }

        if(url.contains("login") || url.contains("register")){
            log.info("登录操作,放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }


        String jwt = req.getHeader("token");

        if(!StringUtils.hasLength(jwt)){
            log.info("请求头为空");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败,返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        log.info("令牌合法,放行");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
