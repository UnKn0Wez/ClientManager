package com.wt.vo;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @ClassName WindowState
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/5 11:52
 **/
public class WindowState {
    public static Boolean states;
    public static Boolean addStates;
    public void setaddStates(Boolean addStates) {
        this.addStates = addStates;
    }

    public Boolean getaddStates() {
        return this.addStates;
    }
    public void setustates(Boolean states) {
        this.states = states;
    }

    public Boolean getstates() {
        return this.states;
    }
}
