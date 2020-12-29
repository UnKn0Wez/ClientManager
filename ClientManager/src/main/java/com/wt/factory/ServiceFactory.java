package com.wt.factory;

import com.wt.service.UserService;
import com.wt.service.impl.UserServiceImpl;

/**
 * @ClassName ServiceFactory
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/29 17:59
 **/
public class ServiceFactory {
    public static UserService getUserServiceInstance(){return new UserServiceImpl();}
}
