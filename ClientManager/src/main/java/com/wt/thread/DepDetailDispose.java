package com.wt.thread;

import com.wt.factory.ServiceFactory;
import com.wt.utils.ShowValuesUtil;
import com.wt.vo.WindowState;

import javax.swing.*;

/**
 * @ClassName DepDetailDispose
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/6 13:52
 **/
public class DepDetailDispose implements Runnable {
    Boolean states;
    JPanel depContentPanel;
    JPanel depBodyPanel;
    public void setAll(Boolean states,JPanel depContentPanel,JPanel depBodyPanel){
        this.states=states;
        this.depContentPanel=depContentPanel;
        this.depBodyPanel=depBodyPanel;
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
            depBodyPanel.removeAll();
            svu.showDep(ServiceFactory.getDepServiceInstance().selectDepAll(),depContentPanel,depBodyPanel);
            depBodyPanel.revalidate();
            depBodyPanel.repaint();
        }
    }
}
