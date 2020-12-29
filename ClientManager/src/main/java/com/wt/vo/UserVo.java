package com.wt.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserVo
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/29 18:30
 **/
public class UserVo {
    public static String uName;
    public static String uRole;

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuName() {
        return this.uName;
    }

    public void setuRole(String uRole) {
        this.uRole = uRole;
    }

    public String getuRole() {
        return this.uRole;
    }
}
