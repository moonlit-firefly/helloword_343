package com.iweb.controller.fore;

import com.iweb.entity.Order;
import com.iweb.entity.OrderItem;
import com.iweb.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/9  14:17
 **/
@WebServlet(urlPatterns = "/orderServlet")
public class OrderServlet extends BaseForeServlet {
    public String cart(HttpServletRequest req, HttpServletResponse resp){
        //购物车管理
        User buyUser=(User) req.getSession().getAttribute("buyUser");
        List<OrderItem> carts=new ArrayList<>();
        for (OrderItem c:orderItemService.list(buyUser)) {
            if(c.getOrder().getId()==-1){
                carts.add(c);
            }
        }
        req.setAttribute("ps", carts);
        return "/page/fore/cart/cartManager.jsp";
    }
    public String manager(HttpServletRequest req, HttpServletResponse resp){
        //订单管理

        return "/page/fore/order/orderManager.jsp";
    }
}
