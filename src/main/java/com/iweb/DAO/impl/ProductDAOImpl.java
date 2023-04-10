package com.iweb.DAO.impl;

import com.iweb.DAO.EntityDAO;
import com.iweb.entity.Category;
import com.iweb.entity.Product;
import com.iweb.entity.ProductImage;
import com.iweb.util.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/4/1  22:16
 **/
public class ProductDAOImpl implements EntityDAO<Product> {
    private static CategoryDAOImpl cdi = new CategoryDAOImpl();
    private static ReviewDAOImpl rdi = new ReviewDAOImpl();
    private static OrderItemDAOImpl oidi = new OrderItemDAOImpl();
    private static ProductImageDAOImpl pidi = new ProductImageDAOImpl();

    @Override
    public boolean add(Product bean) {
        String sql = "insert into product(name,subTitle,originalPrice,promotePrice,stock,cid,createDate) " +
                "values(?,?,?,?,?,?,?)";
        try (Connection c = JDBCUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getSubTitle());
            ps.setFloat(3, bean.getOriginalPrice());
            ps.setFloat(4, bean.getPromotePrice());
            ps.setInt(5, bean.getStock());
            ps.setInt(6, bean.getCategory().getId());
            ps.setDate(7, (Date) bean.getCreateDate());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("ProductDAOImply" + bean.getName() + "add异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Product bean) {
        return delete(bean.getId());
    }

    /**
     * 根据id删除
     *
     * @param id 待删除id
     * @return
     */
    public boolean delete(int id) {
        String sql = "delete from product where id=?";
        try (Connection c = JDBCUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("ProductDAOImply" + "product" + id + "delete异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Product bean) {
        String sql = "update product set name=?,subTitle=?,originalPrice=?," +
                "promotePrice=?,stock=?,cid=?,createDate=? where id=?";
        try (Connection c = JDBCUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getSubTitle());
            ps.setFloat(3, bean.getOriginalPrice());
            ps.setFloat(4, bean.getPromotePrice());
            ps.setInt(5, bean.getStock());
            ps.setInt(6, bean.getCategory().getId());
            ps.setDate(7, (Date) bean.getCreateDate());
            ps.setInt(8, bean.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("ProductDAOImply" + bean.getName() + "update异常");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product get(int id) {
        Product product = null;
        String sql = "select * from product where id=?";
        try (Connection c = JDBCUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(id);
                product.setName(rs.getString("name"));
                product.setSubTitle(rs.getString("subTitle"));
                product.setOriginalPrice(rs.getFloat("originalPrice"));
                product.setPromotePrice(rs.getFloat("promotePrice"));
                product.setStock(rs.getInt("stock"));
                product.setCategory(cdi.get(rs.getInt("cid")));
                product.setCreateDate(rs.getDate("createDate"));
                product.setReviewCount(rdi.list(product).size());
                product.setSaleCount(oidi.list(product).size());
                List<ProductImage> productImages=pidi.list(product);
                product.setImages(productImages);
                if(productImages.size()==0){
                    //如果该产品没有照片，存入默认图片
                    ProductImage productImage=new ProductImage();
                    productImage.setUrl("http://39.106.106.39:8888/voice/20230404115600_0.png");
                    product.getImages().add(productImage);
                }
            }
        } catch (Exception e) {
            System.out.println("ProductDAOImpl.get(" + id + ")出现异常");
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> list() {
        return list(0,50000000);
    }

    /**
     * 从写list方法 获取同类产品集合
     *
     * @param category 该种类对象
     * @return同类产品集合
     */
    public List<Product> list(Category category) {
        List<Product> products = new ArrayList<>();
        String sql = "select * from product where cid=?";
        try (Connection c = JDBCUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, category.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setSubTitle(rs.getString("subTitle"));
                product.setOriginalPrice(rs.getFloat("originalPrice"));
                product.setPromotePrice(rs.getFloat("promotePrice"));
                product.setStock(rs.getInt("stock"));
                //根据id获取完整的种类对象
                product.setCategory(category);
                product.setCreateDate(rs.getDate("createDate"));
                //调用xxxximpl的方法计算评价数量,销量
                product.setReviewCount(rdi.list(product).size());
                product.setSaleCount(oidi.list(product).size());
                List<ProductImage> productImages=pidi.list(product);
                product.setImages(productImages);
                if(productImages.size()==0){
                    //如果该产品没有照片，存入默认图片
                    ProductImage productImage=new ProductImage();
                    productImage.setUrl("http://39.106.106.39:8888/voice/20230404115600_0.png");
                    product.getImages().add(productImage);
                }
                products.add(product);
            }
        } catch (Exception e) {
            System.out.println("ProductDAOImpl.list(" + category.getId() + ")出现异常");
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> list(int cid) {
        List<Product> products = new ArrayList<>();
        String sql = "select * from product where cid=?";
        try (Connection c = JDBCUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setSubTitle(rs.getString("subTitle"));
                product.setOriginalPrice(rs.getFloat("originalPrice"));
                product.setPromotePrice(rs.getFloat("promotePrice"));
                product.setStock(rs.getInt("stock"));
                //根据id获取完整的种类对象
                product.setCategory(cdi.get(cid));
                product.setCreateDate(rs.getDate("createDate"));
                //调用xxxximpl的方法计算评价数量,销量
                product.setReviewCount(rdi.list(product).size());
                product.setSaleCount(oidi.list(product).size());
                List<ProductImage> productImages=pidi.list(product);
                product.setImages(productImages);
                if(productImages.size()==0){
                    //如果该产品没有照片，存入默认图片
                    ProductImage productImage=new ProductImage();
                    productImage.setUrl("http://39.106.106.39:8888/voice/20230404115600_0.png");
                    product.getImages().add(productImage);
                }
                products.add(product);
            }
        } catch (Exception e) {
            System.out.println("ProductDAOImpl.list(" + cid + ")出现异常");
            e.printStackTrace();
        }
        return products;
    }

    /**模糊查询
     * @param value 模糊查询值
     * @return
     */
    public List<Product> list(String value) {
        String name="%"+value+"%";
        List<Product> products = new ArrayList<>();
        String sql = "select * from product where name like ?";
        try (Connection c = JDBCUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setSubTitle(rs.getString("subTitle"));
                product.setOriginalPrice(rs.getFloat("originalPrice"));
                product.setPromotePrice(rs.getFloat("promotePrice"));
                product.setStock(rs.getInt("stock"));
                //根据id获取完整的种类对象
                product.setCategory(cdi.get(rs.getInt("cid")));
                product.setCreateDate(rs.getDate("createDate"));
                //调用xxxximpl的方法计算评价数量,销量
                product.setReviewCount(rdi.list(product).size());
                product.setSaleCount(oidi.list(product).size());
                List<ProductImage> productImages=pidi.list(product);
                product.setImages(productImages);
                if(productImages.size()==0){
                    //如果该产品没有照片，存入默认图片
                    ProductImage productImage=new ProductImage();
                    productImage.setUrl("http://39.106.106.39:8888/voice/20230404115600_0.png");
                    product.getImages().add(productImage);
                }
                products.add(product);
            }
        } catch (Exception e) {
            System.out.println("ProductDAOImpl.list(" + value + ")出现异常");
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> list(int start, int count) {
        List<Product> products=new ArrayList<>();
        String sql="select * from category limit ?,?";
        try(Connection c= JDBCUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Product product=new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setSubTitle(rs.getString("subTitle"));
                product.setOriginalPrice(rs.getFloat("originalPrice"));
                product.setPromotePrice(rs.getFloat("promotePrice"));
                product.setStock(rs.getInt("stock"));
                //根据cid获取对象
                product.setCategory(cdi.get(rs.getInt("cid")));
                product.setCreateDate(rs.getDate("createDate"));
                //调用xxxximpl的方法计算评价数量,销量
                product.setReviewCount(rdi.list(product).size());
                product.setSaleCount(oidi.list(product).size());
                product.setImages(pidi.list(product));
                products.add(product);
            }
        }catch (Exception e){
            System.out.println("CategoryDAOImpl.list("+start+","+count+")出现异常");
            e.printStackTrace();
        }
        return products;
    }
}
