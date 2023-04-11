//package com.iweb.filter;
//
//import com.iweb.entity.User;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///** 根据session判断用户是否登录
// * 我们应该提前准备好对应的url字符串数组
// * 如果发现用户访问的路径是无需登录就可以访问的  就请求放行
// * 如果发现用户访问的路径是需要登录才可以访问的  就做session验证
// * 如果用户未登录，就拦截
// * @author 陈郅治
// * @date 2023/4/7  9:19
// **/
//@WebFilter(urlPatterns = "/*")
//public class E_ForeServletFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        //用户校验标志
//        boolean flag=false;
//        //对参数向下转型
//        HttpServletRequest req= (HttpServletRequest) request;
//        HttpServletResponse resp=(HttpServletResponse) response;
//        //获取请求uri
//        String uri=req.getRequestURI();
//        //这里过滤器就应该从session中获取用户 判断是否为空
//        User buyUser=(User) req.getSession().getAttribute("buyUser");
//        if(null!=buyUser){
//            flag=true;
//        }else {
//            flag=false;
//        }
//        //如果是这些请求，直接放行
//        if(uri.endsWith("login.jsp")||uri.endsWith("login")
//                ||uri.endsWith("gif")||uri.endsWith("jpg")
//                ||uri.endsWith("webp")||uri.endsWith("css")
//                ||uri.endsWith("js")||uri.endsWith("png")){
//            chain.doFilter(req,resp);
//            return;
//        }
//        //涉及到购物车，订单等登陆后业务的，需要验证是否登陆
//        if(uri.contains("order")||uri.contains("cart")){
//            if(flag){
//                chain.doFilter(req,resp);
//                return;
//            }else {
//                resp.sendRedirect("/fore_mainPage_pageShow");
//                return;
//            }
//        }
//        //如果是主界面访问请求,转到主界面
//        if(uri.endsWith("mainPage.jsp")||uri.endsWith("pageShow")){
//            chain.doFilter(req,resp);
//            return;
//        }
//        chain.doFilter(req,resp);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
