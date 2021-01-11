package com.wt.thread;

import com.wt.factory.ServiceFactory;
import com.wt.utils.ShowValuesUtil;
import com.wt.vo.WindowState;

import javax.swing.*;

/**
 * @ClassName OrderDetailDispose
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/11 19:04
 **/
public class OrderDetailDispose implements Runnable{
    Boolean states;
    JPanel orderContentPanel;
    JPanel orderBodyPanel;
    public void setAll(Boolean states,JPanel orderContentPanel,JPanel orderBodyPanel){
        this.states=states;
        this.orderContentPanel=orderContentPanel;
        this.orderBodyPanel=orderBodyPanel;
    }
    @Override
    public void run() {
        while(states){
            WindowState windowState=new WindowState();
            states=windowState.getstates();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(states==false){
            ShowValuesUtil svu=new ShowValuesUtil();
            orderBodyPanel.removeAll();
            svu.showOrders(ServiceFactory.getOrderServiceInstance().selectAllOrder(),orderContentPanel,orderBodyPanel);
            orderBodyPanel.revalidate();
            orderBodyPanel.repaint();
        }
    }
}
