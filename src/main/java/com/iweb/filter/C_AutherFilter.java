package com.iweb.filter;

import com.iweb.entity.User;
import com.iweb.service.CategoryService;
import com.iweb.service.impl.CategoryServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈郅治
 * @date 2023/3/30  9:39
 **/
@WebFilter(urlPatterns = "/*")
public class C_AutherFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //对参数向下转型
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //用户校验标志
        boolean flag=false;
        //获取请求uri
        String uri = req.getRequestURI();
        //判断请求访问的是否是登陆界面或者是提交登录信息的请求
        //如果是这请求，直接放行  否则会进入死循环
        if (uri.endsWith("login.jsp") || uri.endsWith("login")
                || uri.endsWith("gif") || uri.endsWith("jpg")
                || uri.endsWith("webp") || uri.endsWith("css")
                || uri.endsWith("js") || uri.endsWith("png")) {
            chain.doFilter(req, resp);
            return;
        }
        //这里过滤器就应该从session中获取用户 判断是否为空
        User buyUser=(User) req.getSession().getAttribute("buyUser");
        if(null!=buyUser){
            flag=true;
        }else {
            flag=false;
        }
        //涉及到购物车，订单等登陆后业务的，需要验证是否登陆
        if (uri.contains("order") || uri.contains("cart")) {
            if (flag) {
                chain.doFilter(req, resp);
                return;
            } else {
                resp.sendRedirect("/fore_mainPage_pageShow");
                return;
            }
        }


        //放行关于前台的部分
        if (uri.contains("fore")) {
            chain.doFilter(req, resp);
            return;
        }
        //我们这里省略cookie的操作  我们假设登录成功  就直接把用户面馆存放到当前session
        //这里过滤器就应该从session中获取用户 判断是否为空
        User user = (User) req.getSession().getAttribute("user");
        if (null == user) {
            resp.sendRedirect("/fore_mainPage_pageShow");
            return;
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
