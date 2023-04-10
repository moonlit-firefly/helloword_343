package com.iweb.DAO.impl;

import com.iweb.DAO.EntityDAO;
import com.iweb.entity.Product;
import com.iweb.entity.ProductImage;
import com.iweb.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/1  23:10
 **/
public class ProductImageDAOImpl implements EntityDAO<ProductImage> {
    private static ProductDAOImpl pdi=new ProductDAOImpl();
    @Override
    public boolean add(ProductImage bean) {
        String sql="insert into img(url,pid) values(?,?)";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,bean.getUrl());
            ps.setInt(2,bean.getProduct().getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("ProductImageDAOImply"+bean.getId()+"add异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(ProductImage bean) {
        return delete(bean.getId());
    }
    /**根据id删除
     * @param id 待删除种类id
     * @return
     */
    public boolean delete(int id) {
        String sql="delete from img where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("ProductImageImply"+id+"delete异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ProductImage bean) {
        String sql="update img set url=?,pid=? where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,bean.getUrl());
            ps.setInt(2,bean.getProduct().getId());
            ps.setInt(3,bean.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("ProductImageDAOImply"+bean.getId()+"update异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ProductImage get(int id) {
        ProductImage productImage=null;
        String sql="select * from img where id=?";
        try(Connection c=JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                productImage=new ProductImage();
                productImage.setId(id);
                productImage.setUrl(rs.getString("url"));
                productImage.setProduct(pdi.get(rs.getInt("pid")));
            }
        }catch (Exception e){
            System.out.println("ProductImageDAOImpl.get("+id+")出现异常");
            e.printStackTrace();
        }
        return productImage;
    }

    @Override
    public List<ProductImage> list() {
        return list(0,5000000);
    }

    /**获取该产品的图品集合
     * @param product 待获取图片集合的产品
     * @return
     */
    public List<ProductImage> list(Product product) {
        List<ProductImage> productImages=new ArrayList<>();
        String sql="select * from img where pid=?";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,product.getId());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                ProductImage productImage=new ProductImage();
                productImage.setId(rs.getInt("id"));
                productImage.setUrl(rs.getString("url"));
                productImage.setProduct(product);
                productImages.add(productImage);
            }
        }catch (Exception e){
            System.out.println("ProductImageDAOImpl.list("+product.getName()+")出现异常");
            e.printStackTrace();
        }
        return productImages;
    }
    public List<ProductImage> list(int pid) {
        List<ProductImage> productImages=new ArrayList<>();
        String sql="select * from img where pid=?";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,pid);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                ProductImage productImage=new ProductImage();
                productImage.setId(rs.getInt("id"));
                productImage.setUrl(rs.getString("url"));
                productImage.setProduct(pdi.get(pid));
                productImages.add(productImage);
            }
        }catch (Exception e){
            System.out.println("ProductImageDAOImpl.list("+pid+")出现异常");
            e.printStackTrace();
        }
        return productImages;
    }

    @Override
    public List<ProductImage> list(int start, int count) {
        List<ProductImage> productImages=new ArrayList<>();
        String sql="select * from img limit ?,?";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                ProductImage productImage=new ProductImage();
                productImage.setId(rs.getInt("id"));
                productImage.setUrl(rs.getString("url"));
                productImage.setProduct(pdi.get(rs.getInt("pid")));
                productImages.add(productImage);
            }
        }catch (Exception e){
            System.out.println("ProductImageDAOImpl.list("+start+","+count+")出现异常");
            e.printStackTrace();
        }
        return productImages;
    }
}
