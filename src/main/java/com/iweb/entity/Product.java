package com.iweb.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author 陈郅治
 */
@Data
public class Product {
    private int id;
    private String name;
    private String subTitle;
    private float originalPrice;
    private float promotePrice;
    private int stock;
    private Category category;
    private Date createDate;
    private int reviewCount;//评价数量
    private int saleCount;// 销量
    private List<ProductImage> images;

    public void setImages(List<ProductImage> productImages){
        images=productImages;
    }
}
