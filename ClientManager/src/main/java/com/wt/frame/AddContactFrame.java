package com.wt.frame;

import com.wt.component.RoundBorder;
import com.wt.entity.Department;
import com.wt.entity.Product;
import com.wt.entity.User;
import com.wt.factory.DaoFactory;
import com.wt.factory.ServiceFactory;
import com.wt.utils.AliOSSUtil;
import com.wt.utils.CopeImageUtil;
import com.wt.utils.MyMd5Util;
import com.wt.vo.WindowState;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName AddContactFrame
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/5 13:39
 **/
public class AddContactFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel contactPanel;
    private JButton contactRegButton;
    private JLabel contactImg;
    private JButton contactCelButton;
    private JComboBox<Department> depCombobox;
    private JComboBox<Product> proCombobox;
    private JTextField contactPhoneField;
    private JTextField contactRealField;
    private JTextField salaryText;
    private JPanel topPanel;
    private JLabel xxField;
    private JLabel xxxField;
    private JTextField contactNameField;
    private JPasswordField contactPwField;
    private JPasswordField confirmPasswordField;
    private Integer bb=0;
    private File file;
    private String depId;
    private String productId;
    private final String phoneMatcher="^1[3|4|5|7|8][0-9]{9}$";


    AddContactFrame(){
        init();
        CopeImageUtil copeImageUtil=new CopeImageUtil();
        Border border = new RoundBorder(10, Color.decode("#838383"));
        contactCelButton.setBorder(border);
        contactRegButton.setBorder(border);
        depCombobox.setBorder(border);
        contactPhoneField.setBorder(border);
        proCombobox.setBorder(border);
        contactRealField.setBorder(border);
        contactPwField.setBorder(border);
        confirmPasswordField.setBorder(border);
        contactNameField.setBorder(border);
        salaryText.setBorder(border);
        proComboxInit();
        contactComboxInit();
        xxxField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                WindowState ws=new WindowState();
                ws.setustates(false);
                dispose();
            }
        });
        contactCelButton.addActionListener(e->{
            WindowState ws=new WindowState();
            ws.setustates(false);
            this.dispose();
        });

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
        contactRegButton.addActionListener(e->{
            if("".equals(contactNameField.getText())||contactNameField.getText()==null){
                JOptionPane.showMessageDialog(null,"请输入用户名！");
                return;
            }
            if("".equals(contactPwField.getText())||contactPwField.getText()==null){
                JOptionPane.showMessageDialog(null,"请输入密码！");
                return;
            }
            if("".equals(confirmPasswordField.getText())||confirmPasswordField.getText()==null){
                JOptionPane.showMessageDialog(null,"请确认密码！");
                return;
            }
            if("".equals(contactRealField.getText())||contactRealField.getText()==null){
                JOptionPane.showMessageDialog(null,"请输入联系人真实姓名！");
                return;
            }
            if("".equals(contactPhoneField.getText())||contactPhoneField.getText()==null){
                JOptionPane.showMessageDialog(null,"请输入手机号码！");
                return;
            }
            if("".equals(salaryText.getText())||salaryText.getText()==null){
                JOptionPane.showMessageDialog(null,"请输入工资！");
                return;
            }
            if(depId=="1"){
                JOptionPane.showMessageDialog(null,"请选择部门！");
                return;
            }
            if(productId=="1"){
                JOptionPane.showMessageDialog(null,"请选择产品！");
                return;
            }
            Pattern pattern = Pattern.compile(phoneMatcher);
            Matcher matcher = pattern.matcher(contactPhoneField.getText());
            if(!matcher.find()){
                JOptionPane.showMessageDialog(null,"请输入正确的手机格式！");
                return;
            }
            try {
                User user= DaoFactory.getUserDaoInstance().logins(contactNameField.getText());
                if(user!=null){
                    JOptionPane.showMessageDialog(null,"用户名已存在！");
                    return;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            User contact=new User();
            String pw= String.valueOf(contactPwField.getPassword());
            String secPw=String.valueOf(confirmPasswordField.getPassword());
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
            JOptionPane.showMessageDialog(null,"添加用户成功");
            WindowState ws=new WindowState();
            ws.setustates(false);
            dispose();
        });
    }


    public void proComboxInit() {
        proCombobox.addItem(Product.builder().productName("请选择产品").productId("1").build());
        this.productId="1";
        java.util.List<Product> productList = ServiceFactory.getProductServiceInstance().selectAllProduct();
        for (Product product : productList) {
            proCombobox.addItem(product);
        }
    }

    public void contactComboxInit() {
        depCombobox.addItem(Department.builder().depName("请选择部门").depId("1").build());
        this.depId="1";
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
        new AddContactFrame();
    }
}
