package com.wt.thread;

import com.wt.factory.ServiceFactory;
import com.wt.utils.ShowValuesUtil;
import com.wt.vo.WindowState;

import javax.swing.*;

/**
 * @ClassName ClientDetailDispose
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/6 11:16
 **/
public class ClientDetailDispose implements Runnable {
    Boolean states;
    JPanel clientContentPanel;
    JPanel clientBodyPanel;
    public void setAll(Boolean states,JPanel clientContentPanel,JPanel clientBodyPanel){
        this.states=states;
        this.clientContentPanel=clientContentPanel;
        this.clientBodyPanel=clientBodyPanel;
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
            clientBodyPanel.removeAll();
            svu.showClient(ServiceFactory.getUserServiceInstance().selectClientAll(),clientContentPanel,clientBodyPanel);
            clientBodyPanel.revalidate();
            clientBodyPanel.repaint();
        }
    }
}
