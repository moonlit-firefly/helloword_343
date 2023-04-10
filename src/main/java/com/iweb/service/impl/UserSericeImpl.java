package com.iweb.service.impl;

import com.iweb.DAO.impl.UserDAOImpl;
import com.iweb.entity.User;
import com.iweb.service.UserService;

import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/3  9:42
 **/
public class UserSericeImpl implements UserService {
    public UserDAOImpl userDAO=new UserDAOImpl();
    @Override
    public boolean login(User user) {
        if(user.getName()==null||user.getPassword()==null){
            return false;
        }
        User confirmUser= userDAO.get(user);
        if(confirmUser==null){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public List<User> list() {
        return userDAO.list();
    }

    @Override
    public void add(User user) {
        userDAO.add(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }
}
