package com.iweb.service;

import com.iweb.entity.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/5  20:30
 **/
public interface OrderService {
    List<Order> list();
    List<Order> list(int uid);
    Order edit(Order order);
    void update(Order order);
}
