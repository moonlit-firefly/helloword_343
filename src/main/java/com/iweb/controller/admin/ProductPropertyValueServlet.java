package com.iweb.controller.admin;

import com.iweb.entity.PropertyValue;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/5  12:12
 **/
@WebServlet("/productPropertyValueServlet")
public class ProductPropertyValueServlet extends BaseBackServlet {
    public String list(HttpServletRequest req, HttpServletResponse resp) {
        int pid = Integer.parseInt(req.getParameter("id"));
        // service 调用  获取集合 请求存入
        List<PropertyValue> propertyValues = propertyValueService.list(pid);
        //将获取到的集合存入到请求中
        req.setAttribute("ppvs", propertyValues);
        req.setAttribute("pid", pid);
        //通过转发跳转到jsp页面 页面通过el表达式将数据渲染解析出来
        return "/page/admin/product/propertyValue/listProductProperty.jsp";
    }

    public String update(HttpServletRequest req, HttpServletResponse resp) {
        PropertyValue propertyValue = null;
        String value = req.getParameter("propertyValue");
        Integer pvid = Integer.parseInt(req.getParameter("pvid"));
        propertyValue = propertyValueService.get(pvid);
        propertyValue.setValue(value);
        propertyValueService.update(propertyValue);
        if (propertyValue == null) {
            return "%error";
        } else {
//            req.setAttribute("value", value);
            return "%success";
        }
    }
}
