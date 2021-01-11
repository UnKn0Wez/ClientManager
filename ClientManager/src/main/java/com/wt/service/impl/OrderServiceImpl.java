package com.wt.service.impl;

import com.wt.dao.OrderDao;
import com.wt.dao.ProductDao;
import com.wt.entity.Order;
import com.wt.entity.Product;
import com.wt.factory.DaoFactory;
import com.wt.service.OrderService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/11 12:09
 **/
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao= DaoFactory.getOrderDaoInstance();
    @Override
    public List<Order> selectAllOrder() {
        List<Order> orderList=null;
        try {
            orderList=orderDao.selectAllOrder();
        } catch (SQLException e) {
            System.err.println("查询订单出现错误！");
        }
        return orderList;
    }

    @Override
    public List<Order> searchOrder(String order_id, String type_name, String product_name) {
        List<Order> orderList=null;
        try {
            orderList=orderDao.searchOrder(order_id,type_name,product_name);
        } catch (SQLException e) {
            System.err.println("查询订单出现错误！");
        }
        return orderList;
    }

    @Override
    public Order OrderDetail(String order_id) {
        Order orderList=null;
        try {
            orderList=orderDao.orderDetail(order_id);
        } catch (SQLException e) {
            System.err.println("查询订单出现错误！");
        }
        return orderList;
    }
}
