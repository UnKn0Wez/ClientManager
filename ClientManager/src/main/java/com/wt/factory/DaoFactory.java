package com.wt.factory;

import com.wt.dao.*;
import com.wt.dao.impl.*;

/**
 * @ClassName DaoFactory
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/29 17:59
 **/
public class DaoFactory {
    public static UserDao getUserDaoInstance(){return new UserDaoImpl();}
    public static DepDao getDepDaoInstance(){return new DepDaoImpl();}
    public static OrderDao getOrderDaoInstance(){return new OrderDaoImpl();}
    public static ProductDao getProDaoInstance(){return new ProductDaoImpl();}
    public static RequestDao getRequestInstance(){return new RequestDaoImpl();}
}
