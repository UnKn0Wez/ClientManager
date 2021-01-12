package com.wt.vo;

import javax.swing.*;

/**
 * @ClassName FAQ
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/5 12:32
 **/
public class MyTable {
    private static JTable Contact_table;
    private static JTable Client_table;
    private static JTable Dep_table;
    private static JTable Product_table;
    private static JTable Order_table;
    private static JTable Request_table;
    public void setRequest_table(JTable Request_table) {
        this.Request_table = Request_table;
    }

    public JTable getRequest_table() {
        return this.Request_table;
    }

    public void setuOrder_table(JTable Order_table) {
        this.Order_table = Order_table;
    }

    public JTable getuOrder_table() {
        return this.Order_table;
    }

    public void setuContact_table(JTable Contact_table) {
        this.Contact_table = Contact_table;
    }

    public JTable getuContact_table() {
        return this.Contact_table;
    }
    public void setClient_table(JTable Client_table) {
        this.Client_table = Client_table;
    }

    public JTable getClient_table() {
        return this.Client_table;
    }
    public void setDep_table(JTable Dep_table) {
        this.Dep_table = Dep_table;
    }

    public JTable getDep_table() {
        return this.Dep_table;
    }

    public void setProduct_table(JTable Product_table) {
        this.Product_table = Product_table;
    }

    public JTable getProduct_table() {
        return this.Product_table;
    }


}
