package com.wt.dao.impl;

import com.wt.dao.DepDao;
import com.wt.entity.Department;
import com.wt.entity.Product;
import com.wt.entity.User;
import com.wt.utils.JdbcUtil;
import com.wt.vo.UserVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DepDaoImpl
 * @Description TODO
 * @Author UnKnW
 * @Date 2020/12/30 11:42
 **/
public class DepDaoImpl implements DepDao {
    @Override
    public List<Department> selectDep(String num) throws SQLException {
        JdbcUtil jdbcUtil=JdbcUtil.getInitJdbcUtil();
        Connection connection=jdbcUtil.getConnection();
        String sql="Select * from t_department where dep_id=?";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        pstmt.setString(1,num);
        List<Department> departmentList = new ArrayList<>();
        Department department;
        while (rs.next()){
            department= Department.builder()
                    .depId(rs.getString("dep_id"))
                    .depName(rs.getString("dep_name")).build();
            departmentList.add(department);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return departmentList;
    }
    @Override
    public List<Department> selectAllDep() throws SQLException{
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_department";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Department> departmentList = new ArrayList<>();
        while(rs.next()){
            Department department = new Department();
            department.setDepId(rs.getString("dep_id"));
            department.setDepName(rs.getString("dep_name"));
            departmentList.add(department);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return departmentList;
    }
}
