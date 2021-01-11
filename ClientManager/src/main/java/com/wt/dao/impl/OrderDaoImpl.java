package com.wt.dao.impl;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.wt.dao.OrderDao;
import com.wt.entity.Order;
import com.wt.utils.JdbcUtil;
import com.wt.vo.ContactVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrderDaoImpl
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/11 11:16
 **/
public class OrderDaoImpl implements OrderDao {
    @Override
    public List<Order> selectAllOrder() throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql="select " +
                "t_order.order_id," +
                "t_user.user_name as 'client_name'," +
                "t_user.user_phone as 'client_phone',buy_time,buy_num,product_type,product_name,price" +
                " from t_order,t_plan,t_product,t_user where " +
                "t_order.plan_id=t_plan.plan_id and " +
                "t_order.client_id=t_user.client_id and t_product.product_id=t_plan.product_id";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Order> list = new ArrayList<>();
        while (rs.next()){
            Order order = Order.builder()
                    .buy_num(rs.getInt("buy_num"))
                    .buy_time(rs.getDate("buy_time"))
                    .client_name(rs.getString("client_name"))
                    .client_phone(rs.getString("client_phone"))
                    .order_id(rs.getString("t_order.order_id"))
                    .price(rs.getDouble("price"))
                    .product_name(rs.getString("product_name"))
                    .product_type(rs.getString("product_type"))
                    .build();
            list.add(order);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return list;
    }

    @Override
    public List<Order> selectClientOrder(String client_id) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql="select " +
                "t_order.order_id," +
                "t_user.user_name as 'contact_name'," +
                "t_user.user_phone as 'contact_phone',buy_time,buy_num,product_type,product_name,price" +
                " from t_order,t_plan,t_product,t_user where " +
                "t_order.plan_id=t_plan.plan_id and " +
                "t_order.client_id=t_user.client_id and t_product.product_id=t_plan.product_id and t_user.client_id='"+client_id+"'";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Order> list = new ArrayList<>();
        while (rs.next()){
            Order order = Order.builder()
                    .buy_num(rs.getInt("buy_num"))
                    .buy_time(rs.getDate("buy_time"))
                    .contact_name(rs.getString("contact_name"))
                    .contact_phone(rs.getString("contact_phone"))
                    .order_id(rs.getString("t_order.order_id"))
                    .price(rs.getDouble("price"))
                    .product_name(rs.getString("product_name"))
                    .product_type(rs.getString("product_type"))
                    .build();
            list.add(order);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return list;
    }

    @Override
    public List<Order> selectContactOrder(String contact_id) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql="select " +
                "t_order.order_id," +
                "t_user.user_name as 'client_name'," +
                "t_user.user_phone as 'client_phone',buy_time,buy_num,product_type,product_name,price" +
                " from t_order,t_plan,t_product,t_user where " +
                "t_order.plan_id=t_plan.plan_id and " +
                "t_order.client_id=t_user.client_id and t_product.product_id=t_plan.product_id and t_order.contact_id='"+contact_id+"'";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Order> list = new ArrayList<>();
        while (rs.next()){
            Order order = Order.builder()
                    .buy_num(rs.getInt("buy_num"))
                    .buy_time(rs.getDate("buy_time"))
                    .client_name(rs.getString("client_name"))
                    .client_phone(rs.getString("client_phone"))
                    .order_id(rs.getString("t_order.order_id"))
                    .price(rs.getDouble("price"))
                    .product_name(rs.getString("product_name"))
                    .product_type(rs.getString("product_type"))
                    .build();
            list.add(order);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return list;
    }
}
