package com.wt.frame;

import com.wt.component.CustomPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName registerFrame
 * @Description TODO
 * @Author UnKnW
 * @Date 2020/12/29 14:00
 **/
public class registerFrame extends JFrame{
    private JPanel mainPanel;
    private CustomPanel registerPanel;

    public registerFrame() {
        init();

    }

    public void init(){
        setTitle("registerFrame");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450,900);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new registerFrame();
    }
}
