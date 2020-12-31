package com.wt.dao.impl;

import com.wt.dao.UserDao;
import com.wt.entity.User;
import com.wt.utils.JdbcUtil;
import com.wt.vo.UserVo;

import javax.management.relation.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/29 17:49
 **/
public class UserDaoImpl implements UserDao {
    @Override
    public User logins(String account) throws SQLException {
        JdbcUtil jdbcUtil=JdbcUtil.getInitJdbcUtil();
        Connection connection=jdbcUtil.getConnection();
        String sql="Select * from t_user where user_name='"+account+"'";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        User user=null;
        while (rs.next()){
            user= User.builder()
                    .userId(rs.getString("user_id"))
                    .userName(rs.getString("user_name"))
                    .realName(rs.getString("realname"))
                    .userRole(rs.getString("user_role"))
                    .password(rs.getString("password"))
                    .userImag(rs.getString("user_img")).build();
            UserVo uv=new UserVo();
            uv.setuName(user.getUserName());
            uv.setuRole(user.getUserRole());
            uv.setuImg(user.getUserImag());
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return user;
    }

    @Override
    public void contactRegister(User user) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String ist = "INSERT INTO t_user(user_id,user_name,`password`,user_role,dep_id,product_id,user_img) " +
                "values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(ist);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String date = LocalDateTime.now().format(df);
        pstmt.setString(1,date);
        pstmt.setString(2,user.getUserName());
        pstmt.setString(3,user.getPassword());
        pstmt.setString(4,user.getUserRole());
        pstmt.setString(5,user.getDepId());
        pstmt.setString(6,user.getProductId());
        pstmt.setString(7,user.getUserImag());
        pstmt.executeUpdate();
        pstmt.close();
        connection.close();
    }

    @Override
    public void clientRegister(User user) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String ist = "INSERT INTO t_user(user_id,user_name,realname,user_phone,client_address,`password`,user_role,user_img) " +
                "values(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(ist);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String date = LocalDateTime.now().format(df);
        pstmt.setString(1,date);
        pstmt.setString(2,user.getUserName());
        pstmt.setString(3,user.getRealName());
        pstmt.setString(4,user.getUserPhone());
        pstmt.setString(5,user.getUserAddress());
        pstmt.setString(6,user.getPassword());
        pstmt.setString(7,user.getUserRole());
        pstmt.setString(8,user.getUserImag());
        pstmt.executeUpdate();
        pstmt.close();
        connection.close();
    }
}
