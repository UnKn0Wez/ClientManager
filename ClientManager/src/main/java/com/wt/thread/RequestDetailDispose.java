package com.wt.thread;

import com.wt.factory.ServiceFactory;
import com.wt.utils.ShowValuesUtil;
import com.wt.vo.WindowState;

import javax.swing.*;

/**
 * @ClassName RequestDetailDispose
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/12 10:34
 **/
public class RequestDetailDispose implements Runnable {
    Boolean states;
    JPanel requestContentPanel;
    JPanel requestBodyPanel;
    public void setAll(Boolean states,JPanel requestContentPanel,JPanel requestBodyPanel){
        this.states=states;
        this.requestContentPanel=requestContentPanel;
        this.requestBodyPanel=requestBodyPanel;
    }
    @Override
    public void run() {
        while(states){
            WindowState windowState=new WindowState();
            states=windowState.getaddStates();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(states==false){
            ShowValuesUtil svu=new ShowValuesUtil();
            requestBodyPanel.removeAll();
            svu.showRequest(ServiceFactory.getRequestServiceInstanct().selectAllRequest(),requestContentPanel,requestBodyPanel);
            requestBodyPanel.revalidate();
            requestBodyPanel.repaint();
        }
    }
}
