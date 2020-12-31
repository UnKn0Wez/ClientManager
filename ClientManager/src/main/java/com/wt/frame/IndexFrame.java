package com.wt.frame;

import com.wt.component.RoundBorder;
import com.wt.vo.UserVo;
import org.apache.poi.ss.formula.functions.Index;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @ClassName IndexFrame
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/30 9:21
 **/
public class IndexFrame extends JFrame{
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JLabel headLabel;
    private JLabel loginName;
    private JPanel indexPanel;
    private JPanel contactPanel;
    private JPanel clientPanel;
    private JPanel productPanel;
    private JPanel requestPanel;
    private JPanel missionPanel;
    private JPanel depPanel;
    private JPanel strongPanel;
    private JLabel contactLabel;
    private JLabel clientLabel;
    private JLabel productLabel;
    private JLabel requestLabel;
    private JLabel missionLabel;
    private JLabel depLabel;
    private JLabel strongLael;
    private JPanel contactSearchPanel;
    private JPanel contactContentPanel;
    private JTextField contactSearchText;
    private JTextField contactProSearchText;
    private JButton contactSearchButton;
    private JComboBox depSearchCombox;
    private final CardLayout C;
    private UserVo uv=new UserVo();

    IndexFrame(){
        init();
        Border border=new RoundBorder(250,Color.black);
        Border border1=new RoundBorder(15,Color.decode("#E2E2E2"));
        Border border2 = new RoundBorder(10,Color.decode("#838383"));
        contactSearchButton.setBorder(border2);
        depSearchCombox.setBorder(border2);
        contactSearchText.setBorder(border2);
        contactProSearchText.setBorder(border2);
        contactSearchPanel.setBorder(border1);
        contactContentPanel.setBorder(border1);
        headLabel.setBorder(border);
        headLabel.setText("<html><img src='"+uv.getuImg()+"' width='160' height='160'/></html>");
        loginName.setText(uv.getuName());

        //创建CardLayout
        C = new CardLayout();
        indexPanel.setLayout(C);
        indexPanel.add("1",contactPanel);
        indexPanel.add("2",clientPanel);
        indexPanel.add("3",productPanel);
        indexPanel.add("4",requestPanel);
        indexPanel.add("5",missionPanel);
        indexPanel.add("6",depPanel);
        indexPanel.add("7",strongPanel);
        hidePanel();
        contactLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "1");
            }
        });
        clientLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "2");
            }
        });
        productLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "3");
            }
        });
        requestLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "4");
            }
        });
        missionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "5");
            }
        });
        depLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "6");
            }
        });
        strongLael.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "7");
            }
        });
    }

    public void contactComboxInit(){
        //List<>
    }

    //根据身份显示不同的页面
    public void hidePanel(){
        String role=uv.getuRole();
        if("Admin".equals(role)){
            C.show(indexPanel, "1");
            return;
        }
        if("Client".equals(role)){
            C.show(indexPanel, "4");
            clientLabel.setVisible(false);
            contactLabel.setVisible(false);
            productLabel.setVisible(false);
            depLabel.setVisible(false);
            return;
        }
        if("Contact".equals(role)){
            C.show(indexPanel, "2");
            contactLabel.setVisible(false);
            productLabel.setVisible(false);
            depLabel.setVisible(false);
            return;
        }
    }

    public void init(){
        setContentPane(mainPanel);
        setTitle("主页");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1200,800));
        setVisible(true);
    }

    public static void main(String[] args) {
        new IndexFrame();
    }
}
