package com.wt.frame;

import com.wt.component.CustomPanel;
import com.wt.component.RoundBorder;
import com.wt.factory.ServiceFactory;
import com.wt.utils.ResultEntity;
import com.wt.vo.UserVo;
import org.apache.poi.ss.formula.functions.Index;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * @ClassName LoginFrame
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/29 14:23
 **/
public class LoginFrame extends JFrame {
    private JPanel mainPanel;
    private CustomPanel logoPanel;
    private JPanel loginPanel;
    private JPanel bodyPanel;
    private JTextField uNameText;
    private JButton loginButton;
    private JButton registButton;
    private JPasswordField passWordText;
    private JLabel loginLabel;

    public LoginFrame(){
        init();
        //登录按钮点击事件
        loginButton.addActionListener(e -> {
            if("".equals(uNameText.getText())||uNameText.getText()==null){
                JOptionPane.showMessageDialog(null,"请输入用户名");
                return;
            }
            if("".equals(passWordText.getText())||passWordText.getText()==null){
                JOptionPane.showMessageDialog(null,"请输入密码");
                return;
            }
            ResultEntity resultEntity= ServiceFactory.getUserServiceInstance().login(uNameText.getText(),String.valueOf(passWordText.getText()));
            JOptionPane.showMessageDialog(null,resultEntity.getMessage());
            if(resultEntity.getCode()==0){
                //根据不同身份进入不同页面
                UserVo uv=new UserVo();
                this.dispose();
                new IndexFrame();
            }
            else {
                uNameText.setText("");
                passWordText.setText("");
            }
        });
    }

    //页面初始化方法
    public void  init(){
        setContentPane(mainPanel);
        setTitle("登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1200,800));
        setVisible(true);
        //设置图片Panel
        logoPanel.setFileName("./images/loginBack.jpg");
        //设置输入框与按钮圆角
        Border border = new RoundBorder(10,Color.decode("#838383"));
        loginButton.setBorder(border);
        registButton.setBorder(border);
        uNameText.setBorder(border);
        passWordText.setBorder(border);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
