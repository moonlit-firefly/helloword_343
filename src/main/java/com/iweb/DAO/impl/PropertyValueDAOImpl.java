package com.iweb.DAO.impl;

import com.iweb.DAO.EntityDAO;
import com.iweb.entity.Product;
import com.iweb.entity.Property;
import com.iweb.entity.PropertyValue;
import com.iweb.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/2  14:38
 **/
public class PropertyValueDAOImpl implements EntityDAO<PropertyValue> {
    private static ProductDAOImpl pdi=new ProductDAOImpl();
    private static PropertyDAOImpl ptdi=new PropertyDAOImpl();
    @Override
    public boolean add(PropertyValue bean) {
        String sql="insert into Propertyvalue(pid,ptid,value) values(?,?,?)";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,bean.getProduct().getId());
            ps.setInt(2,bean.getProperty().getId());
            ps.setString(3,bean.getValue());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("PropertyValueDAOImply"+bean.getId()+"add异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(PropertyValue bean) {
        return delete(bean.getId());
    }
    /**根据id删除
     * @param id 待删除种类id
     * @return
     */
    public boolean delete(int id) {
        String sql="delete from propertyvalue where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("PropertyValueDAOImply"+id+"delete异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(PropertyValue bean) {
        String sql="update propertyvalue set pid=?,ptid=?,value=? where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,bean.getProduct().getId());
            ps.setInt(2,bean.getProperty().getId());
            ps.setString(3,bean.getValue());
            ps.setInt(4,bean.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("PropertyValueDAOImply"+bean.getId()+"update异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PropertyValue get(int id) {
        PropertyValue propertyValue=null;
        String sql="select * from propertyvalue where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                propertyValue=new PropertyValue();
                propertyValue.setId(id);
                propertyValue.setProduct(pdi.get(rs.getInt("pid")));
                propertyValue.setProperty(ptdi.get(rs.getInt("ptid")));
                propertyValue.setValue(rs.getString("value"));
            }
        }catch (Exception e){
            System.out.println("PropertyValueDAOImply.get("+id+")出现异常");
            e.printStackTrace();
        }
        return propertyValue;
    }

    @Override
    public List<PropertyValue> list() {
        return list(0,5000000);
    }
    @Override
    public List<PropertyValue> list(int start, int count) {
        List<PropertyValue> propertyValues=new ArrayList<>();
        String sql="select * from propertyvalue limit ?,?";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                PropertyValue propertyValue=new PropertyValue();
                propertyValue.setId(rs.getInt("id"));
//                propertyValue.setProduct(pdi.get(rs.getInt("pid")));
                Product product=new Product();
                product.setId(rs.getInt("pid"));
                propertyValue.setProduct(product);
//                propertyValue.setProperty(ptdi.get(rs.getInt("ptid")));
                Property property=new Property();
                property.setId(rs.getInt("ptid"));
                propertyValue.setProperty(property);
                propertyValue.setValue(rs.getString("value"));
                propertyValues.add(propertyValue);
            }
        }catch (Exception e){
            System.out.println("PropertyValueDAOImply.list("+start+","+count+")出现异常");
            e.printStackTrace();
        }
        return propertyValues;
    }
}
