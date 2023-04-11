package com.iweb.service.impl;

import com.iweb.DAO.impl.OrderItemDAOImpl;
import com.iweb.entity.OrderItem;
import com.iweb.entity.User;
import com.iweb.service.OrderItemService;

import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/10  15:52
 **/
public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDAOImpl orderItemDAO=new OrderItemDAOImpl();
    @Override
    public List<OrderItem> list(User buyUser) {
        return orderItemDAO.list(buyUser);
    }

    @Override
    public void delete(Integer id) {
        orderItemDAO.delete(id);
    }
}
