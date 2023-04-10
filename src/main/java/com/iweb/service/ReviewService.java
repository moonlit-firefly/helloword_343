package com.iweb.service;

import com.iweb.entity.Review;

import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/9  20:16
 **/
public interface ReviewService {
    List<Review> list(Integer pid);
}
