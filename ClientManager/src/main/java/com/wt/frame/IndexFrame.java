package com.wt.frame;

import org.apache.poi.ss.formula.functions.Index;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName IndexFrame
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/30 9:21
 **/
public class IndexFrame extends JFrame{
    private JPanel mainPanel;
    private JPanel menuPanel;

    IndexFrame(){
        init();
    }

    public void init(){
        setContentPane(mainPanel);
        setTitle("主页");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1200,800));
        setVisible(true);
    }

    public static void main(String[] args) {
        new IndexFrame();
    }
}
