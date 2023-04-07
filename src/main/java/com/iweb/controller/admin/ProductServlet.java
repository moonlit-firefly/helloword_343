package com.iweb.controller.admin;

import com.iweb.entity.Category;
import com.iweb.entity.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/3  22:28
 **/
@WebServlet(urlPatterns = "/productServlet")
public class ProductServlet extends BaseBackServlet {
    public String list(HttpServletRequest req, HttpServletResponse resp){
        int cid=Integer.parseInt(req.getParameter("id"));
        // service 调用  获取集合 请求存入
        List<Product> products=productService.list(cid);
        //将获取到的集合存入到请求中
        req.setAttribute("ps",products);
        req.setAttribute("cid",cid);
        //通过转发跳转到jsp页面 页面通过el表达式将数据渲染解析出来
        return "/page/admin/product/listProduct.jsp";
    }
    public String edit(HttpServletRequest req, HttpServletResponse resp){
        // service 调用 DAO调用 获取集合 请求存入
        Integer pid=Integer.parseInt(req.getParameter("id"));
        //获取对应的对象
        Product p=productService.get(pid);
        //数据存入请求
        req.setAttribute("p",p);
        //转发跳转页面
        return "/page/admin/product/editProduct.jsp";
    }
    public String update(HttpServletRequest req, HttpServletResponse resp){
        // service 调用 DAO调用 获取集合 请求存入
        Product product=new Product();
        product.setId(Integer.parseInt(req.getParameter("id")));
        product.setName(req.getParameter("name"));
        product.setSubTitle(req.getParameter("subTitle"));
        product.setOriginalPrice(Float.parseFloat(req.getParameter("originalPrice")));
        product.setPromotePrice(Float.parseFloat(req.getParameter("promotePrice")));
        product.setStock(Integer.parseInt(req.getParameter("stock")));
        int cid=Integer.parseInt(req.getParameter("cid"));
        Category category=categoryService.get(cid);
        product.setCategory(category);
        //调用service处理
        productService.update(product);
        //重新发送请求获取最新的listCategory页面
        return "@/admin_product_list?id="+cid;
    }
    public String add(HttpServletRequest req, HttpServletResponse resp){
        // service 调用 DAO调用 获取集合 请求存入
        String name=req.getParameter("name");
        //参数封装到对象中
            Product product=new Product();
            product.setName(name);
            product.setSubTitle(req.getParameter("subTitle"));
            product.setOriginalPrice(Float.parseFloat(req.getParameter("originalPrice")));
            product.setPromotePrice(Float.parseFloat(req.getParameter("promotePrice")));
            product.setStock(Integer.parseInt(req.getParameter("stock")));
            int cid=Integer.parseInt(req.getParameter("cid"));
            Category category=categoryService.get(cid);
            product.setCategory(category);
            //调用service
            productService.add(product);
        //重新发送请求
        return "@admin_product_list?id="+cid;
    }

    public String delete(HttpServletRequest req, HttpServletResponse resp){
        //获取ajax请求中所携带的参数id
        Integer id=Integer.parseInt(req.getParameter("id"));
        productService.delete(id);
        //返回响应字符串
        return "%success";
    }
}
