package com.wt.frame;

import com.wt.component.RoundBorder;
import com.wt.vo.UserVo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @ClassName LockOnFrame
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/12 14:45
 **/
public class LockOnFrame extends JFrame{
    private JPanel mainPanel;
    private JLabel imgLabel;
    private JLabel nameLabel;
    private JButton unlockButton;
    private JPanel topPanel;
    private JLabel xxField;
    private JPasswordField passwordText;

    LockOnFrame(){
        init();
        xxField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        Border border = new RoundBorder(10, Color.decode("#838383"));
        passwordText.setBorder(border);
        unlockButton.setBorder(border);
        UserVo uv=new UserVo();
        imgLabel.setText("<html><img src='" + uv.getuImg() + "' width='160' height='160'/></html>");
        nameLabel.setText(uv.getuName());
        unlockButton.addActionListener(e->{
            if("".equals(passwordText.getText())||passwordText.getText()==null){
                JOptionPane.showMessageDialog(null,"请输入密码");
            }
            else if(!uv.getpassword().equals(passwordText.getText())){
                JOptionPane.showMessageDialog(null,"密码错误");
            }else {
                new IndexFrame();
                dispose();
            }
        });
    }

    void init(){
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(450,800));
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new LockOnFrame();
    }
}
