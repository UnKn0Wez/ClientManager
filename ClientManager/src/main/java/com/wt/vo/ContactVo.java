package com.wt.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ContactVo
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/31 9:02
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactVo {
    /**
     * 用户ID
     */
    private String userId;
    //联系人ID
    private String contactId;
    //用户名
    private String userName;
    //真实姓名
    private String realName;
    //用户手机号
    private String userPhone;
    //部门名称
    private String depName;
    //用户头像
    private String userImag;
    //产品名称
    private String productName;
    //工资
    private Double salary;
    //部门ID
    private String depId;
    //产品ID
    private String ProId;

    @Override
    public String toString() {
        return realName;
    }
}
