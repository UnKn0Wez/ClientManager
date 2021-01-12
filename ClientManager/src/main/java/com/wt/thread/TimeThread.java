package com.wt.thread;

import com.wt.utils.FormatUtil;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @ClassName TimeThread
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/12 13:48
 **/
public class TimeThread implements Runnable {
    public JLabel timeLabel;
    public void setTimeLabel(JLabel timeLabel){
        this.timeLabel=timeLabel;
    }
    @Override
    public void run() {
        while (true){
            Date date=new Date();
            timeLabel.setText(FormatUtil.formatDatetime(date));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
