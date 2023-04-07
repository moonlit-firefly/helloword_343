package com.iweb.controller.admin;

import com.iweb.entity.Product;
import com.iweb.entity.ProductImage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/4  10:48
 **/
@WebServlet("/productImageServlet")
public class ProductImageServlet extends BaseBackServlet {
    public String list(HttpServletRequest req, HttpServletResponse resp){
        int pid=Integer.parseInt(req.getParameter("id"));
        // service 调用  获取集合 请求存入
        List<ProductImage> productImages=productImageService.list(pid);
        //将获取到的集合存入到请求中
        req.setAttribute("pis",productImages);
        req.setAttribute("pid",pid);
        //通过转发跳转到jsp页面 页面通过el表达式将数据渲染解析出来
        return "/page/admin/product/image/listProductImage.jsp";
    }
    public String edit(HttpServletRequest req, HttpServletResponse resp){
        // service 调用 DAO调用 获取集合 请求存入
        Integer piid=Integer.parseInt(req.getParameter("id"));
        //获取对应的对象
        ProductImage pi=productImageService.get(piid);
        //数据存入请求
        req.setAttribute("pi",pi);
        req.setAttribute("piid",piid);
        //转发跳转页面
        return "/page/admin/product/image/editProductImage.jsp";
    }
    public String update(HttpServletRequest req, HttpServletResponse resp){
        // service 调用 DAO调用 获取集合 请求存入
        ProductImage productImage=new ProductImage();
        productImage.setId(Integer.parseInt(req.getParameter("id")));
        productImage.setUrl(req.getParameter("url"));
        productImage.setProduct(productService.get(Integer.parseInt(req.getParameter("pid"))));
        int pid=Integer.parseInt(req.getParameter("pid"));
        productImageService.update(productImage);
        return "@/admin_productImage_list?id="+pid;
    }
    public String add(HttpServletRequest req, HttpServletResponse resp){
        // service 调用 DAO调用 获取集合 请求存入
        String url=req.getParameter("url");
        //参数封装到对象中
        ProductImage productImage=new ProductImage();
        productImage.setUrl(url);
        int pid=Integer.parseInt(req.getParameter("pid"));
        Product product=productService.get(pid);
        productImage.setProduct(product);
        //调用service
        productImageService.add(productImage);
        //重新发送请求
        return "@admin_productImage_list?id="+pid;
    }

    public String delete(HttpServletRequest req, HttpServletResponse resp){
        //获取ajax请求中所携带的参数id
        Integer id=Integer.parseInt(req.getParameter("id"));
        productImageService.delete(id);
        //返回响应字符串
        return "%success";
    }
}
