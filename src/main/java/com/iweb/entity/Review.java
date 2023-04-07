package com.iweb.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 陈郅治
 */
@Data
public class Review {
    private int id;
    private String content;
    private User user;
    private Product product;
    private Date createDate;
}
