package com.wt.dao.impl;

import com.wt.dao.DepDao;
import com.wt.entity.Department;
import com.wt.entity.Product;
import com.wt.entity.User;
import com.wt.utils.FormatUtil;
import com.wt.utils.JdbcUtil;
import com.wt.vo.ContactVo;
import com.wt.vo.UserVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName DepDaoImpl
 * @Description TODO
 * @Author UnKnW
 * @Date 2020/12/30 11:42
 **/
public class DepDaoImpl implements DepDao {
    @Override
    public List<Department> selectDep(String name, Integer time) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement pstmt;
        String sql = "Select * from t_department ";
        if(!("".equals(name)) && name != null){
            if(sql.contains("where")){
                sql += " and dep_name like '%" + name+"%'";
            }else{
                sql += "where dep_name like '%" + name+"%'";
            }
        }
        if(time != 0 && time != null){
            if(sql.contains("where")){
                sql += " and YEAR(dep_time)=" + time;
            }else{
                sql += "where YEAR(dep_time)=" + time;
            }
        }
        pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Department> departmentList = new ArrayList<>();
        Department department;
        while (rs.next()) {
            department = Department.builder()
                    .depId(rs.getString("dep_id"))
                    .depName(rs.getString("dep_name"))
                    .depTime(rs.getDate("dep_time"))
                    .build();
            departmentList.add(department);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return departmentList;
    }

    @Override
    public List<Department> selectAllDep() throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_department";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Department> departmentList = new ArrayList<>();
        while (rs.next()) {
            Department department = new Department();
            department.setDepId(rs.getString("dep_id"));
            department.setDepName(rs.getString("dep_name"));
            department.setDepTime(rs.getDate("dep_time"));
            departmentList.add(department);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return departmentList;
    }

    @Override
    public void deleteDep(String depId) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "delete from t_department where dep_id='" + depId + "'";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
        jdbcUtil.closeConnection();
    }

    @Override
    public void addDep(Department department) throws SQLException, ParseException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String date = LocalDateTime.now().format(df);
        String longFormat = "yyyy-MM-dd";
        Date date1 = new Date();
        String scDate = FormatUtil.formatDate(date1);
        long time = FormatUtil.longFormat(scDate, longFormat);
        java.sql.Date sqlDate = new java.sql.Date(time);
        String sql = "insert into t_department(dep_id,dep_name,dep_time) " +
                "values('"+date+"','"+department.getDepName()+"','"+sqlDate+"')";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
        jdbcUtil.closeConnection();
    }

    @Override
    public void update(Department department) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "update t_department " +
                " set dep_name='" + department.getDepName() + "'" +
                " where dep_id='" + department.getDepId() + "'";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
        jdbcUtil.closeConnection();
    }

    @Override
    public Department selectDepById(String depId) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "select dep_name,dep_time from t_department where dep_id='"+depId+"'";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        Department student = null;
        while (rs.next()) {
            student = Department.builder()
                    .depName(rs.getString("dep_name"))
                    .depTime(rs.getDate("dep_time"))
                    .build();
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return student;
    }
}

