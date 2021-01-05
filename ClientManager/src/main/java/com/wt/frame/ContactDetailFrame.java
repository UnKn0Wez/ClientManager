package com.wt.frame;

import com.sun.xml.internal.ws.api.Component;
import com.wt.component.RoundBorder;
import com.wt.entity.Department;
import com.wt.factory.ServiceFactory;
import com.wt.vo.ContactVo;
import com.wt.vo.UserDetailVo;
import com.wt.vo.UserVo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * @ClassName ContactDetailFrame
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/5 9:25
 **/
public class ContactDetailFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel contactPanel;
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
    private JLabel contactNameLabel;
    private JTextField salaryText;

    ContactDetailFrame(){
        init();
        Border border = new RoundBorder(10, Color.decode("#838383"));
        contactCelButton.setBorder(border);
        contactRegButton.setBorder(border);
        depCombobox.setBorder(border);
        contactPhoneField.setBorder(border);
        proCombobox.setBorder(border);
        contactRealField.setBorder(border);
        proCombobox.setBorder(border);
        salaryText.setBorder(border);
        xxxField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        contactCelButton.addActionListener(e->{
            this.dispose();
        });
        showDetails();

    }

    public void showDetails(){
        UserDetailVo udv=new UserDetailVo();
        if(udv.getdetail_Id()!=null){
            ContactVo cv=ServiceFactory.getUserServiceInstance().selectByContact(udv.getdetail_Id());
            contactImg.setText("<html><img src='" + cv.getUserImag() + "' width='160' height='160'/></html>");
            contactNameLabel.setText(cv.getUserName());
            contactRealField.setText(cv.getRealName());
            contactPhoneField.setText(cv.getUserPhone());
            salaryText.setText(cv.getSalary().toString());
            depCombobox.addItem(cv.getDepName());
            depInit();
            proCombobox.addItem(cv.getProductName());
        }
    }

    public void depInit(){
        List<Department> departmentList = ServiceFactory.getDepServiceInstance().selectDepAll();
        for (Department department : departmentList) {
            depCombobox.addItem(department);
        }
    }

    public void productInit(){
        List<Department> departmentList = ServiceFactory.getDepServiceInstance().selectDepAll();
        for (Department department : departmentList) {
            depCombobox.addItem(department);
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
