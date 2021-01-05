package com.wt.frame;

import com.sun.xml.internal.ws.api.Component;
import com.wt.factory.ServiceFactory;
import com.wt.vo.ContactVo;
import com.wt.vo.UserDetailVo;
import com.wt.vo.UserVo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @ClassName ContactDetailFrame
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/5 9:25
 **/
public class ContactDetailFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel contactPanel;
    private JTextField contactNameField;
    private JPasswordField contactPwField;
    private JPasswordField contactSecPwField;
    private JButton contactRegButton;
    private JLabel contactImg;
    private JButton contactCelButton;
    private JComboBox depCombobox;
    private JComboBox proCombobox;
    private JTextField contactPhoneField;
    private JTextField contactRealField;
    private JLabel xxField;
    private JPanel topPanel;
    private JLabel xxxField;

    ContactDetailFrame(){
        init();
        xxxField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
    }

    public void showDetails(){
        UserDetailVo udv=new UserDetailVo();
        if(udv.getdetail_Id()!=null){
            ContactVo cv= (ContactVo) ServiceFactory.getUserServiceInstance().selectByContact(udv.getdetail_Id());

        }
    }

    public void init(){
        setUndecorated(true);
        setTitle("registerFrame");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,800);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ContactDetailFrame();
    }
}
