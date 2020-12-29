package com.wt.dao;

import com.wt.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/29 17:49
 **/
public interface UserDao {
    User logins(String account) throws SQLException;
}
