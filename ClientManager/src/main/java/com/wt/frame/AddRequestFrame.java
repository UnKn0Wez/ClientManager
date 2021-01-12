package com.wt.frame;

import com.wt.component.RoundBorder;
import com.wt.entity.Request;
import com.wt.factory.ServiceFactory;
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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName AddRequestFrame
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/12 9:34
 **/
public class AddRequestFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JLabel xxField;
    private JLabel xxxField;
    private JPanel contactPanel;
    private JTextField id_text;
    private JButton request_Btn;
    private JTextArea requestContentText;

    AddRequestFrame(){
        init();
        Border border = new RoundBorder(10, Color.decode("#838383"));
        id_text.setBorder(border);
        requestContentText.setBorder(border);
        request_Btn.setBorder(border);
        id_text.setText(OrderDetailVo.getorder_id());
        xxxField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                WindowState ws=new WindowState();
                ws.setaddStates(false);
                dispose();
            }
        });
        request_Btn.addActionListener(e->{
            UserVo uv=new UserVo();
            Date date=new Date();
            Request request=Request.builder()
                    .client_id(uv.getclientId())
                    .request_content(requestContentText.getText())
                    .order_id(id_text.getText())
                    .request_time(date)
                    .build();
            ServiceFactory.getRequestServiceInstanct().addRequest(request);
            JOptionPane.showMessageDialog(null,"添加成功！");
            WindowState ws=new WindowState();
            ws.setaddStates(false);
            this.dispose();
        });
    }

    void init(){
        this.setContentPane(mainPanel);
        this.setUndecorated(true);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(600,500));
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new AddRequestFrame();
    }
}
