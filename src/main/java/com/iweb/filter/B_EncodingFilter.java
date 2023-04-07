package com.iweb.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈郅治
 * @date 2023/3/30  9:14
 **/
//表示过滤器会过滤所有请求
    //过滤器中是不可以
@WebFilter(urlPatterns = "/*")
public class B_EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //对参数向下转型
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse resp=(HttpServletResponse) response;
        req.setCharacterEncoding("UTF-8");
        //过滤器放行请求
        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
