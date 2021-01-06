package com.wt.frame;

import com.wt.component.RoundBorder;
import com.wt.entity.Product;
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
 * @ClassName AddProductFrame
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/6 8:55
 **/
public class AddProductFrame extends JFrame{
    private JPanel mainPanel;
    private JTextField proNameText;
    private JTextField proPriceText;
    private JComboBox proTypeCombox;
    private JButton addButton;
    private JButton CancelButton;
    private JLabel mainXField;

    AddProductFrame(){
        init();
        Border border2 = new RoundBorder(10, Color.decode("#838383"));
        proNameText.setBorder(border2);
        proPriceText.setBorder(border2);
        proTypeCombox.setBorder(border2);
        addButton.setBorder(border2);
        CancelButton.setBorder(border2);
        proComboxInit();
        mainXField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                WindowState ws=new WindowState();
                ws.setustates(false);
                dispose();
            }
        });
        CancelButton.addActionListener(e->{
            WindowState ws=new WindowState();
            ws.setustates(false);
            dispose();
        });
        addButton.addActionListener(e->{
            if("".equals(proNameText.getText())||proNameText==null){
                JOptionPane.showMessageDialog(null,"请输入产品名称");
                return;
            }
            if("".equals(proPriceText.getText())||proPriceText==null){
                JOptionPane.showMessageDialog(null,"请输入产品单价");
                return;
            }
            if(proTypeCombox.getSelectedItem().toString()=="请选择产品类型"){
                JOptionPane.showMessageDialog(null,"请选择产品类型");
                return;
            }
            Product product= Product.builder()
                    .productName(proNameText.getText())
                    .price(Double.parseDouble(proPriceText.getText()))
                    .productType(proTypeCombox.getSelectedItem().toString()).build();
            ServiceFactory.getProductServiceInstance().addProduct(product);
            JOptionPane.showMessageDialog(null,"添加产品成功！");
            WindowState ws=new WindowState();
            ws.setustates(false);
            dispose();
        });
    }


    public void proComboxInit() {
        proTypeCombox.addItem("请选择产品类型");
        proTypeCombox.addItem("运动产品");
        proTypeCombox.addItem("电子产品");
        proTypeCombox.addItem("机械产品");
        proTypeCombox.addItem("儿童玩具");
        proTypeCombox.addItem("床上用品");
        proTypeCombox.addItem("厨房用品");
        proTypeCombox.addItem("高科技产品");
    }

    void init(){
        setUndecorated(true);
        setTitle("registerFrame");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,500);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddProductFrame();
    }
}
