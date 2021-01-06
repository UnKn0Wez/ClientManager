package com.wt.service;

import com.wt.entity.Department;
import com.wt.entity.Product;

import java.util.List;

/**
 * @ClassName ProductService
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/5
 **/
public interface ProductService {
    List<Product> selectAllProduct();
    void addProduct(Product product);
    void delProduct(String proId);
}
