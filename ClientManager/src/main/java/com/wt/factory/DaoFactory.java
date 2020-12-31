package com.wt.factory;

import com.wt.dao.DepDao;
import com.wt.dao.UserDao;
import com.wt.dao.impl.DepDaoImpl;
import com.wt.dao.impl.UserDaoImpl;

/**
 * @ClassName DaoFactory
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/29 17:59
 **/
public class DaoFactory {
    public static UserDao getUserDaoInstance(){return new UserDaoImpl();}
    public static DepDao getDepDaoInstance(){return new DepDaoImpl();}
}
