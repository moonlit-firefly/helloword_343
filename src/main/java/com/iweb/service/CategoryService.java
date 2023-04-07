package com.iweb.service;


import com.iweb.entity.Category;
import com.iweb.util.Page;

import java.util.List;


/**
 * @author 陈郅治
 */
public interface CategoryService {
    List<Category> list();
    List<Category> list(Page page);
    Category get(Integer id);
    void  add(Category category);
    Category  edit(int id);
    void delete(int id);
    void update(Category category);
}
