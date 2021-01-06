package com.wt.dao;

import com.wt.entity.Department;
import com.wt.entity.Product;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * @ClassName DepDao
 * @Description TODO
 * @Author UnKnW
 * @Date 2020/12/30
 **/
public interface DepDao {
    List<Department> selectDep(String name,Integer time) throws SQLException;
    List<Department> selectAllDep()throws SQLException;
    void deleteDep(String depId)throws SQLException;
    void addDep(Department department) throws SQLException, ParseException;
    void update(Department department)throws SQLException;
    Department selectDepById(String depId)throws SQLException;
}
