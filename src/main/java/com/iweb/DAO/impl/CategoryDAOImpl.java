package com.iweb.DAO.impl;

import com.iweb.DAO.EntityDAO;
import com.iweb.entity.Category;
import com.iweb.entity.Product;
import com.iweb.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/1  21:35
 **/
public class CategoryDAOImpl implements EntityDAO<Category> {
    private static ProductDAOImpl pdi=new ProductDAOImpl();

    @Override
    public boolean add(Category bean) {
        String sql="insert into category(name) values(?)";
        try(Connection c=JDBCUtil.getConnection();
        PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,bean.getName());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("CategoryDAOImply"+bean.getName()+"add异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Category bean) {
       return delete(bean.getId());
    }

    /**根据id删除
     * @param id 待删除种类id
     * @return
     */
    public boolean delete(int id) {
        String sql="delete from category where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("CategoryDAOImply"+"category"+id+"delete异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Category bean) {
        String sql="update category set name=? where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,bean.getName());
            ps.setInt(2,bean.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("CategoryDAOImply"+bean.getName()+"update异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Category get(int id) {
        Category category=null;
        String sql="select * from category where id=?";
        try(Connection c=JDBCUtil.getConnection();
        PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                category=new Category();
                category.setId(id);
                category.setName(rs.getString("name"));
                category.setProducts(pdi.list(category));
            }
        }catch (Exception e){
            System.out.println("CategoryDAOImpl.get("+id+")出现异常");
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public List<Category> list() {
        return list(0,5000000);
    }

    @Override
    public List<Category> list(int start, int count) {
        //获取分页查询得到的种类对象集合
        List<Category> categories=new ArrayList<>();
        String sql="select * from category limit ?,?";
        try(Connection c= JDBCUtil.getConnection();
        PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Category category=new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                //调用产品实现类的方法获取产品集合
                List<Product> products=pdi.list(category);
                category.setProducts(products);
                categories.add(category);
            }
        }catch (Exception e){
            System.out.println("CategoryDAOImpl.list("+start+","+count+")出现异常");
            e.printStackTrace();
        }
        return categories;
    }
}
