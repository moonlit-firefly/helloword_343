package com.iweb.controller.admin;

import com.iweb.entity.Order;
import com.iweb.entity.Product;
import com.iweb.entity.User;
import com.iweb.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/3  9:45
 **/
@WebServlet(urlPatterns = "/userServlet")
public class UserServlet extends BaseBackServlet {
    public String exitLogin(HttpServletRequest request, HttpServletResponse response){
        //获取session 并且清除session中的用户信息
        request.getSession().removeAttribute("user");
        //重新回到用户登录界面
        return "@/page/admin/login/login.jsp";
    }
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接收参数并封装
        User user=new User();
        user.setName(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        //调用service进行处理  loginService来自父类属性
        boolean isLogin=loginService.login(user);
        if(isLogin){
            //如果登录成功  将用户信息存入session用于后续校验
            request.getSession().setAttribute("user",user);
            return "@/admin_category_list";
        }else{
            //如果登录失败
            return "@/page/admin/login/login.jsp";
        }
    }
    public String list(HttpServletRequest req, HttpServletResponse resp){
        // service 调用  获取集合 请求存入
        List<User> users= loginService.list();
        //将获取到的集合存入到请求中
        req.setAttribute("us",users);
        //通过转发跳转到jsp页面 页面通过el表达式将数据渲染解析出来
        return "/page/admin/user/listUser.jsp";
    }
    public String listOrder(HttpServletRequest req, HttpServletResponse resp){
        // service 调用  获取集合 请求存入
        List<Order> orders=orderService.list();
        //将获取到的集合存入到请求中
        req.setAttribute("os",orders);
        //通过转发跳转到jsp页面 页面通过el表达式将数据渲染解析出来
        return "/page/admin/order/listOrder.jsp";
    }

    public String userOrderList(HttpServletRequest req, HttpServletResponse resp){
        // service 调用  获取集合 请求存入
        Integer uid=Integer.parseInt(req.getParameter("id"));
        List<Order> orders=orderService.list(uid);
        //将获取到的集合存入到请求中
        req.setAttribute("os",orders);
        req.setAttribute("uid",uid);
        //通过转发跳转到jsp页面 页面通过el表达式将数据渲染解析出来
        return "/page/admin/order/listUserOrder.jsp";
    }



}
