package com.wt.vo;

import com.sun.xml.internal.fastinfoset.tools.PrintTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName ClientVo
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/5 9:02
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientVo {
    private String userId;
    private String clientId;
    private String userName;
    private String realName;
    private String userPhone;
    private String userRole;
    private String userImg;
    private String clientAddress;
    private String clientCredit;
    private Date buyTime;
}
