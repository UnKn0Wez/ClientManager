package com.wt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName Mission
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/11 12:32
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mission {
    private String planId;
    private Integer clientNum;
    private Double planProfit;
    private Date startTime;
    private Date finishTime;
    private String planState;
    private String productId;
}
