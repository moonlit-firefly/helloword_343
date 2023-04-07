package com.iweb.controller.admin;

import com.iweb.entity.Category;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/3/30  10:49
 **/
@WebServlet(urlPatterns = "/categoryServlet")
public class CategoryServlet extends BaseBackServlet {
    public String list(HttpServletRequest req, HttpServletResponse resp){
        // service 调用  获取集合 请求存入
        List<Category> categories=categoryService.list();
        //将获取到的集合存入到请求中
        req.setAttribute("cs",categories);
        //通过转发跳转到jsp页面 页面通过el表达式将数据渲染解析出来
        return "/page/admin/category/listCategory.jsp";
    }
    public String edit(HttpServletRequest req, HttpServletResponse resp){
        // service 调用 DAO调用 获取集合 请求存入
        Integer id=Integer.parseInt(req.getParameter("id"));
        //获取对应的分类对象
        Category category=categoryService.get(id);
        //数据存入请求
        req.setAttribute("c",category);
        //转发跳转页面
        return "/page/admin/category/editCategory.jsp";
    }

    public String update(HttpServletRequest req, HttpServletResponse resp){
        // service 调用 DAO调用 获取集合 请求存入
        Category category=new Category();
        category.setId(Integer.parseInt(req.getParameter("id")));
        category.setName(req.getParameter("name"));
        //调用service处理
        categoryService.update(category);
        //重新发送请求获取最新的listCategory页面
        return "@/admin_category_list";
    }

    public String add(HttpServletRequest req, HttpServletResponse resp){
        // service 调用 DAO调用 获取集合 请求存入
        String name=req.getParameter("name");
        //参数封装到对象中
        if(name!=""){
            Category category=new Category();
            category.setName(name);
            //调用service
            categoryService.add(category);
        }
        //重新发送请求
        return "@admin_category_list";
    }

    public String delete(HttpServletRequest req, HttpServletResponse resp){
        //获取ajax请求中所携带的参数id
        Integer id=Integer.parseInt(req.getParameter("id"));
        categoryService.delete(id);
        //返回响应字符串
        return "%success";
    }
}
