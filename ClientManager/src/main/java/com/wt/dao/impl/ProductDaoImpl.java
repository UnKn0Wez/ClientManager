package com.wt.dao.impl;

import com.wt.dao.ProductDao;
import com.wt.entity.Department;
import com.wt.entity.Product;
import com.wt.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
}
