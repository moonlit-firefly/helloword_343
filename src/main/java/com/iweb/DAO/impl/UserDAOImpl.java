package com.iweb.DAO.impl;

import com.iweb.DAO.EntityDAO;
import com.iweb.entity.User;
import com.iweb.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/2  12:26
 **/
public class UserDAOImpl implements EntityDAO<User> {
    @Override
    public boolean add(User bean) {
        String sql="insert into user(name,password) values(?,?)";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,bean.getName());
            ps.setString(2,bean.getPassword());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("UserDAOImpl"+bean.getId()+"add异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(User bean) {
        return delete(bean.getId());
    }
    /**根据id删除
     * @param id 待删除对象id
     * @return
     */
    public boolean delete(int id) {
        String sql="delete from user where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("UserDAOImpl"+id+"delete异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(User bean) {
        String sql="update user set name=?,password=? where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,bean.getName());
            ps.setString(2,bean.getPassword());
            ps.setInt(3,bean.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("UserDAOImpl"+bean.getId()+"update异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User get(int id) {
        User user=null;
        String sql="select * from user where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                user=new User();
                user.setId(id);
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
        }catch (Exception e){
            System.out.println("UserDAOImpl.get("+id+")出现异常");
            e.printStackTrace();
        }
        return user;
    }

    public User get(User user){
        User user1=null;
        String sql="select * from user where name=? and password=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                user1 = new User();
                user.setId(rs.getInt("id"));
                user1.setId(rs.getInt("id"));
                user1.setName(rs.getString("name"));
                user1.setPassword(rs.getString("password"));
            }
        }catch (Exception e){
            System.out.println("UserDAOImpl.get("+user.getName()+")出现异常");
            e.printStackTrace();
        }
        return user1;
    }

    @Override
    public List<User> list() {
        return list(0,5000000);
    }

    @Override
    public List<User> list(int start, int count) {
        List<User> users=new ArrayList<>();
        String sql="select * from user limit ?,?";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        }catch (Exception e){
            System.out.println("ProductImageDAOImpl.list("+start+","+count+")出现异常");
            e.printStackTrace();
        }
        return users;
    }
}
