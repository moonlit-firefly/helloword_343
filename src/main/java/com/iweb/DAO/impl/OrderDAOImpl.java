package com.iweb.DAO.impl;

import com.iweb.DAO.EntityDAO;
import com.iweb.entity.Order;
import com.iweb.util.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/2  12:26
 **/
public class OrderDAOImpl implements EntityDAO<Order> {
    private static UserDAOImpl udi=new UserDAOImpl();
    @Override
    public boolean add(Order bean) {
        String sql="insert into " +
                "order_(orderCode,address,post,receiver,mobile,userMessage,createDate,payDate,deliveryDate,confirmDate,uid,status) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,bean.getOrderCode());
            ps.setString(2,bean.getAddress());
            ps.setString(3,bean.getPost());
            ps.setString(4,bean.getReceiver());
            ps.setString(5,bean.getMobile());
            ps.setString(6,bean.getUserMessage());

            ps.setDate(7, (Date) bean.getCreateDate());
            ps.setDate(8, (Date) bean.getPayDate());
            ps.setDate(9, (Date) bean.getDeliveryDate());
            ps.setDate(10, (Date) bean.getConfirmDate());

            ps.setInt(11,bean.getUser().getId());
            ps.setString(12,bean.getStatusDesc());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("OrderDAOImply订单"+bean.getId()+"add异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Order bean) {
        return delete(bean.getId());
    }

    /**根据id删除
     * @param id 待删除种类id
     * @return
     */
    public boolean delete(int id) {
        String sql="delete from order_ where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("OrderDAOImply"+"订单"+id+"delete异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Order bean) {
        String sql="update order_ set " +
                "orderCode=?,address=?,post=?,receiver=?,mobile=?,userMessage=?," +
                "createDate=?,payDate=?,deliveryDate=?,confirmDate=?,uid=?,status=? " +
                "where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,bean.getOrderCode());
            ps.setString(2,bean.getAddress());
            ps.setString(3,bean.getPost());
            ps.setString(4,bean.getReceiver());
            ps.setString(5,bean.getMobile());
            ps.setString(6,bean.getUserMessage());

            ps.setDate(7, (Date) bean.getCreateDate());
            ps.setDate(8, (Date) bean.getPayDate());
            ps.setDate(9, (Date) bean.getDeliveryDate());
            ps.setDate(10, (Date) bean.getConfirmDate());

            ps.setInt(11,bean.getUser().getId());
            ps.setString(12,bean.getStatusDesc());
            ps.setInt(13,bean.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("OrderDAOImply"+"订单"+bean.getId()+"update异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Order get(int id) {
        Order order=null;
        String sql="select * from order_ where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                order=new Order();
                order.setId(id);
                //调用方法充实对象属性
                order.setOrderCode(rs.getString("orderCode"));
                order.setAddress(rs.getString("address"));
                order.setPost(rs.getString("post"));
                order.setReceiver(rs.getString("receiver"));
                order.setMobile(rs.getString("mobile"));
                order.setUserMessage(rs.getString("userMessage"));
                order.setCreateDate(rs.getDate("createDate"));
                order.setPayDate(rs.getDate("payDate"));
                order.setDeliveryDate(rs.getDate("deliveryDate"));
                order.setConfirmDate(rs.getDate("confirmDate"));
                order.setUser(udi.get(rs.getInt("uid")));
                order.setStatus(rs.getString("status"));
            }
        }catch (Exception e){
            System.out.println("OrderDAOImply.get("+id+")出现异常");
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> list() {
        return list(0,50000000);
    }

    @Override
    public List<Order> list(int start, int count) {
        //获取分页查询得到的对象集合
        List<Order> orders=new ArrayList<>();
        String sql="select * from order_ limit ?,?";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Order order=new Order();
                order.setId(rs.getInt("id"));
                order.setOrderCode(rs.getString("orderCode"));
                order.setAddress(rs.getString("address"));
                order.setPost(rs.getString("post"));
                order.setReceiver(rs.getString("receiver"));
                order.setMobile(rs.getString("mobile"));
                order.setUserMessage(rs.getString("userMessage"));
                order.setCreateDate(rs.getDate("createDate"));
                order.setPayDate(rs.getDate("payDate"));
                order.setDeliveryDate(rs.getDate("deliveryDate"));
                order.setConfirmDate(rs.getDate("confirmDate"));
                order.setUser(udi.get(rs.getInt("uid")));
                order.setStatus(rs.getString("status"));
                orders.add(order);
            }
        }catch (Exception e){
            System.out.println("OrderItemDAOImply.list("+start+","+count+")出现异常");
            e.printStackTrace();
        }
        return orders;
    }
}
