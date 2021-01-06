package com.wt.frame;

import com.wt.component.RoundBorder;
import com.wt.entity.Product;
import com.wt.factory.ServiceFactory;
import com.wt.vo.ProDetailVo;
import com.wt.vo.WindowState;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @ClassName ProductDetail
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/6 11:19
 **/
public class ProductDetailFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField proNameText;
    private JTextField proPriceText;
    private JComboBox proTypeCombox;
    private JButton updateButton;
    private JButton CancelButton;
    private JLabel mainXField;
    private JLabel proIdLabel;
    private JLabel addDateLabel;

    ProductDetailFrame(){
        init();
        Border border2 = new RoundBorder(10, Color.decode("#838383"));
        proNameText.setBorder(border2);
        proPriceText.setBorder(border2);
        proTypeCombox.setBorder(border2);
        updateButton.setBorder(border2);
        CancelButton.setBorder(border2);
        ProDetailVo pdv = new ProDetailVo();
        Product product =ServiceFactory.getProductServiceInstance().productDetail(pdv.getproId());
        proIdLabel.setText(product.getProductId());
        proNameText.setText(product.getProductName());
        proPriceText.setText(product.getPrice().toString());
        addDateLabel.setText(product.getProductDate().toString());
        proTypeCombox.addItem(product.getProductType());
        proTypeCombox.addItem("运动产品");
        proTypeCombox.addItem("电子产品");
        proTypeCombox.addItem("机械产品");
        proTypeCombox.addItem("儿童玩具");
        proTypeCombox.addItem("床上用品");
        proTypeCombox.addItem("厨房用品");
        proTypeCombox.addItem("高科技产品");
        mainXField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                WindowState ws=new WindowState();
                ws.setustates(false);
                dispose();
            }
        });
        updateButton.addActionListener(e->{
            if("".equals(proNameText.getText())&&proNameText.getText()==null){
                JOptionPane.showMessageDialog(null,"请输入产品名称！");
                return;
            }
            if("".equals(proPriceText.getText())&&proPriceText.getText()==null){
                JOptionPane.showMessageDialog(null,"请输入产品单价！");
                return;
            }
            Product product1= Product.builder()
                    .productName(proNameText.getText())
                    .productType(proTypeCombox.getSelectedItem().toString())
                    .price(Double.parseDouble(proPriceText.getText())).build();
            ServiceFactory.getProductServiceInstance().updateProduct(product1,product.getProductId());
            JOptionPane.showMessageDialog(null,"修改成功！");
            WindowState ws=new WindowState();
            ws.setustates(false);
            dispose();
        });
    }

    void init(){
        setUndecorated(true);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,500);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new ProductDetailFrame();
    }
}
