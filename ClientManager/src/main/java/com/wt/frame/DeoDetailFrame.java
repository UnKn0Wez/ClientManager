package com.wt.frame;

import com.wt.component.RoundBorder;
import com.wt.entity.Department;
import com.wt.factory.ServiceFactory;
import com.wt.utils.FormatUtil;
import com.wt.vo.DepDetailVo;
import com.wt.vo.WindowState;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @ClassName DeoDetailFrame
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/6 14:20
 **/
public class DeoDetailFrame extends JFrame{
    private JPanel outMainPanel;
    private JPanel mainPanel;
    private JTextField depIdText;
    private JTextField depNameText;
    private JButton updateButton;
    private JButton CancelButton;
    private JLabel mainXField;
    private JLabel depIdLabel;
    private JLabel depTimeLabel;

    DeoDetailFrame(){
        init();
        showDetail();
        Border border = new RoundBorder(10, Color.decode("#838383"));
        depNameText.setBorder(border);
        mainXField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        CancelButton.addActionListener(e -> {
            dispose();
        });
        updateButton.addActionListener(e -> {
            if(depNameText.getText()==null||"".equals(depNameText.getText())){
                JOptionPane.showMessageDialog(null,"请输入部门名称");
                return;
            }
            Department department =new Department();
            department.setDepName(depNameText.getText());
            department.setDepId(DepDetailVo.getDepId());
            ServiceFactory.getDepServiceInstance().update(department);
            JOptionPane.showMessageDialog(null,"修改成功！");
            WindowState ws=new WindowState();
            ws.setustates(false);
            this.dispose();
        });
    }
    public void showDetail(){
        Department department = ServiceFactory.getDepServiceInstance().selectDepById(DepDetailVo.getDepId());
        depIdLabel.setText(DepDetailVo.getDepId());
        depNameText.setText(department.getDepName());
        depTimeLabel.setText(FormatUtil.formatDate(department.getDepTime()));
    }
    public static void main(String[] args) {
        new DeoDetailFrame();
    }
    void init(){
        setUndecorated(true);
        setTitle("registerFrame");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,500);
        setVisible(true);
    }
}
