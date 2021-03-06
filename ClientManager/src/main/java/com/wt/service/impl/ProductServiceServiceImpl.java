package com.wt.service.impl;

import com.wt.dao.DepDao;
import com.wt.dao.ProductDao;
import com.wt.entity.Department;
import com.wt.entity.Product;
import com.wt.factory.DaoFactory;
import com.wt.service.ProductService;

import java.sql.SQLException;
import java.text.ParseException;
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
            System.err.println("查询产品出现错误！");
        }
        return productList;
    }

    @Override
    public void addProduct(Product product) {
        try {
            productDao.addProduct(product);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delProduct(String proId) {
        try {
            productDao.delProduct(proId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product productDetail(String proId) {
        Product product=null;
        try {
            product=productDao.productDetail(proId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void updateProduct(Product product, String proId) {
        try {
            productDao.updateProduct(product,proId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> searchProduct(String name, String type) {
        List<Product> productList=null;
        try {
            productList=productDao.searchProduct(name,type);
        } catch (SQLException e) {
            System.err.println("查询产品出现错误！");
        }
        return productList;
    }

    @Override
    public Product selectProByName(String proName) {
        Product product=null;
        try {
            product=productDao.selectProByName(proName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
