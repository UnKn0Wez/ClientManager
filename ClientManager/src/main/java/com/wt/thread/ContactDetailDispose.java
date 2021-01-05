package com.wt.thread;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.wt.factory.ServiceFactory;
import com.wt.frame.ContactDetailFrame;
import com.wt.frame.IndexFrame;
import com.wt.utils.ShowValuesUtil;
import com.wt.vo.WindowState;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;

/**
 * @ClassName ContactDetailDispose
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/5 11:40
 **/
public class ContactDetailDispose implements Runnable{
    Boolean states;
    JPanel contactContentPanel;
    JPanel contactBodyPanel;
    JPanel depPanel;
    public void setCdf(Boolean states){
        this.states=states;
    }
    public void setcontentPanel(JPanel contactContentPanel,JPanel contactBodyPanel,JPanel depPanel){
        this.contactBodyPanel=contactBodyPanel;
        this.contactContentPanel=contactContentPanel;
        this.depPanel=depPanel;
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
            contactBodyPanel.removeAll();
            svu.showContact(ServiceFactory.getUserServiceInstance().selectAll(),contactContentPanel,contactBodyPanel,depPanel);
            contactBodyPanel.revalidate();
            contactBodyPanel.repaint();
        }
    }
}
