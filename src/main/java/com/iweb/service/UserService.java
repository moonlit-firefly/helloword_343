package com.iweb.service;

import com.iweb.entity.User;

import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/3  9:41
 **/
public interface UserService {
    boolean login(User user);
    List<User> list();

}
