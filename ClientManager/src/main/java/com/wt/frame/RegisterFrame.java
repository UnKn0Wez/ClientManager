package com.wt.frame;

import com.wt.component.RoundBorder;
import com.wt.entity.Department;
import com.wt.entity.Path;
import com.wt.entity.Product;
import com.wt.entity.User;
import com.wt.factory.ServiceFactory;
import com.wt.utils.AliOSSUtil;
import com.wt.utils.CopeImageUtil;
import com.wt.utils.MyMd5Util;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName registerFrame
 * @Description TODO
 * @Author UnKnW
 * @Date 2020/12/29 14:00
 **/
public class RegisterFrame extends JFrame{
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
    private JButton contactCelButton;
    private JComboBox<Department> depCombobox;
    private JComboBox<Product> proCombobox;
    private JTextField contactNameField;
    private JPasswordField contactPwField;
    private JPasswordField contactSecPwField;
    private JTextField contactPhoneField;
    private JTextField contactRealField;
    private JLabel xxField;
    private File file;
    private String depId;
    private String productId;
    private Integer bb=0;



    public RegisterFrame() {
        init();

        CopeImageUtil copeImageUtil=new CopeImageUtil();
        Border border1=new RoundBorder(10,Color.black);
        clientRegButton.setBorder(border1);
        cancelButton.setBorder(border1);
        contactRegButton.setBorder(border1);
        contactCelButton.setBorder(border1);
        List<Department> departmentList=ServiceFactory.getDepServiceInstance().selectDepAll();
        depCombobox.removeAllItems();
        depCombobox.addItem(Department.builder().depName("请选择部门").depId("1").build());
        for (Department department:departmentList){
            depCombobox.addItem(department);
        }
        List<Product> productList=ServiceFactory.getProductServiceInstance().selectAllProduct();
        proCombobox.removeAllItems();
        proCombobox.addItem(Product.builder().productName("请选择产品").productId("1").build());
        for(Product product:productList){
            proCombobox.addItem(product);
        }
        //CopeImageUtil copeImageUtil = new CopeImageUtil();
        copeImageUtil.urlCut("https://image-un.oss-cn-zhangjiakou.aliyuncs.com/image/qzw/20201229190028.png");
        imgLabel.setText("<html><img src="+ Path.getPath() +" width='160' height='160'></html>");
        contactImg.setText("<html><img src="+ Path.getPath() +" width='160' height='160'></html>");
        //将2个单选框加入一个group
        ButtonGroup group = new ButtonGroup();
        group.add(clientRadio);
        group.add(contactRadio);
        contactRadio.addActionListener(e->{
            if(clientRadio.isSelected()){
                contactPanel.setVisible(false);
                clientPanel.setVisible(true);
                mainPanel.revalidate();
            }
            if(contactRadio.isSelected()){
                clientPanel.setVisible(false);
                contactPanel.setVisible(true);
                mainPanel.revalidate();
            }
        });
        //单选框监听，决定显示哪个注册表
        clientRadio.addActionListener(e->{
            if(clientRadio.isSelected()){
                contactPanel.setVisible(false);
                clientPanel.setVisible(true);
                mainPanel.revalidate();
            }
            if(contactRadio.isSelected()){
                clientPanel.setVisible(false);
                contactPanel.setVisible(true);
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
                    ImageIcon icon = new ImageIcon(copeImageUtil.fileCut(file.getAbsolutePath()));
                    icon.setImage(icon.getImage().getScaledInstance(100,100,100));
                    imgLabel.setText("");
                    imgLabel.setIcon(icon);
                }
                bb++;
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
                    ImageIcon icon = new ImageIcon(copeImageUtil.fileCut(file.getAbsolutePath()));
                    icon.setImage(icon.getImage().getScaledInstance(100,100,100));
                    contactImg.setText("");
                    contactImg.setIcon(icon);
                }
                bb++;
            }
        });
        xxField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

        cancelButton.addActionListener(e -> {
            dispose();
        });
        contactCelButton.addActionListener(e -> {
            dispose();
        });
        depCombobox.addActionListener(e -> {
                int index=depCombobox.getSelectedIndex();
                if(index!=0){
                    depId=depCombobox.getItemAt(index).getDepId();
                }
        });
        proCombobox.addActionListener(e->{
                int index=proCombobox.getSelectedIndex();
                if(index!=0){
                    productId=proCombobox.getItemAt(index).getProductId();
                }
        });
        clientRegButton.addActionListener(e -> {
            User client=new User();
            String pw= String.valueOf(clientPw.getPassword());
            String secPw= String.valueOf(clientSecPw.getPassword());
            if(pw.equals(secPw)){
                client.setPassword(MyMd5Util.md5(secPw));
            }else{
                JOptionPane.showMessageDialog(null,"两次密码不相同");
            }
            client.setUserName(userNameField.getText());
            client.setRealName(realNameField.getText());
            client.setUserPhone(phoneField.getText());
            client.setUserAddress(adressField.getText());
            client.setUserRole("Client");
            if(bb!=0){
                File file1=new File(copeImageUtil.fileCut(file.getAbsolutePath()));
                client.setUserImag(AliOSSUtil.ossUpload(file1));
            }else{
                client.setUserImag("https://image-un.oss-cn-zhangjiakou.aliyuncs.com/image/qzw/20201230095732.jpg");
                bb=0;
            }
            ServiceFactory.getUserServiceInstance().clientRegister(client);
            dispose();
        });
        contactRegButton.addActionListener(e->{
            User contact=new User();
            String pw= String.valueOf(contactPwField.getPassword());
            String secPw=String.valueOf(contactSecPwField.getPassword());
            if(pw.equals(secPw)){
                contact.setPassword(MyMd5Util.md5(secPw));
            }else{
                JOptionPane.showMessageDialog(null,"两次密码不相同");
            }
            contact.setUserName(contactNameField.getText());
            contact.setDepId(depId);
            contact.setProductId(productId);
            contact.setUserRole("Contact");
            contact.setUserPhone(contactPhoneField.getText());
            contact.setRealName(contactRealField.getText());
            if(bb!=0){
                File file1=new File(copeImageUtil.fileCut(file.getAbsolutePath()));
                contact.setUserImag(AliOSSUtil.ossUpload(file1));
            }else{
                contact.setUserImag("https://image-un.oss-cn-zhangjiakou.aliyuncs.com/image/qzw/20201230095732.jpg");
                bb=0;
            }
            ServiceFactory.getUserServiceInstance().contactRegister(contact);
            dispose();
        });
    }
    public void init(){
        //clientPanel.setFileName("./images/regPanel.png");
        //contactPanel.setFileName("./images/regPanel.png");
        clientPanel.repaint();
        contactPanel.repaint();
        setUndecorated(true);
        setTitle("registerFrame");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,800);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new RegisterFrame();
    }
}
