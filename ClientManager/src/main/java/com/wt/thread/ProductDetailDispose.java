package com.wt.thread;

import com.wt.factory.ServiceFactory;
import com.wt.utils.ShowValuesUtil;
import com.wt.vo.WindowState;

import javax.swing.*;

/**
 * @ClassName ProductDetailDispose
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/6 9:58
 **/
public class ProductDetailDispose implements Runnable{
    Boolean states;
    JPanel productContentPanel;
    JPanel productBodyPanel;
    public void setAll(Boolean states,JPanel productContentPanel,JPanel productBodyPanel){
        this.states=states;
        this.productContentPanel=productContentPanel;
        this.productBodyPanel=productBodyPanel;
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
            productBodyPanel.removeAll();
            svu.showProducts(ServiceFactory.getProductServiceInstance().selectAllProduct(),productContentPanel,productBodyPanel);
            productBodyPanel.revalidate();
            productBodyPanel.repaint();
        }
    }
}
