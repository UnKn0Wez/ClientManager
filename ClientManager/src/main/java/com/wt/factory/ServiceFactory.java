package com.wt.factory;

import com.wt.service.*;
import com.wt.service.impl.*;

/**
 * @ClassName ServiceFactory
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/29 17:59
 **/
public class ServiceFactory {
    public static UserService getUserServiceInstance(){return new UserServiceImpl();}
    public static DepService getDepServiceInstance(){return new DepServiceImpl();}
    public static ProductService getProductServiceInstance(){return new ProductServiceServiceImpl();}
    public static OrderService getOrderServiceInstance(){return new OrderServiceImpl();}
    public static RequestService getRequestServiceInstanct(){return new RequestServiceImpl();}
}
