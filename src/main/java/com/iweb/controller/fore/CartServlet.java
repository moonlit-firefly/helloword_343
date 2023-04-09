package com.iweb.controller.fore;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 陈郅治
 * @date 2023/4/9  13:29
 **/
@WebServlet(urlPatterns = "/cartServlet")
public class CartServlet extends BaseForeServlet {
    public String add(HttpServletRequest req, HttpServletResponse resp){

        return "@success";
    }
}
