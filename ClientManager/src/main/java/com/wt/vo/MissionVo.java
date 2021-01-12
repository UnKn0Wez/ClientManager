package com.wt.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName MissionVo
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/11 13:49
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionVo {
    private String planId;
    private Integer clientNum;
    private Double planProfit;
    private Date startTime;
    private Date finishTime;
    private String planState;
    private String productType;
    private String productName;
    private String realName;
    private String productId;
}
