package com.wt.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ResultEntity
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/29 18:09
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultEntity {
    private int code;
    private String message;
    private Object data;
}
