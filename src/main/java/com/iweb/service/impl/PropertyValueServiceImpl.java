package com.iweb.service.impl;

import com.iweb.DAO.impl.PropertyDAOImpl;
import com.iweb.DAO.impl.PropertyValueDAOImpl;
import com.iweb.entity.PropertyValue;
import com.iweb.service.PropertyValueService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/3  14:45
 **/
public class PropertyValueServiceImpl implements PropertyValueService {
    private static PropertyValueDAOImpl propertyValueDAO=new PropertyValueDAOImpl();
    private  List<PropertyValue> propertyValues=propertyValueDAO.list();
    @Override
    public List<PropertyValue> list(int id) {
        List<PropertyValue> propertyValueList=new ArrayList<>();
        PropertyDAOImpl propertyDAO=new PropertyDAOImpl();
        for (PropertyValue p:propertyValues) {
            if(p.getProduct().getId()==id){
                p.setProperty(propertyDAO.get(p.getProperty().getId()));
                propertyValueList.add(p);
            }
        }
        return propertyValueList;
    }

    @Override
    public void update(PropertyValue propertyValue) {
        propertyValueDAO.update(propertyValue);
    }

    @Override
    public PropertyValue get(int id) {
        return propertyValueDAO.get(id);
    }
}
