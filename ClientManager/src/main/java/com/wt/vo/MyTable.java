package com.wt.vo;

import javax.swing.*;

/**
 * @ClassName FAQ
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/5 12:32
 **/
public class MyTable {
    public static JTable Contact_table;
    public void setuContact_table(JTable Contact_table) {
        this.Contact_table = Contact_table;
    }

    public JTable getuContact_table() {
        return this.Contact_table;
    }
}
