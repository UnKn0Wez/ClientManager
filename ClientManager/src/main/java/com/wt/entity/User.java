package com.wt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Admin
 * @Description TODO
 * @Author UnKnW
 * @Date 2020/12/29 8:31
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 客户ID
     */
    private String clientId;
    //联系人ID
    private String contactId;
    //用户名
    private String userName;
    //真实姓名
    private String realName;
    //用户手机号
    private String userPhone;
    //客户地址
    private String userAddress;
    //客户信用度
    private String clientCredit;
    //购买时间
    private String buyTime;
    //部门ID
    private String depId;
    //产品ID
    private String productId;
    //工资
    private Double salary;
    //用户身份
    private String userRole;
    //用户密码
    private String password;
}
