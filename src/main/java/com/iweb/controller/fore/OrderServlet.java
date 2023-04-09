package com.iweb.controller.fore;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 陈郅治
 * @date 2023/4/9  14:17
 **/
@WebServlet(urlPatterns = "/orderServlet")
public class OrderServlet extends BaseForeServlet {
    public String cart(HttpServletRequest req, HttpServletResponse resp){
        //购物车管理

        return "/page/fore/cart/cartManager.jsp";
    }
    public String manager(HttpServletRequest req, HttpServletResponse resp){
        //订单管理

        return "/page/fore/order/orderManager.jsp";
    }
}
