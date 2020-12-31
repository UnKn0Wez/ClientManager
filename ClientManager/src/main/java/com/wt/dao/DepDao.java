package com.wt.dao;

import com.wt.entity.Department;
import com.wt.entity.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName DepDao
 * @Description TODO
 * @Author UnKnW
 * @Date 2020/12/30
 **/
public interface DepDao {
    List<Department> selectDep(String num) throws SQLException;
    List<Department> selectAllDep()throws SQLException;
    List<Product> selectAllPro()throws SQLException;
}
