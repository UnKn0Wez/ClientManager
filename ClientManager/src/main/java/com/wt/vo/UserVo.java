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
    public static String clientId;
    public static String contactId;
    public static String uName;
    public static String uRole;
    public static String uImg;
    public static String password;
    public void setpassword(String password) {
        this.password = password;
    }

    public String getpassword() {
        return this.password;
    }
    public void setcontactId(String contactId) {
        this.contactId = contactId;
    }

    public String getcontactId() {
        return this.contactId;
    }

    public void setclientId(String clientId) {
        this.clientId = clientId;
    }

    public String getclientId() {
        return this.clientId;
    }

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

    public void setuImg(String uImg) {
        this.uImg = uImg;
    }

    public String getuImg() {
        return this.uImg;
    }
}
