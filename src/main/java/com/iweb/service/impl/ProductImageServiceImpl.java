package com.iweb.service.impl;

import com.iweb.DAO.impl.ProductImageDAOImpl;
import com.iweb.entity.ProductImage;
import com.iweb.service.ProductImageService;
import com.iweb.util.Page;

import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/4  11:02
 **/
public class ProductImageServiceImpl implements ProductImageService {
    public static ProductImageDAOImpl productImageDAO=new ProductImageDAOImpl();
    @Override
    public List<ProductImage> list(int pid) {
        return productImageDAO.list(pid);
    }

    @Override
    public List<ProductImage> list(Page page) {
        int total = productImageDAO.list().size();
        page.calculateLast(total);
        return productImageDAO.list(page.getStart(),page.getCount());
    }

    @Override
    public ProductImage get(Integer id) {
        return productImageDAO.get(id);
    }

    @Override
    public void add(ProductImage productImage) {
        productImageDAO.add(productImage);
    }

    @Override
    public ProductImage edit(int id) {
        return productImageDAO.get(id);
    }

    @Override
    public void delete(int id) {
        productImageDAO.delete(id);
    }

    @Override
    public void update(ProductImage productImage) {
        productImageDAO.update(productImage);
    }
}
