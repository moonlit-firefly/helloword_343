package com.iweb.service;

import com.iweb.entity.Product;
import com.iweb.entity.ProductImage;
import com.iweb.util.Page;

import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/4  11:01
 **/
public interface ProductImageService {
    List<ProductImage> list(int pid);
    List<ProductImage> list(Page page);
    ProductImage get(Integer id);
    void  add(ProductImage productImage);
    ProductImage  edit(int id);
    void delete(int id);
    void update(ProductImage productImage);
}
