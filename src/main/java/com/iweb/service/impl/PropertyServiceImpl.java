package com.iweb.service.impl;

import com.iweb.DAO.impl.PropertyDAOImpl;
import com.iweb.entity.Property;
import com.iweb.service.PropertyService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/3  14:43
 **/
public class PropertyServiceImpl implements PropertyService {
    public PropertyDAOImpl propertyDAO=new PropertyDAOImpl();
    @Override
    public List<Property> list(int id) {
        List<Property> properties=propertyDAO.list();
        List<Property> propertyList=new ArrayList<>();
        for (Property p:properties) {
            if(p.getCategory().getId()==id){
                propertyList.add(p);
            }
        }
        return propertyList;
    }

    @Override
    public Property get(int id) {
        return propertyDAO.get(id);
    }

    @Override
    public void add(Property property) {
        propertyDAO.add(property);
    }

    @Override
    public Property edit(int id) {
        return propertyDAO.get(id);
    }

    @Override
    public void delete(int id) {
        propertyDAO.delete(id);
    }

    @Override
    public void update(Property property) {
        propertyDAO.update(property);
    }
}
