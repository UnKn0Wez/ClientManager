package com.wt.service.impl;

import com.wt.dao.DepDao;
import com.wt.dao.UserDao;
import com.wt.entity.Department;
import com.wt.entity.Product;
import com.wt.factory.DaoFactory;
import com.wt.service.DepService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName DepServiceImpl
 * @Description TODO
 * @Author UnKnW
 * @Date 2020/12/30 13:00
 **/
public class DepServiceImpl implements DepService {
    private final DepDao depDao= DaoFactory.getDepDaoInstance();
    @Override
    public List<Department> selectDepAll() {
        List<Department> departmentList=null;
        try {
            departmentList=depDao.selectAllDep();
        } catch (SQLException e) {
            System.err.println("查询部门出现错误！");
        }
        return departmentList;
    }


}
