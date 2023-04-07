package com.iweb.DAO.impl;

import com.iweb.DAO.EntityDAO;
import com.iweb.entity.Product;
import com.iweb.entity.ProductImage;
import com.iweb.entity.Review;
import com.iweb.util.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/1  22:41
 **/
public class ReviewDAOImpl implements EntityDAO<Review> {
    private static ProductDAOImpl pdi=new ProductDAOImpl();
    private static UserDAOImpl udi=new UserDAOImpl();
    @Override
    public boolean add(Review bean) {
        String sql="insert into review(content,uid,pid,createDate) values(?,?,?,?)";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,bean.getContent());
            ps.setInt(2,bean.getUser().getId());
            ps.setInt(3,bean.getProduct().getId());
            ps.setDate(4, (Date) bean.getCreateDate());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("ReviewDAOImpl"+bean.getId()+"add异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Review bean) {
        return delete(bean.getId());
    }
    /**根据id删除
     * @param id 待删除种类id
     * @return
     */
    public boolean delete(int id) {
        String sql="delete from review where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("ReviewDAOImpl"+id+"delete异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Review bean) {
        String sql="update review set content=?,uid=?,pid=?,createDate=? " +
                "where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,bean.getContent());
            ps.setInt(2,bean.getUser().getId());
            ps.setInt(3,bean.getProduct().getId());
            ps.setDate(4, (Date) bean.getCreateDate());
            ps.setInt(5,bean.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("ReviewDAOImpl"+bean.getId()+"update异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Review get(int id) {
        Review review=null;
        String sql="select * from review where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                review=new Review();
                review.setId(id);
                review.setContent(rs.getString("content"));
               // review.setUser(udi.get(rs.getInt("uid")));
               // review.setProduct(pdi.get(rs.getInt("pid")));
                review.setCreateDate(rs.getDate("createDate"));
            }
        }catch (Exception e){
            System.out.println("ReviewDAOImpl.get("+id+")出现异常");
            e.printStackTrace();
        }
        return review;
    }

    @Override
    public List<Review> list() {
        return list(0,5000000);
    }
    @Override
    public List<Review> list(int start, int count) {
        List<Review> reviews=new ArrayList<>();
        String sql="select * from review limit ?,?";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Review review=new Review();
                review.setId(rs.getInt("id"));
                review.setContent(rs.getString("content"));
               // review.setUser(udi.get(rs.getInt("uid")));
                //review.setProduct(pdi.get(rs.getInt("pid")));
                review.setCreateDate(rs.getDate("createDate"));
                reviews.add(review);
            }
        }catch (Exception e){
            System.out.println("ReviewDAOImpl.list("+start+","+count+")出现异常");
            e.printStackTrace();
        }
        return reviews;
    }

    /**获取当前产品的所有评价集合
     * @param product 被评价的产品
     * @return 当前产品的所有评价集合
     */
    public List<Review> list(Product product) {
        return list(product,0,5000000);
    }


    /**分页获取当前产品的评价集合
     * @param product 被评价的产品
     * @param start  从第几个评价开始
     * @param count 一页的评价数
     * @return
     */
    public List<Review> list(Product product,int start, int count) {
        List<Review> reviews=new ArrayList<>();
        String sql="select * from review where pid=? limit ?,?";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,product.getId());
            ps.setInt(2,start);
            ps.setInt(2,count);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Review review=new Review();
                review.setId(rs.getInt("id"));
                review.setContent(rs.getString("content"));
//                review.setUser(udi.get(rs.getInt("uid")));
                review.setProduct(product);
                review.setCreateDate(rs.getDate("createDate"));
                reviews.add(review);
            }
        }catch (Exception e){
            System.out.println("ReviewDAOImpl.list("+start+","+count+")出现异常");
            e.printStackTrace();
        }
        return reviews;
    }

}
