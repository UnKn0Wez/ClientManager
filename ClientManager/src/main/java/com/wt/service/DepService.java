package com.wt.service;

import com.wt.entity.Department;
import com.wt.entity.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName DepService
 * @Description TODO
 * @Author UnKnW
 * @Date 2020/12/30
 **/
public interface DepService {
    List<Department> selectDepAll();
    List<Department> selectDep(String name,Integer time);
}
