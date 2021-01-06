package com.wt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName Department
 * @Description TODO
 * @Author UnKnW
 * @Date 2020/12/29 17:57
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private String depId;
    private String depName;
    private Date depTime;
    @Override
    public String toString() {
        return depName;
    }
}
