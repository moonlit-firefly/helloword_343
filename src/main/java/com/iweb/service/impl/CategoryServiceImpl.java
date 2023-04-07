package com.iweb.service.impl;


import com.iweb.DAO.EntityDAO;
import com.iweb.DAO.impl.CategoryDAOImpl;
import com.iweb.entity.Category;
import com.iweb.service.CategoryService;
import com.iweb.util.Page;

import java.util.List;


/**
 * @author 陈郅治
 */
public class CategoryServiceImpl implements CategoryService {
    CategoryDAOImpl categoryDAO = new CategoryDAOImpl();

    @Override
    public List<Category> list() {
        //调用DAO方法获取数据
        return categoryDAO.list();
    }

    @Override
    public List<Category> list(Page page) {
        //调用dao的方法 获取表记录总数
        int total = categoryDAO.list().size();
        page.calculateLast(total);
        return categoryDAO.list(page.getStart(),page.getCount());
    }

    @Override
    public Category get(Integer id) {
        return categoryDAO.get(id);
    }

    @Override
    public void add(Category category) {
        categoryDAO.add(category);
    }

    @Override
    public Category edit(int id) {
        return categoryDAO.get(id);
    }

    @Override
    public void delete(int id) {
        categoryDAO.delete(id);
    }

    @Override
    public void update(Category category) {
        categoryDAO.update(category);
    }
}
