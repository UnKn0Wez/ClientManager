package com.wt.dao.impl;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.wt.dao.OrderDao;
import com.wt.entity.Order;
import com.wt.utils.FormatUtil;
import com.wt.utils.JdbcUtil;
import com.wt.vo.ContactVo;
import com.wt.vo.OrderVo;
import com.wt.vo.UserVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
        String sql;
        List<Order> list = new ArrayList<>();
        UserVo uv=new UserVo();
        String urole=uv.getuRole();
        String contact_id=uv.getcontactId();
        String client_id=uv.getclientId();
        if("Admin".equals(urole)){
            sql="select " +
                    "t_order.order_id," +
                    "buy_time,buy_num,product_type,product_name,price,"+
                    " t_user.user_name as 'client_name',t_user.user_phone as 'client_phone'" +
                    " from t_order,t_plan,t_product,t_user where " +
                    "t_order.plan_id=t_plan.plan_id and " +
                    "t_order.client_id=t_user.client_id and " +
                    "t_product.product_id=t_plan.product_id";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
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
        }
        if("Contact".equals(urole)){
            sql="select " +
                    "t_order.order_id," +
                    "buy_time,buy_num,product_type,product_name,price,"+
                    " t_user.user_name as 'client_name',t_user.user_phone as 'client_phone'" +
                    " from t_order,t_plan,t_product,t_user where " +
                    "t_order.plan_id=t_plan.plan_id and " +
                    "t_order.client_id=t_user.client_id and " +
                    "t_product.product_id=t_plan.product_id and "+
                    " t_order.contact_id='"+contact_id+"'";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
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
        }
        if("Client".equals(urole)){
            sql="select " +
                    "t_order.order_id," +
                    "buy_time,buy_num,product_type,product_name,price,"+
                    " t_user.user_name as 'contact_name',t_user.user_phone as 'contact_phone'" +
                    " from t_order,t_plan,t_product,t_user where " +
                    "t_order.plan_id=t_plan.plan_id and " +
                    "t_order.client_id=t_user.client_id and " +
                    "t_product.product_id=t_plan.product_id and "+
                    " t_order.client_id='"+client_id+"'";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
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
        }
        jdbcUtil.closeConnection();
        return list;
    }

    @Override
    public List<Order> searchOrder(String order_id, String type_name, String product_name) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql;
        UserVo uv=new UserVo();
        String urole=uv.getuRole();
        String contact_id=uv.getcontactId();
        String client_id=uv.getclientId();
        List<Order> list = new ArrayList<>();
        if("Admin".equals(urole)){
            sql="select " +
                    "t_order.order_id," +
                    "buy_time,buy_num,product_type,product_name,price,"+
                    " t_user.user_name as 'client_name',t_user.user_phone as 'client_phone'" +
                    " from t_order,t_plan,t_product,t_user where " +
                    "t_order.plan_id=t_plan.plan_id and " +
                    "t_order.client_id=t_user.client_id and " +
                    "t_product.product_id=t_plan.product_id";
            if(!"".equals(order_id)&&order_id!=null){
                sql+=" and order_id like '%"+order_id+"%'";
            }
            if(!"请选择产品类型".equals(type_name)){
                sql+=" and product_type='"+type_name+"'";
            }
            if(!"".equals(product_name)&&product_name!=null){
                sql+=" and product_name like '%"+product_name+"%'";
            }
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
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
        }
        if("Contact".equals(urole)){
            sql="select " +
                    "t_order.order_id," +
                    "buy_time,buy_num,product_type,product_name,price,"+
                    " t_user.user_name as 'client_name',t_user.user_phone as 'client_phone'" +
                    " from t_order,t_plan,t_product,t_user where " +
                    "t_order.plan_id=t_plan.plan_id and " +
                    "t_order.client_id=t_user.client_id and " +
                    "t_product.product_id=t_plan.product_id and "+
                    " t_order.contact_id='"+contact_id+"'";
            if(!"".equals(order_id)&&order_id!=null){
                sql+=" and order_id like '%"+order_id+"%'";
            }
            if(!"请选择产品类型".equals(type_name)){
                sql+=" and product_type='"+type_name+"'";
            }
            if(!"".equals(product_name)&&product_name!=null){
                sql+=" and product_name like '%"+product_name+"%'";
            }
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
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
        }
        if("Client".equals(urole)){
            sql="select " +
                    "t_order.order_id," +
                    "buy_time,buy_num,product_type,product_name,price,"+
                    " t_user.user_name as 'contact_name',t_user.user_phone as 'contact_phone'" +
                    " from t_order,t_plan,t_product,t_user where " +
                    "t_order.plan_id=t_plan.plan_id and " +
                    "t_order.client_id=t_user.client_id and " +
                    "t_product.product_id=t_plan.product_id and "+
                    " t_user.client_id='"+client_id+"'";
            if(!"".equals(order_id)&&order_id!=null){
                sql+=" and order_id like '%"+order_id+"%'";
            }
            if(!"请选择产品类型".equals(type_name)){
                sql+=" and product_type='"+type_name+"'";
            }
            if(!"".equals(product_name)&&product_name!=null){
                sql+=" and product_name like '%"+product_name+"%'";
            }
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
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
        }
        return list;
    }

    @Override
    public Order orderDetail(String order_id) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql;
        Order order=new Order();
        UserVo uv=new UserVo();
        String urole=uv.getuRole();
        String contact_id=uv.getcontactId();
        String client_id=uv.getclientId();
        if("Admin".equals(urole)){
            sql="select " +
                    "t_order.order_id," +
                    "buy_time,buy_num,product_type,product_name,price,"+
                    " t_user.user_name as 'client_name',t_user.user_phone as 'client_phone'" +
                    " from t_order,t_plan,t_product,t_user where " +
                    "t_order.plan_id=t_plan.plan_id and " +
                    "t_order.client_id=t_user.client_id and " +
                    "t_product.product_id=t_plan.product_id and order_id='"+order_id+"'";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                order = Order.builder()
                        .buy_num(rs.getInt("buy_num"))
                        .buy_time(rs.getDate("buy_time"))
                        .client_name(rs.getString("client_name"))
                        .client_phone(rs.getString("client_phone"))
                        .order_id(rs.getString("t_order.order_id"))
                        .price(rs.getDouble("price"))
                        .product_name(rs.getString("product_name"))
                        .product_type(rs.getString("product_type"))
                        .build();
            }
            rs.close();
            pstmt.close();
        }
        if("Contact".equals(urole)){
            sql="select " +
                    "t_order.order_id," +
                    "buy_time,buy_num,product_type,product_name,price,"+
                    " t_user.user_name as 'client_name',t_user.user_phone as 'client_phone'" +
                    " from t_order,t_plan,t_product,t_user where " +
                    "t_order.plan_id=t_plan.plan_id and " +
                    "t_order.client_id=t_user.client_id and " +
                    "t_product.product_id=t_plan.product_id and "+
                    " t_order.contact_id='"+contact_id+"' and order_id='"+order_id+"'";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                order = Order.builder()
                        .buy_num(rs.getInt("buy_num"))
                        .buy_time(rs.getDate("buy_time"))
                        .client_name(rs.getString("client_name"))
                        .client_phone(rs.getString("client_phone"))
                        .order_id(rs.getString("t_order.order_id"))
                        .price(rs.getDouble("price"))
                        .product_name(rs.getString("product_name"))
                        .product_type(rs.getString("product_type"))
                        .build();
            }
            rs.close();
            pstmt.close();
        }
        if("Client".equals(urole)){
            sql="select " +
                    "t_order.order_id," +
                    "buy_time,buy_num,product_type,product_name,price,"+
                    " t_user.user_name as 'contact_name',t_user.user_phone as 'contact_phone'" +
                    " from t_order,t_plan,t_product,t_user where " +
                    "t_order.plan_id=t_plan.plan_id and " +
                    "t_order.client_id=t_user.client_id and " +
                    "t_product.product_id=t_plan.product_id and "+
                    " t_order.client_id='"+client_id+"' and order_id='"+order_id+"'";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                order = Order.builder()
                        .buy_num(rs.getInt("buy_num"))
                        .buy_time(rs.getDate("buy_time"))
                        .contact_name(rs.getString("contact_name"))
                        .contact_phone(rs.getString("contact_phone"))
                        .order_id(rs.getString("t_order.order_id"))
                        .price(rs.getDouble("price"))
                        .product_name(rs.getString("product_name"))
                        .product_type(rs.getString("product_type"))
                        .build();
            }
            rs.close();
            pstmt.close();
        }
        jdbcUtil.closeConnection();
        return order;
    }

    @Override
    public void newOrder(OrderVo order) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String date = LocalDateTime.now().format(df);
        String longFormat = "yyyy-MM-dd";
        Date date1 = new Date();
        String scDate = FormatUtil.formatDate(date1);
        long time = 0;
        try {
            time = FormatUtil.longFormat(scDate, longFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(time);
        String sql = "insert into t_order(order_id,contact_id,client_id,buy_time,plan_id,buy_num) " +
                "values('"+date+"','"+order.getContactId()+"','"+order.getClientId()+"','"+sqlDate+"','"+order.getPlanId()+"',"+order.getBuy_num()+")";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
        jdbcUtil.closeConnection();
    }
}
