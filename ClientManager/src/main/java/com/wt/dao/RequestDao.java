package com.wt.dao;

import com.wt.entity.Product;
import com.wt.entity.Request;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * @ClassName RequestDao
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/12
 **/
public interface RequestDao {
    List<Request> selectAllRequest()throws SQLException;
    void addRequest(Request request) throws SQLException, ParseException;
    int Requested(String order_id,String client_id)throws SQLException;
    List<Request> searchRequest(String request_id,String order_id)throws SQLException;
}
