package com.iweb.service;

import com.iweb.entity.Property;

import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/3  14:43
 **/
public interface PropertyService {
    /**获取同种类属性集合
     * @param id 种类id
     * @return
     */
    List<Property> list(int id);

    Property get(int id);

    void add(Property property);

    Property edit(int id);

    void delete(int id);

    void update(Property property);
}
