package com.wt.service.impl;

import com.wt.dao.DepDao;
import com.wt.dao.UserDao;
import com.wt.entity.Department;
import com.wt.entity.Product;
import com.wt.factory.DaoFactory;
import com.wt.service.DepService;

import java.sql.SQLException;
import java.text.ParseException;
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

    @Override
    public List<Department> selectDep(String name, Integer time) {
        List<Department> departmentList=null;
        try {
            departmentList=depDao.selectDep(name,time);
        } catch (SQLException e) {
            System.err.println("根据关键查询部门出现错误！");
        }
        return departmentList;
    }

    @Override
    public void deleteDep(String depId) {
        try {
            depDao.deleteDep(depId);
        } catch (SQLException e) {
            System.err.println("删除部门出现错误！");
        }
    }

    @Override
    public void addDep(Department department) {
        try {
            depDao.addDep(department);
        } catch (SQLException e) {
            System.err.println("添加部门发生错误！");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Department department) {
        try {
            depDao.update(department);
        } catch (SQLException e) {
            System.err.println("更新部门信息发生错误！");
        }
    }

    @Override
    public Department selectDepById(String depID) {
        Department department=null;
        try {
            department=depDao.selectDepById(depID);
        } catch (SQLException e) {
            System.err.println("根据部门ID查询发生错误！");
        }
        return department;
    }
}
