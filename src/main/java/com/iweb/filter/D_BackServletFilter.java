package com.iweb.filter;

import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈郅治
 * @date 2023/3/30  10:33
 **/
@WebFilter(urlPatterns = "/*")
public class D_BackServletFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //对参数向下转型
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse resp=(HttpServletResponse) response;
        //如果你定义了上下文  那么上下文就需要进行获取
        String contextPath=req.getContextPath();
        //获取uri
        String uri=req.getRequestURI();
        //如果定义了上下文 需要从uri中讲上下文去除
        uri=StringUtils.remove(uri,contextPath);
        //判断uri是否是以/admin_开头的
        //假设uri是location/admin_category_list
        if(uri.startsWith("/admin_")){
            //取出后续要访问的servlet的匹配路径
            //这里servletPath=categoryServlet
            String servletPath=StringUtils.substringBetween(uri,"_","_")+"Servlet";
            //取出后续想要访问的方法名称
            //这里method=list
            String method=StringUtils.substringAfterLast(uri,"_");
            //将方法名称存入到请求中
            req.setAttribute("method",method);
            //通过转发跳转到指定Servlet
            req.getRequestDispatcher("/"+servletPath).forward(req,resp);
            return;
        }
        //如果不是我们指定的 则请求放行
        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
