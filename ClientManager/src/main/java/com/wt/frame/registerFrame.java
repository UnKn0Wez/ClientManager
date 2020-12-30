package com.wt.frame;

import com.wt.component.CustomPanel;
import com.wt.component.RoundBorder;
import com.wt.entity.User;
import com.wt.factory.ServiceFactory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * @ClassName registerFrame
 * @Description TODO
 * @Author UnKnW
 * @Date 2020/12/29 14:00
 **/
public class registerFrame extends JFrame{
    private JPanel mainPanel;
    private JPanel clientPanel;
    private JPanel contactPanel;
    private JTextField userNameField;
    private JPasswordField clientPw;
    private JPasswordField clientSecPw;
    private JTextField realNameField;
    private JTextField phoneField;
    private JTextField adressField;
    private JButton clientRegButton;
    private JLabel imgLabel;
    private JRadioButton contactRadio;
    private JRadioButton clientRadio;
    private JPanel topPanel;
    private JLabel contactImg;
    private JButton contactRegButton;
    private JButton cancelButton;
    private JButton 取消Button;
    private File file;



    public registerFrame() {
        init();
        Border border=new RoundBorder(200);
        Border border1=new RoundBorder(10);
        imgLabel.setBorder(border);
        clientRegButton.setBorder(border1);
        cancelButton.setBorder(border1);
        //将2个单选框加入一个group
        ButtonGroup group = new ButtonGroup();
        group.add(clientRadio);
        group.add(contactRadio);
        contactRadio.addActionListener(e->{
            if(clientRadio.isSelected()){
                contactPanel.setVisible(true);
                clientPanel.setVisible(false);
                mainPanel.revalidate();
            }
            if(contactRadio.isSelected()){
                clientPanel.setVisible(true);
                contactPanel.setVisible(false);
                mainPanel.revalidate();
            }
        });
        //单选框监听，决定显示哪个注册表
        clientRadio.addActionListener(e->{
            if(clientRadio.isSelected()){
                contactPanel.setVisible(true);
                clientPanel.setVisible(false);
                mainPanel.revalidate();
            }
            if(contactRadio.isSelected()){
                clientPanel.setVisible(true);
                contactPanel.setVisible(false);
                mainPanel.revalidate();
            }
        });
        //客户头像选择监听
        imgLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("C:/Users/UnKnW/Pictures"));
                int result = fileChooser.showOpenDialog(rootPane);
                if(result == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                    icon.setImage(icon.getImage().getScaledInstance(100,100,100));
                    imgLabel.setText("");
                    imgLabel.setIcon(icon);
                }
            }
        });
        //员工头像选择监听
        contactImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("C:/Users/UnKnW/Pictures"));
                int result = fileChooser.showOpenDialog(rootPane);
                if(result == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                    icon.setImage(icon.getImage().getScaledInstance(100,100,100));
                    contactImg.setText("");
                    contactImg.setIcon(icon);
                }
            }
        });

        clientRegButton.addActionListener(e -> {
            User client=new User();
            client.setUserName(userNameField.getText());
            client.setPassword(Arrays.toString(clientSecPw.getPassword()));
            client.setRealName(realNameField.getText());
            client.setUserPhone(phoneField.getText());
            client.setUserAddress(adressField.getText());
            ServiceFactory.getUserServiceInstance().clientRegister(client);
            dispose();
        });
        cancelButton.addActionListener(e -> {
            dispose();
        });
    }
    public void init(){
        //clientPanel.setFileName("./images/regPanel.png");
        //contactPanel.setFileName("./images/regPanel.png");
        clientPanel.repaint();
        contactPanel.repaint();
        setTitle("registerFrame");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,800);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new registerFrame();
    }
}
