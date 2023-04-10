package com.iweb.service.impl;

import com.iweb.DAO.impl.ProductDAOImpl;
import com.iweb.DAO.impl.ReviewDAOImpl;
import com.iweb.entity.Review;
import com.iweb.service.ReviewService;

import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/9  20:16
 **/
public class ReviewServiceImpl implements ReviewService {
    public static ReviewDAOImpl reviewDAO=new ReviewDAOImpl();
    @Override
    public List<Review> list(Integer pid) {
        return reviewDAO.list(new ProductDAOImpl().get(pid));
    }
}
