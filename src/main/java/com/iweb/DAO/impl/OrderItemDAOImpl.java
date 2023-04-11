package com.iweb.DAO.impl;

import com.iweb.DAO.EntityDAO;
import com.iweb.entity.OrderItem;
import com.iweb.entity.Product;
import com.iweb.entity.User;
import com.iweb.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/1  23:04
 **/
public class OrderItemDAOImpl implements EntityDAO<OrderItem> {
    private static ProductDAOImpl pdi=new ProductDAOImpl();
    private static UserDAOImpl udi=new UserDAOImpl();
    private static OrderDAOImpl odi=new OrderDAOImpl();
    @Override
    public boolean add(OrderItem bean) {
        String sql="insert into orderitem(pid,oid,uid,number) values(?,?,?,?)";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,bean.getProduct().getId());
            ps.setInt(2,bean.getOrder().getId());
            ps.setInt(3,bean.getUser().getId());
            ps.setInt(4,bean.getNumber());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("OrderItemImply订单详情"+bean.getId()+"add异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(OrderItem bean) {
        return delete(bean.getId());
    }

    /**根据id删除
     * @param id 待删除种类id
     * @return
     */
    public boolean delete(int id) {
        String sql="delete from orderitem where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("OrderItemDAOImply"+"订单详情"+id+"delete异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(OrderItem bean) {
        String sql="update orderitem set pid=?,oid=?,uid=?,number=? where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,bean.getProduct().getId());
            ps.setInt(2,bean.getOrder().getId());
            ps.setInt(3,bean.getUser().getId());
            ps.setInt(4,bean.getNumber());
            ps.setInt(5,bean.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("OrderItemDAOImply"+"订单详情"+bean.getId()+"update异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public OrderItem get(int id) {
        OrderItem orderItem=null;
        String sql="select * from orderitem where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                orderItem=new OrderItem();
                orderItem.setId(id);
                //调用方法充实对象属性
                orderItem.setNumber(rs.getInt("number"));
                orderItem.setOrder(odi.get(rs.getInt("oid")));
                orderItem.setProduct(pdi.get(rs.getInt("pid")));
                orderItem.setUser(udi.get(rs.getInt("uid")));
            }
        }catch (Exception e){
            System.out.println("OrderItemDAOImply.get("+id+")出现异常");
            e.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public List<OrderItem> list() {
        return list(0,5000000);
    }

    @Override
    public List<OrderItem> list(int start, int count) {
        //获取分页查询得到的对象集合
        List<OrderItem> orderItems=new ArrayList<>();
        String sql="select * from orderitem limit ?,?";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                OrderItem orderItem=new OrderItem();
                orderItem.setNumber(rs.getInt("number"));
                orderItem.setOrder(odi.get(rs.getInt("oid")));
                orderItem.setProduct(pdi.get(rs.getInt("pid")));
                orderItem.setUser(udi.get(rs.getInt("uid")));
                orderItems.add(orderItem);
            }
        }catch (Exception e){
            System.out.println("OrderItemDAOImply.list("+start+","+count+")出现异常");
            e.printStackTrace();
        }
        return orderItems;
    }

    /**所有关于该产品的订单详情集合
     * @param product 需要知道产品订单信息的产品
     * @return
     */
    public List<OrderItem> list(Product product) {
        List<OrderItem> orderItems=new ArrayList<>();
        String sql="select * from orderitem where pid=?";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,product.getId());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                OrderItem orderItem=new OrderItem();
                orderItem.setNumber(rs.getInt("number"));
                orderItem.setOrder(odi.get(rs.getInt("oid")));
                orderItem.setProduct(product);
                orderItem.setUser(udi.get(rs.getInt("uid")));
                orderItems.add(orderItem);
            }
        }catch (Exception e){
            System.out.println("OrderItemDAOImply.list("+product.getName()+")出现异常");
            e.printStackTrace();
        }
        return orderItems;
    }

    /**用户订单详情集合
     * @param user 用户
     * @return
     */
    public List<OrderItem> list(User user) {
        List<OrderItem> orderItems=new ArrayList<>();
        String sql="select * from orderitem where uid=?";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,user.getId());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                OrderItem orderItem=new OrderItem();
                orderItem.setNumber(rs.getInt("number"));
                orderItem.setOrder(odi.get(rs.getInt("oid")));
                orderItem.setProduct(pdi.get(rs.getInt("pid")));
                orderItem.setUser(user);
                orderItems.add(orderItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderItems;
    }
}
