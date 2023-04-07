package com.iweb.service.impl;

import com.iweb.DAO.impl.OrderDAOImpl;
import com.iweb.entity.Order;
import com.iweb.service.OrderService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/5  20:39
 **/
public class OrderServiceImpl implements OrderService {
    private OrderDAOImpl orderDAO=new OrderDAOImpl();
    @Override
    public List<Order> list() {
        return orderDAO.list();
    }

    @Override
    public List<Order> list(int uid) {
        List<Order> orders=new ArrayList<>();
        for (Order o:orderDAO.list()) {
            if(o.getUser().getId()==uid){
                orders.add(o);
            }
        }
        return orders;
    }

    @Override
    public Order edit(Order order) {
        return null;
    }

    @Override
    public void update(Order order) {

    }
}
