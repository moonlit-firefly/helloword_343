package com.iweb.DAO.impl;

import com.iweb.DAO.EntityDAO;
import com.iweb.entity.Category;
import com.iweb.entity.Property;
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
public class PropertyDAOImpl implements EntityDAO<Property> {
    private static CategoryDAOImpl cdi = new CategoryDAOImpl();
    @Override
    public boolean add(Property bean) {
        String sql="insert into property(cid,name) values(?,?)";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,bean.getCategory().getId());
            ps.setString(2,bean.getName());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("PropertyDAOImply"+bean.getId()+"add异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Property bean) {
        return delete(bean.getId());
    }
    /**根据id删除
     * @param id 待删除种类id
     * @return
     */
    public boolean delete(int id) {
        String sql="delete from property where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("PropertyImply"+id+"delete异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Property bean) {
        String sql="update property set cid=?,name=? where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,bean.getCategory().getId());
            ps.setString(2,bean.getName());
            ps.setInt(3,bean.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("PropertyDAOImply"+bean.getId()+"update异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Property get(int id) {
        Property property=null;
        String sql="select * from property where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                property=new Property();
                property.setId(id);
                property.setName(rs.getString("name"));
                property.setCategory(cdi.get(rs.getInt("cid")));
            }
        }catch (Exception e){
            System.out.println("PropertyDAOImpl.get("+id+")出现异常");
            e.printStackTrace();
        }
        return property;
    }

    @Override
    public List<Property> list() {
        return list(0,5000000);
    }

    @Override
    public List<Property> list(int start, int count) {
        List<Property> properties=new ArrayList<>();
        String sql="select * from property limit ?,?";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Property property=new Property();
                property.setId(rs.getInt("id"));
                property.setName(rs.getString("name"));
                //property.setCategory(cdi.get(rs.getInt("cid")));
                Category category=new Category();
                category.setId(rs.getInt("cid"));
                property.setCategory(category);
                properties.add(property);
            }
        }catch (Exception e){
            System.out.println("ProductImageDAOImpl.list("+start+","+count+")出现异常");
            e.printStackTrace();
        }
        return properties;
    }
}
