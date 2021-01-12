package com.wt.service;

import com.sun.org.apache.regexp.internal.RE;
import com.wt.entity.Request;

import java.util.List;
/**
 * @ClassName RequestService
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/12
 **/
public interface RequestService {
    List<Request> selectAllRequest();
    List<Request> searchRequest(String request_id,String order_id);
    int Requested(String order_id,String client_id);
    void addRequest(Request request);
}
