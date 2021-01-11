package com.wt.dao;

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
    List<Order> selectClientOrder(String client_id)throws SQLException;
    List<Order> selectContactOrder(String contact_id)throws SQLException;
}
