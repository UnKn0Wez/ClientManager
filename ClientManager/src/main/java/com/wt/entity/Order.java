package com.wt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName Order
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/11 11:02
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String order_id;
    private String client_name;
    private String contact_name;
    private String contact_phone;
    private String client_phone;
    private Date buy_time;
    private int buy_num;
    private String product_type;
    private String product_name;
    private Double price;
}
