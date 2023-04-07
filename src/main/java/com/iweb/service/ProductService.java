package com.iweb.service;

import com.iweb.entity.Category;
import com.iweb.entity.Product;
import com.iweb.util.Page;

import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/3  22:23
 **/
public interface ProductService {
    List<Product> list(int cid);
    List<Product> list(Page page);
    Product get(Integer id);
    void  add(Product product);
    Product  edit(int id);
    void delete(int id);
    void update(Product product);
}
