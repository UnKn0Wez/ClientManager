package com.wt.dao.impl;

import com.wt.dao.ProductDao;
import com.wt.entity.Department;
import com.wt.entity.Product;
import com.wt.utils.FormatUtil;
import com.wt.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ProductDaoImpl
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/5 10:35
 **/
public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> selectAllPro() throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_product";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Product> productList = new ArrayList<>();
        while(rs.next()){
            Product product = new Product();
            product.setProductId(rs.getString("product_id"));
            product.setProductName(rs.getString("product_name"));
            product.setPrice(rs.getDouble("price"));
            product.setProductDate(rs.getDate("product_date"));
            product.setProductType(rs.getString("product_type"));
            productList.add(product);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return productList;
    }

    public static String DATE_FORMAT = "yyyy-MM-dd";
    @Override
    public void addProduct(Product product) throws SQLException, ParseException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        Date date = new Date();
        String mybirth= FormatUtil.formatDate(date);
        long time = FormatUtil.longFormat(mybirth, DATE_FORMAT);
        java.sql.Date sqlDate = new java.sql.Date(time);
        String date1 = LocalDateTime.now().format(df);
        String sql="insert into t_product(product_id,product_name,product_date,product_type,price) values('"+date1+"','"+product.getProductName()+"','"+ sqlDate +"','"+product.getProductType()+"',"+product.getPrice()+")";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
        jdbcUtil.closeConnection();
    }

    @Override
    public void delProduct(String proId) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql="delete from t_product where product_id='"+proId+"'";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
        jdbcUtil.closeConnection();
    }

    @Override
    public Product productDetail(String proId) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql="select * from t_product where product_id='"+proId+"'";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        Product product=null;
        while (rs.next()) {
            product= Product.builder()
                    .productId(rs.getString("product_id"))
                    .price(rs.getDouble("price"))
                    .productName(rs.getString("product_name"))
                    .productType(rs.getString("product_type"))
                    .productDate(rs.getDate("product_date")).build();
        }
        pstmt.close();
        jdbcUtil.closeConnection();
        return product;
    }

    @Override
    public void updateProduct(Product product, String proId) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql="update t_product set product_name = '"+product.getProductName()+"'" +
                ",product_type='"+product.getProductType()+"'" +
                ",price="+product.getPrice()+" " +
                "where product_id='"+proId+"'";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
        jdbcUtil.closeConnection();
    }

    @Override
    public List<Product> searchProduct(String name, String type) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql="select * from t_product ";
        if(!"".equals(name)&&name!=null){
            if(!sql.contains("where")){
                sql+=" where product_name like '%"+name+"%'";
            }else {
                sql+=" and product_name like '%"+name+"%'";
            }
        }
        if(!"请选择产品类型".equals(type)&&name!=null){
            if(!sql.contains("where")){
                sql+=" where product_type = '"+type+"'";
            }else {
                sql+=" and product_type = '"+type+"'";
            }
        }
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Product> productList = new ArrayList<>();
        while(rs.next()){
            Product product = new Product();
            product.setProductId(rs.getString("product_id"));
            product.setProductName(rs.getString("product_name"));
            product.setPrice(rs.getDouble("price"));
            product.setProductDate(rs.getDate("product_date"));
            product.setProductType(rs.getString("product_type"));
            productList.add(product);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return productList;

    }


}
