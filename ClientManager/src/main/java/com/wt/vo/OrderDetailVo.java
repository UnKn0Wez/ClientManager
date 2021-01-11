package com.wt.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName OrderDetailVo
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/11 19:46
 **/
public class OrderDetailVo {
    private static String order_id;
    public static String getorder_id() {
        return order_id;
    }

    public static void setorder_id(String order_id) {
        OrderDetailVo.order_id = order_id;
    }

}
