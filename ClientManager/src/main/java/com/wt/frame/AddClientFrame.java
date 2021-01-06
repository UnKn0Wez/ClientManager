package com.wt.frame;

import com.wt.component.RoundBorder;
import com.wt.entity.User;
import com.wt.factory.DaoFactory;
import com.wt.factory.ServiceFactory;
import com.wt.utils.AliOSSUtil;
import com.wt.utils.CopeImageUtil;
import com.wt.utils.MyMd5Util;
import com.wt.vo.ClientVo;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName AddClientFrame
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/6 8:39
 **/
public class AddClientFrame extends JFrame{
    private JPanel contactPanel;
    private JButton clientRegButton;
    private JLabel clientImg;
    private JButton clientCelButton;
    private JTextField clientPhoneField;
    private JTextField clientRealField;
    private JTextField addressText;
    private JTextField clientNameField;
    private JPasswordField clientPwField;
    private JPasswordField clientPasswordField;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JLabel xxField;
    private JLabel xxxField;
    private final String phoneMatcher="^1[3|4|5|7|8][0-9]{9}$";
    private Integer bb=0;
    private File file;
    AddClientFrame(){
        init();
        CopeImageUtil copeImageUtil=new CopeImageUtil();
        Border border = new RoundBorder(10, Color.decode("#838383"));
        xxField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        clientNameField.setBorder(border);
        clientPwField.setBorder(border);
        clientPasswordField.setBorder(border);
        clientRealField.setBorder(border);
        clientPhoneField.setBorder(border);
        addressText.setBorder(border);
        clientRegButton.setBorder(border);
        clientCelButton.setBorder(border);
        clientImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("C:/Users/public/Pictures"));
                int result = fileChooser.showOpenDialog(rootPane);
                if(result == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(copeImageUtil.fileCut(file.getAbsolutePath()));
                    icon.setImage(icon.getImage().getScaledInstance(100,100,100));
                    clientImg.setText("");
                    clientImg.setIcon(icon);
                }
                bb++;
            }
        });
        clientRegButton.addActionListener(e -> {
            if("".equals(clientNameField.getText())||clientNameField.getText()==null){
                JOptionPane.showMessageDialog(null,"请输入用户名！");
                return;
            }
            if("".equals(clientPwField.getText())||clientPwField.getText()==null){
                JOptionPane.showMessageDialog(null,"请输入密码！");
                return;
            }
            if("".equals(clientPasswordField.getText())||clientPasswordField.getText()==null){
                JOptionPane.showMessageDialog(null,"请确认密码！");
                return;
            }
            if("".equals(clientRealField.getText())||clientRealField.getText()==null){
                JOptionPane.showMessageDialog(null,"请输入客户真实姓名！");
                return;
            }
            if("".equals(clientPhoneField.getText())||clientPhoneField.getText()==null){
                JOptionPane.showMessageDialog(null,"请输入手机号码！");
                return;
            }
            if("".equals(addressText.getText())||addressText.getText()==null){
                JOptionPane.showMessageDialog(null,"请输入联系地址！");
                return;
            }
            Pattern pattern = Pattern.compile(phoneMatcher);
            Matcher matcher = pattern.matcher(clientPhoneField.getText());
            if(!matcher.find()){
                JOptionPane.showMessageDialog(null,"请输入正确的手机格式！");
                return;
            }
            try {
                User user= DaoFactory.getUserDaoInstance().logins(clientNameField.getText());
                if(user!=null){
                    JOptionPane.showMessageDialog(null,"用户名已存在！");
                    return;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            User client=new User();
            String pw= String.valueOf(clientPwField.getPassword());
            String secPw=String.valueOf(clientPasswordField.getPassword());
            if(pw.equals(secPw)){
                client.setPassword(MyMd5Util.md5(secPw));
            }else{
                JOptionPane.showMessageDialog(null,"两次密码不相同");
            }
            client.setUserName(clientNameField.getText());
            client.setUserRole("Client");
            client.setUserPhone(clientPhoneField.getText());
            client.setRealName(clientRealField.getText());
            client.setUserAddress(addressText.getText());
            if(bb!=0){
                File file1=new File(copeImageUtil.fileCut(file.getAbsolutePath()));
                client.setUserImag(AliOSSUtil.ossUpload(file1));
            }else{
                client.setUserImag("https://image-un.oss-cn-zhangjiakou.aliyuncs.com/image/qzw/20201230095732.jpg");
                bb=0;
            }
            ServiceFactory.getUserServiceInstance().clientRegister(client);
            JOptionPane.showMessageDialog(null,"添加用户成功");
            WindowState ws=new WindowState();
            ws.setustates(false);
            dispose();
        });
        clientCelButton.addActionListener(e -> {
            dispose();
        });
    }
    public static void main(String[] args) {
        new AddClientFrame();
    }
    public void init(){
        setUndecorated(true);
        setTitle("registerFrame");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,800);
        setVisible(true);
    }
}
