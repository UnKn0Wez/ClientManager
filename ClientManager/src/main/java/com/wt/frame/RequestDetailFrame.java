package com.wt.frame;

import com.wt.entity.Request;
import com.wt.factory.ServiceFactory;
import com.wt.vo.RequestDetailVo;
import com.wt.vo.WindowState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * @ClassName RequestDetailFrame
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/12 10:58
 **/
public class RequestDetailFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JLabel xxField;
    private JLabel xxxField;
    private JPanel contactPanel;
    private JTextField id_text;
    private JButton request_Btn;
    private JTextArea requestContentText;
    private JTextField timeText;

    RequestDetailFrame(){
        init();
        List<Request> requestList= ServiceFactory.getRequestServiceInstanct().searchRequest(RequestDetailVo.getrequestId(),null);
        xxxField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                WindowState ws=new WindowState();
                ws.setaddStates(false);
                dispose();
            }
        });
        id_text.setText(requestList.get(0).getOrder_id());
        timeText.setText(requestList.get(0).getRequest_time().toString());
        requestContentText.setText(requestList.get(0).getRequest_content());
        request_Btn.addActionListener(e->{
            WindowState ws=new WindowState();
            ws.setaddStates(false);
            dispose();
        });
    }

    void init(){
        this.setContentPane(mainPanel);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(600,500));
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new RequestDetailFrame();
    }
}
