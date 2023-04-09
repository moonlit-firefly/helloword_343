package com.iweb.controller.fore;

import com.iweb.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/9  13:45
 **/
@WebServlet(urlPatterns = "/user1Servlet")
public class UserServlet extends BaseForeServlet {
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
            return "@/fore_mainPage_pageShow";
        }else{
            //如果登录失败
            return "@/page/fore/register/login.jsp";
        }
    }
    public String register(HttpServletRequest req, HttpServletResponse resp){
        //获取注册的信息 创建用户对象并存入数据库
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        User user=new User();
        user.setName(name);
        user.setPassword(password);
        //如果信息未填写完整
        if(name==""||password==""){
            return "%error";
        }
        //查看该用户名是否存在
        List<User> users=loginService.list();
        for (User u:users) {
            if(user.getName().equals(u.getName())){
                return "%error";
            }
        }
        //添加用户
        loginService.add(user);
        return "%success";
    }
}
