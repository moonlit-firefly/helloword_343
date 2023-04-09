package com.iweb.controller.fore;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 陈郅治
 * @date 2023/4/9  13:34
 **/
@WebServlet(urlPatterns = "/productViewServlet")
public class ProductViewServlet extends BaseForeServlet {
    public String search(HttpServletRequest req, HttpServletResponse resp){
        //模糊搜索商品后跳转到商品展示页面

        return "/page/fore/product/listProduct.jsp";
    }
    public String listByCategory(HttpServletRequest req, HttpServletResponse resp){
        //模糊搜索商品后跳转到商品展示页面

        return "/page/fore/product/listProduct.jsp";
    }
    public String show(HttpServletRequest req, HttpServletResponse resp){
        //展示商品详情页面

        return "/page/fore/product/showProduct.jsp";
    }



}
