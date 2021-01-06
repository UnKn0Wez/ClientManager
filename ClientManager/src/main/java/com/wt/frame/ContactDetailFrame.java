package com.wt.frame;

import com.wt.component.RoundBorder;
import com.wt.entity.Department;
import com.wt.entity.Product;
import com.wt.entity.User;
import com.wt.factory.ServiceFactory;
import com.wt.utils.AliOSSUtil;
import com.wt.utils.CopeImageUtil;
import com.wt.vo.ContactVo;
import com.wt.vo.UserDetailVo;
import com.wt.vo.WindowState;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
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
    private JComboBox<Department> depCombobox;
    private JComboBox<Product> proCombobox;
    private JTextField contactPhoneField;
    private JTextField contactRealField;
    private JLabel xxField;
    private JPanel topPanel;
    private JLabel xxxField;
    private JLabel contactNameLabel;
    private JTextField salaryText;
    private String imgUrl;
    private File file;
    private Integer bb=0;
    private String user_id;

    ContactDetailFrame(){
        init();
        CopeImageUtil copeImageUtil=new CopeImageUtil();
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
        contactRegButton.addActionListener(e->{
            if(contactRealField.getText()==null||"".equals(contactRealField.getText())){
                JOptionPane.showMessageDialog(null,"请输入联系人真实姓名");
                return;
            }
            if(contactPhoneField.getText()==null||"".equals(contactPhoneField.getText())){
                JOptionPane.showMessageDialog(null,"请输入联系人手机号码");
                return;
            }
            if(salaryText.getText()==null||"".equals(salaryText.getText())){
                JOptionPane.showMessageDialog(null,"请输入联系人工资");
                return;
            }
            if(bb!=0){
                File file1=new File(copeImageUtil.fileCut(file.getAbsolutePath()));
                imgUrl=AliOSSUtil.ossUpload(file1);
            }
            int index=depCombobox.getSelectedIndex();
            int index1=proCombobox.getSelectedIndex();
            User user= User.builder()
                    .realName(contactRealField.getText())
                    .userPhone(contactPhoneField.getText())
                    .salary(Double.parseDouble(salaryText.getText()))
                    .userImag(imgUrl)
                    .depId(depCombobox.getItemAt(index).getDepId())
                    .productId(proCombobox.getItemAt(index1).getProductId())
                    .build();
            ServiceFactory.getUserServiceInstance().updateContact(user_id,user);
            JOptionPane.showMessageDialog(null,"修改成功！");
            WindowState ws=new WindowState();
            ws.setustates(false);
            this.dispose();
        });

        contactImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("C:/Users/public/Pictures"));
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
            imgUrl=cv.getUserImag();
            user_id=cv.getUserId();
            Product product= Product.builder()
                    .productId(cv.getProId())
                    .productName(cv.getProductName())
                    .build();
            Department department= Department.builder()
                    .depId(cv.getDepId())
                    .depName(cv.getDepName())
                    .build();
            depCombobox.addItem(department);
            depInit();
            proCombobox.addItem(product);
            productInit();
        }
    }

    public void depInit(){
        List<Department> departmentList = ServiceFactory.getDepServiceInstance().selectDepAll();
        for (Department department : departmentList) {
            depCombobox.addItem(department);
        }
    }

    public void productInit(){
        List<Product> productlist = ServiceFactory.getProductServiceInstance().selectAllProduct();
        for (Product product : productlist) {
            proCombobox.addItem(product);
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
