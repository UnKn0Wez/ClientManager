package com.wt.dao;

import com.wt.entity.Department;
import com.wt.entity.Product;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * @ClassName ProductDao
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/5
 **/
public interface ProductDao {
    List<Product> selectAllPro()throws SQLException;
    void addProduct(Product product) throws SQLException, ParseException;
    void delProduct(String proId)throws SQLException;
}
