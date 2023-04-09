package com.iweb.controller.fore;

import com.iweb.entity.Category;
import com.iweb.entity.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/9  12:15
 **/
@WebServlet(urlPatterns = "/mainPageServlet")
public class MainPageServlet extends BaseForeServlet {
    public String pageShow(HttpServletRequest req, HttpServletResponse resp){
        // service 调用  获取集合 请求存入
        List<Category> categories=categoryService.list();
        List<Product> products=productService.list(60);
        List<Product> productList=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            //取出前5个产品用于展示
            productList.add(products.get(i));
        }
        //将获取到的集合存入到请求中
        req.setAttribute("cs",categories);
        req.setAttribute("ps",productList);
        //通过转发跳转到jsp页面 页面通过el表达式将数据渲染解析出来
        return "/page/fore/mainPage/mainPage.jsp";
    }
}
