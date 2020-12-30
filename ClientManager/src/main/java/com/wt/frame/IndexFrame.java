package com.wt.frame;

import com.wt.component.RoundBorder;
import com.wt.vo.UserVo;
import org.apache.poi.ss.formula.functions.Index;

import javax.swing.*;
import javax.swing.border.Border;
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
    private JLabel headLabel;

    IndexFrame(){
        init();
        Border border=new RoundBorder(250);
        UserVo uv=new UserVo();
        headLabel.setBorder(border);
        headLabel.setText("<html><img src='"+uv.getuImg()+"' width='160' height='160'/></html>");
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
