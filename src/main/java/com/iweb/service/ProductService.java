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
    /**同种类产品集合
     * @param cid 种类id
     * @return
     */
    List<Product> list(int cid);

    /**模糊查询产品集合
     * @param value 模糊查询值
     * @return
     */
    List<Product> list(String value);

    /**分页查询
     * @param page 页面对象
     * @return
     */
    List<Product> list(Page page);

    /**根据id获取产品对象
     * @param id 产品id
     * @return
     */
    Product get(Integer id);

    void  add(Product product);

    Product  edit(int id);

    void delete(int id);

    void update(Product product);
}
