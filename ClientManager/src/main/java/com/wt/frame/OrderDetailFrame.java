package com.wt.frame;

import com.sun.org.apache.xpath.internal.operations.Equals;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.wt.component.RoundBorder;
import com.wt.entity.Department;
import com.wt.entity.Order;
import com.wt.factory.ServiceFactory;
import com.wt.utils.FormatUtil;
import com.wt.vo.DepDetailVo;
import com.wt.vo.OrderDetailVo;
import com.wt.vo.UserVo;
import com.wt.vo.WindowState;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @ClassName OrderDetail
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/11 19:00
 **/
public class OrderDetailFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JLabel xxField;
    private JLabel xxxField;
    private JPanel contactPanel;
    private JTextField client_text;
    private JTextField contact_text;
    private JTextField type_text;
    private JButton request_Btn;
    private JButton close_Btn;
    private JTextField id_text;
    private JTextField product_text;
    private JTextField price_text;
    private JTextField num_text;
    private JTextField cost_text;
    private JTextField time_text;
    private JLabel contact_label;
    private JLabel client_label;

    OrderDetailFrame(){
        init();
        xxxField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                WindowState ws=new WindowState();
                ws.setustates(false);
                dispose();
            }
        });
        Border border = new RoundBorder(10, Color.decode("#838383"));
        client_text.setBorder(border);
        contact_text.setBorder(border);
        type_text.setBorder(border);
        request_Btn.setBorder(border);
        close_Btn.setBorder(border);
        id_text.setBorder(border);
        product_text.setBorder(border);
        price_text.setBorder(border);
        num_text.setBorder(border);
        cost_text.setBorder(border);
        time_text.setBorder(border);
        UserVo uv=new UserVo();
        String urole=uv.getuRole();
        String uid=uv.getclientId();

        close_Btn.addActionListener(e->{
            WindowState ws=new WindowState();
            ws.setustates(false);
            dispose();
        });
        showDetail();
    }
    public void showDetail(){
        UserVo uv=new UserVo();
        Order order = ServiceFactory.getOrderServiceInstance().OrderDetail(OrderDetailVo.getorder_id());
        client_text.setText(order.getClient_name());
        contact_text.setText(order.getContact_name());
        id_text.setText(order.getOrder_id());
        product_text.setText(order.getProduct_name());
        type_text.setText(order.getProduct_type());
        price_text.setText(order.getPrice().toString()+"￥");
        num_text.setText(String.valueOf(order.getBuy_num()));
        cost_text.setText(String.valueOf(order.getBuy_num()*order.getPrice())+"￥");
        time_text.setText(String.valueOf(order.getBuy_time()));
        if("Admin".equals(uv.getuRole())){
            contact_label.setVisible(false);
            contact_text.setVisible(false);
            request_Btn.setVisible(false);
        }
        if("Contact".equals(uv.getuRole())){
            contact_label.setVisible(false);
            contact_text.setVisible(false);
            request_Btn.setVisible(false);
        }
        if("Client".equals(uv.getuRole())){
            client_label.setVisible(false);
            client_text.setVisible(false);
        }
    }

    void init(){
        setUndecorated(true);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,800);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new OrderDetailFrame();
    }
}
