package com.wt.service;

import com.wt.entity.Order;

import java.util.List;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/11
 **/
public interface OrderService {
    List<Order> selectAllOrder();
    List<Order> searchOrder(String order_id, String type_name, String product_name);
    Order OrderDetail(String order_id);
}
