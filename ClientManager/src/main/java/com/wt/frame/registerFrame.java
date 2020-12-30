package com.wt.frame;

import com.wt.component.CustomPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * @ClassName registerFrame
 * @Description TODO
 * @Author UnKnW
 * @Date 2020/12/29 14:00
 **/
public class registerFrame extends JFrame{
    private JPanel mainPanel;
    private CustomPanel clientPanel;
    private CustomPanel contactPanel;
    private JTextField userNameField;
    private JPasswordField clientPw;
    private JPasswordField clientSecPw;
    private JTextField realNameField;
    private JTextField phoneField;
    private JTextField adressField;
    private JLabel clientTitle;
    private JButton clientRegButton;
    private JLabel imgLabel;
    private JRadioButton contactRadio;
    private JRadioButton clientRadio;
    private JPanel topPanel;
    private JLabel contactImg;
    private File file;


    public registerFrame() {

        init();
        ButtonGroup group = new ButtonGroup();
        group.add(clientRadio);
        group.add(contactRadio);
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

    }
    public void init(){
        clientPanel.setFileName("./images/regPanel.png");
        contactPanel.setFileName("./images/regPanel.png");
        clientPanel.repaint();
        contactPanel.repaint();
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
