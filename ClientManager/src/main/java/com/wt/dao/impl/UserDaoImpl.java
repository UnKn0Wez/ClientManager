package com.wt.dao.impl;

import com.wt.dao.UserDao;
import com.wt.entity.User;
import com.wt.utils.JdbcUtil;
import com.wt.vo.ClientVo;
import com.wt.vo.ContactVo;
import com.wt.vo.UserVo;

import javax.management.relation.Role;
import java.sql.*;
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
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "Select * from t_user where user_name='" + account + "'";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        User user = null;
        while (rs.next()) {
            user = User.builder()
                    .userId(rs.getString("user_id"))
                    .userName(rs.getString("user_name"))
                    .realName(rs.getString("realname"))
                    .userRole(rs.getString("user_role"))
                    .password(rs.getString("password"))
                    .userImag(rs.getString("user_img")).build();
            UserVo uv = new UserVo();
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
        String ist = "INSERT INTO t_user(user_id,contact_id,user_name,user_phone,dep_id,product_id,user_img,password,user_role,realname,salary)\n" +
                "values(?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement pstmt = connection.prepareStatement(ist);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String date = LocalDateTime.now().format(df);
        pstmt.setString(1, date);
        pstmt.setString(3, user.getUserName());
        pstmt.setString(8, user.getPassword());
        pstmt.setString(4, user.getUserRole());
        pstmt.setString(5, user.getDepId());
        pstmt.setString(6, user.getProductId());
        pstmt.setString(7, user.getUserImag());
        pstmt.setString(4, user.getUserPhone());
        pstmt.setString(2, "Contact" + date);
        pstmt.setString(9, "Contact");
        pstmt.setString(10, user.getRealName());
        pstmt.setDouble(11, 3000);
        pstmt.executeUpdate();
        pstmt.close();
        connection.close();
    }

    @Override
    public List<ContactVo> selectAll() throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "select user_id,contact_id,user_name,realname,user_phone,dep_name,product_name,user_img,salary " +
                "from t_user,t_department,t_product " +
                "where t_user.dep_id=t_department.dep_id " +
                "and t_user.product_id=t_product.product_id " +
                "and user_role = 'Contact'";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<ContactVo> list = new ArrayList<>();
        while (rs.next()) {
            ContactVo student = ContactVo.builder()
                    .userId(rs.getString("user_id"))
                    .contactId(rs.getString("contact_id"))
                    .userName(rs.getString("user_name"))
                    .realName(rs.getString("realname"))
                    .userPhone(rs.getString("user_phone"))
                    .depName(rs.getString("dep_name"))
                    .salary(rs.getDouble("salary"))
                    .userImag(rs.getString("user_img"))
                    .productName(rs.getString("product_name"))
                    .build();
            list.add(student);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return list;
    }

    @Override
    public List<ClientVo> selectClientAll() throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT user_id,client_id,user_name,realname,user_phone,client_credit,buy_time,user_img,client_address" +
                " FROM t_user " +
                "where user_role='Client';";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<ClientVo> list = new ArrayList<>();
        while (rs.next()) {
            ClientVo student = ClientVo.builder()
                    .userId(rs.getString("user_id"))
                    .clientId(rs.getString("client_id"))
                    .userName(rs.getString("user_name"))
                    .realName(rs.getString("realname"))
                    .userPhone(rs.getString("user_phone"))
                    .clientCredit(rs.getString("client_credit"))
                    .buyTime(rs.getDate("buy_time"))
                    .userImg(rs.getString("user_img"))
                    .clientAddress(rs.getString("client_address"))
                    .userRole("Client")
                    .build();
            list.add(student);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return list;
    }

    @Override
    public void deleteContact(String contactId) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "delete from t_user where contact_id='" + contactId + "'";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
        jdbcUtil.closeConnection();
    }

    @Override
    public List<ContactVo> selectByContact(String contact_Id) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "select user_id,contact_id,user_name,realname,user_phone,dep_name,product_name,user_img,salary " +
                "from t_user,t_department,t_product " +
                "where t_user.dep_id=t_department.dep_id " +
                "and t_user.product_id=t_product.product_id " +
                "and contact_id ='" + contact_Id + "'";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<ContactVo> list = new ArrayList<>();
        while (rs.next()) {
            ContactVo student = ContactVo.builder()
                    .userId(rs.getString("user_id"))
                    .contactId(rs.getString("contact_id"))
                    .userName(rs.getString("user_name"))
                    .realName(rs.getString("realname"))
                    .userPhone(rs.getString("user_phone"))
                    .depName(rs.getString("dep_name"))
                    .salary(rs.getDouble("salary"))
                    .userImag(rs.getString("user_img"))
                    .productName(rs.getString("product_name"))
                    .build();
            list.add(student);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return list;
    }

    @Override
    public void clientRegister(User user) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String ist = "INSERT INTO t_user(user_id,user_name,realname,user_phone,client_address,`password`,user_role,user_img,client_id) " +
                "values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(ist);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String date = LocalDateTime.now().format(df);
        pstmt.setString(1, date);
        pstmt.setString(2, user.getUserName());
        pstmt.setString(3, user.getRealName());
        pstmt.setString(4, user.getUserPhone());
        pstmt.setString(5, user.getUserAddress());
        pstmt.setString(6, user.getPassword());
        pstmt.setString(7, user.getUserRole());
        pstmt.setString(8, user.getUserImag());
        pstmt.setString(9, "Client" + date);
        pstmt.executeUpdate();
        pstmt.close();
        connection.close();
    }
}
