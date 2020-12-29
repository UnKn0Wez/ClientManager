package com.wt.service;

import com.wt.entity.User;
import com.wt.utils.ResultEntity;

import java.util.List;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/29
 **/
public interface UserService {
    ResultEntity login(String uName, String password);
}
