package com.wt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.PrimitiveIterator;

/**
 * @ClassName Product
 * @Description TODO
 * @Author UnKnW
 * @Date 2020/12/29 17:58
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String productId;
    private String productName;
    private Date productDate;
    private String productType;
    private Double price;
    @Override
    public String toString() {
        return productName;
    }
}
