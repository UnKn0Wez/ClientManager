package com.wt.service.impl;

import com.wt.dao.ProductDao;
import com.wt.dao.RequestDao;
import com.wt.entity.Product;
import com.wt.entity.Request;
import com.wt.factory.DaoFactory;
import com.wt.service.RequestService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * @ClassName RequestServiceImpl
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/12 8:47
 **/
public class RequestServiceImpl implements RequestService {
    private final RequestDao requestDao= DaoFactory.getRequestInstance();
    @Override
    public List<Request> selectAllRequest() {
        List<Request> requestList=null;
        try {
            requestList=requestDao.selectAllRequest();
        } catch (SQLException e) {
            System.err.println("查询反馈信息出现错误！");
        }
        return requestList;
    }

    @Override
    public List<Request> searchRequest(String request_id, String order_id) {
        List<Request> requestList=null;
        try {
            requestList=requestDao.searchRequest(request_id,order_id);
        } catch (SQLException e) {
            System.err.println("查询反馈信息出现错误！");
        }
        return requestList;
    }

    @Override
    public int Requested(String order_id, String client_id) {
        int requestList=0;
        try {
            requestList=requestDao.Requested(order_id,client_id);
        } catch (SQLException e) {
            System.err.println("查询反馈信息出现错误！");
        }
        return requestList;
    }

    @Override
    public void addRequest(Request request) {
        try {
            requestDao.addRequest(request);
        } catch (SQLException | ParseException e) {
            System.err.println("添加反馈信息出现错误！");
        }
    }
}
