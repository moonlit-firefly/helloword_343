package com.iweb.controller.admin;

import com.iweb.entity.Category;
import com.iweb.entity.Property;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/3  14:41
 **/
@WebServlet(urlPatterns = "/propertyServlet")
public class PropertyServlet extends BaseBackServlet {
    public String list(HttpServletRequest req, HttpServletResponse resp){
        // service 调用  获取集合 请求存入
        Integer cid=Integer.parseInt(req.getParameter("id"));
        List<Property> properties=propertyService.list(cid);
        //将获取到的集合存入到请求中
        req.setAttribute("pts",properties);
        //通过转发跳转到jsp页面 页面通过el表达式将数据渲染解析出来
        return "/page/admin/property/listProperty.jsp";
    }
    public String add(HttpServletRequest req, HttpServletResponse resp){
        // service 调用  获取集合 请求存入
        Integer cid=Integer.parseInt(req.getParameter("cid"));
        String propertyName=req.getParameter("name");
        if(propertyName!=null&&cid!=null){
            Property property=new Property();
            property.setName(propertyName);
            Category category=new Category();
            category.setId(cid);
            property.setCategory(category);
            propertyService.add(property);
        }
        //重新发送请求
        return "@admin_property_list?id="+cid;
    }
    public String edit(HttpServletRequest req, HttpServletResponse resp){
        // service 调用 DAO调用 获取集合 请求存入
        Integer id=Integer.parseInt(req.getParameter("id"));
        //获取对应的分类对象
        Property property=propertyService.get(id);
        //数据存入请求
        req.setAttribute("pt",property);
        //转发跳转页面
        return "/page/admin/property/editProperty.jsp";
    }
    public String update(HttpServletRequest req, HttpServletResponse resp){
        // service 调用 DAO调用 获取集合 请求存入
        Property property=new Property();
        property.setId(Integer.parseInt(req.getParameter("id")));
        property.setName(req.getParameter("name"));
        int cid=Integer.parseInt(req.getParameter("cid"));
        Category category=categoryService.get(cid);
        property.setCategory(category);
        //调用service处理
        propertyService.update(property);
        //重新发送请求获取最新的listCategory页面
        return "@/admin_property_list?id="+cid;
    }
    public String delete(HttpServletRequest req, HttpServletResponse resp){
        //获取ajax请求中所携带的参数id
        Integer id=Integer.parseInt(req.getParameter("id"));
        propertyService.delete(id);
        //返回响应字符串
        return "%success";
    }


}
