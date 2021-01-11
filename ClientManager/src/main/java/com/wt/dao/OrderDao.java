package com.wt.dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.wt.entity.Department;
import com.wt.entity.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName OrderDao
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/11
 **/
public interface OrderDao {
    List<Order> selectAllOrder()throws SQLException;
    List<Order> searchOrder(String order_id,String type_name,String product_name)throws SQLException;
    Order orderDetail(String order_id)throws SQLException;
}
