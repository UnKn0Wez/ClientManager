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
                    .password(rs.getString("password")).build();
            UserVo uv=new UserVo();
            uv.setuName(user.getUserName());
            uv.setuRole(user.getUserRole());
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return user;
    }
}
