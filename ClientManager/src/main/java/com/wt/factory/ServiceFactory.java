package com.wt.factory;

import com.wt.dao.PlanDao;
import com.wt.service.DepService;
import com.wt.service.OrderService;
import com.wt.service.ProductService;
import com.wt.service.UserService;
import com.wt.service.impl.DepServiceImpl;
import com.wt.service.impl.OrderServiceImpl;
import com.wt.service.PlanService;
import com.wt.service.ProductService;
import com.wt.service.UserService;
import com.wt.service.impl.DepServiceImpl;
import com.wt.service.impl.PlanServiceImpl;
import com.wt.service.impl.ProductServiceServiceImpl;
import com.wt.service.impl.UserServiceImpl;
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
    public static PlanService getPlanServiceInstance(){return new PlanServiceImpl(); }
    public static MessageService getMessageServiceInstance(){return new MessageServiceImpl(); }
    public static RequestService getRequestServiceInstanct(){return new RequestServiceImpl();}
}
