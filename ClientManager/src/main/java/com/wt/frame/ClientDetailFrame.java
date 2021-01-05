package com.wt.frame;

import com.wt.component.RoundBorder;
import com.wt.factory.ServiceFactory;
import com.wt.utils.AliOSSUtil;
import com.wt.utils.CopeImageUtil;
import com.wt.vo.ClientDetailVo;
import com.wt.vo.ClientVo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * @ClassName ClientDetailFrame
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/5 16:01
 **/
public class ClientDetailFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel contactPanel;
    private JButton clientUpdataButton;
    private JLabel clientImg;
    private JButton clientCelButton;
    private JTextField clientPhoneField;
    private JTextField clientRealField;
    private JLabel clientNameLabel;
    private JTextField clientAddressText;
    private JPanel topPanel;
    private JLabel xxField;
    private JLabel clientCreditLabel;
    private Integer bb=0;
    private File file;
    private String imgUrl;

    ClientDetailFrame() {
        init();
        showDetail();
        CopeImageUtil copeImageUtil=new CopeImageUtil();
        Border border = new RoundBorder(10, Color.decode("#838383"));
        xxField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                bb=0;
            }
        });
        clientCelButton.addActionListener(e -> {
            dispose();
            bb=0;
        });
        clientRealField.setBorder(border);
        clientPhoneField.setBorder(border);
        clientAddressText.setBorder(border);
        clientUpdataButton.setBorder(border);
        clientCelButton.setBorder(border);
        clientUpdataButton.addActionListener(e -> {
            if(bb!=0){
                File file1=new File(copeImageUtil.fileCut(file.getAbsolutePath()));
                imgUrl=AliOSSUtil.ossUpload(file1);
            }else{
                imgUrl= ClientDetailVo.getClientDetailImg();
                //<html><img src="https://image-un.oss-cn-zhangjiakou.aliyuncs.com/image/qzw16a0306d-4e34-4b2e-9bee-4f2787e35e58.png" width="160" height="160"></html>
            }
            if(clientRealField.getText()==null||"".equals(clientRealField.getText())){
                JOptionPane.showMessageDialog(null,"请输入客户真实姓名");
                return;
            }
            if(clientPhoneField.getText()==null||"".equals(clientPhoneField.getText())){
                JOptionPane.showMessageDialog(null,"请输入客户手机号码");
                return;
            }
            if(clientAddressText.getText()==null||"".equals(clientAddressText.getText())){
                JOptionPane.showMessageDialog(null,"请输入客户联系地址");
                return;
            }
            ClientDetailVo clientDetailVo = new ClientDetailVo();
            ClientVo clientVo=ClientVo.builder()
                    .realName(clientRealField.getText())
                    .userPhone(clientPhoneField.getText())
                    .clientAddress(clientAddressText.getText())
                    .userImg(imgUrl)
                    .clientId(clientDetailVo.getClientDetailId())
                    .build();
            ServiceFactory.getUserServiceInstance().updateClient(clientVo);
            dispose();
        });


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
    }
    public void showDetail(){
        ClientDetailVo clientDetailVo = new ClientDetailVo();
        ClientVo clientVo = ServiceFactory.getUserServiceInstance().selectByClient(clientDetailVo.getClientDetailId());
        clientNameLabel.setText(clientVo.getUserName());
        clientRealField.setText(clientVo.getRealName());
        clientPhoneField.setText(clientVo.getUserPhone());
        clientAddressText.setText(clientVo.getClientAddress());
        clientCreditLabel.setText(clientVo.getClientCredit());
        clientImg.setText("<html><img src='"+clientVo.getUserImg()+"' width='160' height='160'></html>");
    }
    public static void main(String[] args) {
        new ClientDetailFrame();
    }

    public void init() {
        setUndecorated(true);
        setTitle("registerFrame");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setVisible(true);
    }

}
