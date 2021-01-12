package com.wt.thread;

import com.wt.factory.ServiceFactory;
import com.wt.utils.ShowValuesUtil;
import com.wt.vo.WindowState;

import javax.swing.*;

/**
 * @ClassName PlanDetailDispose
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/12 8:13
 **/
public class PlanDetailDispose implements Runnable{
    Boolean states;
    JPanel planContentPanel;
    JPanel planBodyPanel;
    public void setAll(Boolean states,JPanel planContentPanel,JPanel planBodyPanel){
        this.states=states;
        this.planContentPanel=planContentPanel;
        this.planBodyPanel=planBodyPanel;
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
            planBodyPanel.removeAll();
            svu.showPlan(ServiceFactory.getPlanServiceInstance().selectAll(),planContentPanel,planBodyPanel);
            planBodyPanel.revalidate();
            planBodyPanel.repaint();
        }
    }
}
