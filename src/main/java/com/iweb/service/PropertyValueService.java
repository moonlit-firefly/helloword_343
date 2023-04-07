package com.iweb.service;

import com.iweb.entity.PropertyValue;

import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/3  14:44
 **/
public interface PropertyValueService {
    List<PropertyValue> list(int id);

    void update(PropertyValue propertyValue);

    PropertyValue get(int id);
}
