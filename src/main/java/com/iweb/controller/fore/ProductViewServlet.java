package com.iweb.controller.fore;

import com.iweb.entity.Product;
import com.iweb.entity.PropertyValue;
import com.iweb.entity.Review;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/9  13:34
 **/
@WebServlet(urlPatterns = "/productViewServlet")
public class ProductViewServlet extends BaseForeServlet {
    public String search(HttpServletRequest req, HttpServletResponse resp){
        //模糊搜索商品后跳转到商品展示页面
        String value=req.getParameter("value");
        List<Product> products=productService.list(value);
        req.setAttribute("ps",products);
        return "/page/fore/product/listProduct.jsp";
    }
    public String listByCategory(HttpServletRequest req, HttpServletResponse resp){
        //模糊搜索商品后跳转到商品展示页面
        Integer cid=Integer.parseInt(req.getParameter("id"));
        List<Product> products=productService.list(cid);
        req.setAttribute("ps",products);
        return "/page/fore/product/listProduct.jsp";
    }
    public String show(HttpServletRequest req, HttpServletResponse resp){
        //展示商品详情页面
        Integer pid=Integer.parseInt(req.getParameter("id"));
        Product product=productService.get(pid);
        List<PropertyValue> propertyValues = propertyValueService.list(pid);
        List<Review> reviews = reviewService.list(pid);
        req.setAttribute("p",product);
        req.setAttribute("ppvs",propertyValues);
        req.setAttribute("rs",reviews);
        return "/page/fore/product/showProduct.jsp";
    }



}
