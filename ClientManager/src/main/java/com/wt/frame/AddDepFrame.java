package com.wt.frame;

import com.wt.component.RoundBorder;
import com.wt.entity.Department;
import com.wt.factory.ServiceFactory;
import com.wt.vo.WindowState;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @ClassName AddDepFrame
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/6 13:27
 **/
public class AddDepFrame extends JFrame{
    private JPanel mainPanel;
    private JTextField depNameText;
    private JButton addButton;
    private JButton CancelButton;
    private JLabel mainXField;
    private JPanel outMainPanel;

    public static void main(String[] args) {
        new AddDepFrame();
    }
    AddDepFrame(){
        init();
        Border border = new RoundBorder(10, Color.decode("#838383"));
        depNameText.setBorder(border);
        addButton.setBorder(border);
        CancelButton.setBorder(border);
        mainXField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        CancelButton.addActionListener(e -> {
            dispose();
        });
        addButton.addActionListener(e -> {
            if("".equals(depNameText.getText())||depNameText.getText()==null){
                JOptionPane.showMessageDialog(null,"请输入部门名称！");
                return;
            }
            Department department = new Department();
            department.setDepName(depNameText.getText());
            ServiceFactory.getDepServiceInstance().addDep(department);
            JOptionPane.showMessageDialog(null,"添加部门成功");
            WindowState ws=new WindowState();
            ws.setustates(false);
            dispose();
        });
    }

    public void init(){
        setUndecorated(true);
        setTitle("registerFrame");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,500);
        setVisible(true);
    }
}
