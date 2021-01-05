package com.wt.service.impl;

import com.wt.dao.DepDao;
import com.wt.dao.ProductDao;
import com.wt.entity.Department;
import com.wt.entity.Product;
import com.wt.factory.DaoFactory;
import com.wt.service.ProductService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ProductServiceServiceImpl
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/5 10:43
 **/
public class ProductServiceServiceImpl implements ProductService {
    private final ProductDao productDao= DaoFactory.getProDaoInstance();
    @Override
    public List<Product> selectAllProduct() {
        List<Product> productList=null;
        try {
            productList=productDao.selectAllPro();
        } catch (SQLException e) {
            System.err.println("查询部门出现错误！");
        }
        return productList;
    }
}
