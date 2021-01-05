package com.wt.dao;

import com.wt.entity.Department;
import com.wt.entity.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ProductDao
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/5
 **/
public interface ProductDao {
    List<Product> selectAllPro()throws SQLException;
}
