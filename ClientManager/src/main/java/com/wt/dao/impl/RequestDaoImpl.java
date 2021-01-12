package com.wt.dao.impl;

import com.wt.dao.RequestDao;
import com.wt.entity.Order;
import com.wt.entity.Request;
import com.wt.utils.FormatUtil;
import com.wt.utils.JdbcUtil;
import com.wt.vo.ContactVo;
import com.wt.vo.UserVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RequestDaoImpl
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/12 8:16
 **/
public class RequestDaoImpl implements RequestDao {
    @Override
    public List<Request> selectAllRequest() throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql;
        List<Request> list = new ArrayList<>();
        UserVo uv=new UserVo();
        String urole=uv.getuRole();
        String contact_id=uv.getcontactId();
        String requestclient_id=uv.getclientId();
        if("Admin".equals(urole)) {
        sql="select request_id,t_order.order_id,user_name,request_time,t_request.client_id from t_request,t_order,t_user where " +
                "t_request.order_id=t_order.order_id and t_request.client_id=t_user.client_id";
        PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Request request = Request.builder()
                        .client_id(rs.getString("client_id"))
                        .order_id(rs.getString("t_order.order_id"))
                        .request_id(rs.getString("request_id"))
                        .request_time(rs.getDate("request_time"))
                        .client_name(rs.getString("user_name"))
                        .build();
                list.add(request);
            }
            rs.close();
            pstmt.close();
        }
        if("Contact".equals(urole)){
            sql="select request_id,t_order.order_id,user_name,request_time,t_request.client_id from t_request,t_order,t_user where " +
                    "t_request.order_id=t_order.order_id and t_request.client_id=t_user.client_id and t_order.contact_id='"+contact_id+"'";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Request request = Request.builder()
                        .client_id(rs.getString("client_id"))
                        .order_id(rs.getString("t_order.order_id"))
                        .request_id(rs.getString("request_id"))
                        .request_time(rs.getDate("request_time"))
                        .client_name(rs.getString("user_name"))
                        .build();
                list.add(request);
            }
            rs.close();
            pstmt.close();
        }
        if("Client".equals(urole)){
            sql="select request_id,t_order.order_id,user_name,request_time,t_request.client_id from t_request,t_order,t_user where " +
                    "t_request.order_id=t_order.order_id  and t_order.contact_id=t_user.contact_id and t_request.client_id='"+requestclient_id+"'";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Request request = Request.builder()
                        .client_id(rs.getString("client_id"))
                        .order_id(rs.getString("t_order.order_id"))
                        .request_id(rs.getString("request_id"))
                        .request_time(rs.getDate("request_time"))
                        .contact_name(rs.getString("user_name"))
                        .build();
                list.add(request);
            }
            rs.close();
            pstmt.close();
        }
        return list;
    }

    @Override
    public void addRequest(Request request) throws SQLException, ParseException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String longFormat = "yyyy-MM-dd";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String date = LocalDateTime.now().format(df);
        String scDate = FormatUtil.formatDate(request.getRequest_time());
        long time = FormatUtil.longFormat(scDate, longFormat);
        java.sql.Date sqlDate = new java.sql.Date(time);
        String sql="insert into t_request values('"+date+"','"+request.getClient_id()+"','"+sqlDate+"','"+request.getRequest_content()+"','"+request.getOrder_id()+"',null,null,null)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
        jdbcUtil.closeConnection();
    }

    @Override
    public int Requested(String order_id,String client_id) throws SQLException {
        int count=0;
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "select * from t_request,t_order where t_request.order_id=t_order.order_id and t_request.order_id='"+order_id+"' and t_request.client_id='"+client_id+"'";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            count+=1;
        }
        return count;
    }

    @Override
    public List<Request> searchRequest(String request_id, String order_id) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql;
        List<Request> list = new ArrayList<>();
        UserVo uv=new UserVo();
        String urole=uv.getuRole();
        String contact_id=uv.getcontactId();
        String requestclient_id=uv.getclientId();
        if("Admin".equals(urole)) {
            sql="select request_id,t_order.order_id,request_content,user_name,request_time,t_request.client_id from t_request,t_order,t_user where " +
                    "t_request.order_id=t_order.order_id and t_request.client_id=t_user.client_id";
            if(!"".equals(request_id)&&request_id!=null){
                sql+=" and request_id like '%"+request_id+"%'";
            }
            if(!"".equals(order_id)&&order_id!=null){
                sql+=" and t_request.order_id like '%"+order_id+"%'";
            }
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Request request = Request.builder()
                        .client_id(rs.getString("client_id"))
                        .order_id(rs.getString("t_order.order_id"))
                        .request_id(rs.getString("request_id"))
                        .request_content(rs.getString("request_content"))
                        .request_time(rs.getDate("request_time"))
                        .client_name(rs.getString("user_name"))
                        .build();
                list.add(request);
            }
            rs.close();
            pstmt.close();
        }
        if("Contact".equals(urole)){
            sql="select request_id,t_order.order_id,request_content,user_name,request_time,t_request.client_id from t_request,t_order,t_user where " +
                    "t_request.order_id=t_order.order_id and t_request.client_id=t_user.client_id and t_order.contact_id='"+contact_id+"'";
            if(!"".equals(request_id)&&request_id!=null){
                sql+=" and request_id like '%"+request_id+"%'";
            }
            if(!"".equals(order_id)&&order_id!=null){
                sql+=" and t_request.order_id like '%"+order_id+"%'";
            }
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Request request = Request.builder()
                        .client_id(rs.getString("client_id"))
                        .order_id(rs.getString("t_order.order_id"))
                        .request_id(rs.getString("request_id"))
                        .request_content(rs.getString("request_content"))
                        .request_time(rs.getDate("request_time"))
                        .client_name(rs.getString("user_name"))
                        .build();
                list.add(request);
            }
            rs.close();
            pstmt.close();
        }
        if("Client".equals(urole)){
            sql="select request_id,t_order.order_id,request_content,user_name,request_time,t_request.client_id from t_request,t_order,t_user where " +
                    "t_request.order_id=t_order.order_id  and t_order.contact_id=t_user.contact_id and t_request.client_id='"+requestclient_id+"'";
            if(!"".equals(request_id)&&request_id!=null){
                sql+=" and request_id like '%"+request_id+"%'";
            }
            if(!"".equals(order_id)&&order_id!=null){
                sql+=" and t_request.order_id like '%"+order_id+"%'";
            }
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Request request = Request.builder()
                        .client_id(rs.getString("client_id"))
                        .order_id(rs.getString("t_order.order_id"))
                        .request_id(rs.getString("request_id"))
                        .request_content(rs.getString("request_content"))
                        .request_time(rs.getDate("request_time"))
                        .contact_name(rs.getString("user_name"))
                        .build();
                list.add(request);
            }
            rs.close();
            pstmt.close();
        }
        return list;
    }
}
