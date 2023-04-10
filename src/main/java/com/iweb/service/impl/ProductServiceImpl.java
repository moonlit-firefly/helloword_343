package com.iweb.service.impl;

import com.iweb.DAO.impl.ProductDAOImpl;
import com.iweb.entity.Product;
import com.iweb.service.ProductService;
import com.iweb.util.Page;

import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/3  22:25
 **/
public class ProductServiceImpl implements ProductService {
    public static ProductDAOImpl productDAO=new ProductDAOImpl();
    @Override
    public List<Product> list(int cid) {
        return productDAO.list(cid);
    }

    @Override
    public List<Product> list(String value) {
        return productDAO.list(value);
    }

    @Override
    public List<Product> list(Page page) {
        int total = productDAO.list().size();
        page.calculateLast(total);
        return productDAO.list(page.getStart(),page.getCount());
    }

    @Override
    public Product get(Integer id) {
        return productDAO.get(id);
    }

    @Override
    public void add(Product product) {
        productDAO.add(product);
    }

    @Override
    public Product edit(int id) {
        return productDAO.get(id);
    }

    @Override
    public void delete(int id) {
        productDAO.delete(id);
    }

    @Override
    public void update(Product product) {
        productDAO.update(product);
    }
}
