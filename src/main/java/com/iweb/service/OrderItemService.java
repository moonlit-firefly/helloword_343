package com.iweb.service;

import com.iweb.entity.OrderItem;
import com.iweb.entity.User;

import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/10  15:52
 **/
public interface OrderItemService {
    List<OrderItem> list(User buyUser);

    void delete(Integer id);
}
