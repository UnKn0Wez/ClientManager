package com.wt.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName OrderVo
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/12 14:09
 **/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVo {
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
    private String contactId;
    private String clientId;
    private String planId;
}
