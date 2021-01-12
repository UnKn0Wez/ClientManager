package com.wt.frame;

import com.wt.component.RoundBorder;
import com.wt.entity.Product;
import com.wt.factory.ServiceFactory;
import com.wt.vo.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.net.ServerSocket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MissionDetailFrame
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/11 19:57
 **/
public class MissionDetailFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField planNumText;
    private JTextField planProfitText;
    private JLabel planIdLabel;
    private JButton updateButton;
    private JButton CancelButton;
    private JLabel mainXField;
    private JComboBox<Product> productNameCombox;
    private JComboBox planStateCombobox;
    private JLabel startTimeLabel;
    private JTextField finishField;
    private JLabel realNameLabel;
    private JLabel planNumField;
    private JLabel planProfitField;
    private JLabel label1;
    private JLabel label2;
    private JLabel planStateLabel;
    private JLabel finishLabel;
    private JLabel productNameLabel;
    private JLabel planNumLabel;
    private JLabel planProfitLabel;
    private JTextField buyNum;
    private JLabel butNumLabel;
    private UserVo uv = new UserVo();

    public static void main(String[] args) {
        new MissionDetailFrame();
    }

    MissionDetailFrame() {
        init();
        showDetail();
        Border border = new RoundBorder(10, Color.decode("#838383"));
        planNumText.setBorder(border);
        planProfitText.setBorder(border);
        planStateCombobox.setBorder(border);
        finishField.setBorder(border);
        productNameCombox.setBorder(border);
        updateButton.setBorder(border);
        CancelButton.setBorder(border);
        mainXField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        CancelButton.addActionListener(e -> {
            dispose();
        });

        if("Client".equals(uv.getuRole())) {
            planNumText.setVisible(false);
            planProfitText.setVisible(false);
            planNumField.setVisible(false);
            planProfitField.setVisible(false);
            label1.setVisible(false);
            label2.setVisible(false);
            planStateCombobox.setVisible(false);
            finishField.setVisible(false);
            productNameCombox.setVisible(false);
            planStateLabel.setVisible(true);
            finishLabel.setVisible(true);
            productNameLabel.setVisible(true);
            planNumLabel.setVisible(false);
            planProfitLabel.setVisible(false);
            butNumLabel.setVisible(true);
            buyNum.setVisible(true);
            updateButton.setText("购买");
            updateButton.addActionListener(e->{
                if (buyNum.getText() == null || "".equals(buyNum.getText())) {
                    JOptionPane.showMessageDialog(null, "请输入你想要购买的数量！");
                    return;
                }
                OrderVo orderVo = new OrderVo();
                orderVo.setBuy_num(Integer.parseInt(buyNum.getText()));
                orderVo.setPlanId(planIdLabel.getText());
                orderVo.setContactId(ServiceFactory.getUserServiceInstance().selectContactIdByName(realNameLabel.getText()).getContactId());
                orderVo.setClientId(UserVo.clientId);
                ServiceFactory.getOrderServiceInstance().newOrder(orderVo);
                JOptionPane.showMessageDialog(null, "购买成功！");
                WindowState ws = new WindowState();
                ws.setustates(false);
                this.dispose();
            });
        }
        if("Contact".equals(uv.getuRole())){
            planNumText.setVisible(false);
            planProfitText.setVisible(false);
            planNumField.setVisible(true);
            planProfitField.setVisible(true);
            label1.setVisible(true);
            label2.setVisible(true);
            planNumLabel.setVisible(true);
            planProfitLabel.setVisible(true);
            planStateCombobox.setVisible(false);
            finishField.setVisible(false);
            productNameCombox.setVisible(false);
            planStateLabel.setVisible(true);
            finishLabel.setVisible(true);
            productNameLabel.setVisible(true);
            butNumLabel.setVisible(false);
            buyNum.setVisible(false);
            updateButton.setText("修改");
        }
        if("Admin".equals(uv.getuRole())){
            planNumText.setVisible(true);
            planProfitText.setVisible(true);
            planNumField.setVisible(true);
            planProfitField.setVisible(true);
            label1.setVisible(true);
            label2.setVisible(true);
            planStateCombobox.setVisible(true);
            finishField.setVisible(true);
            productNameCombox.setVisible(true);
            planStateLabel.setVisible(false);
            finishLabel.setVisible(false);
            productNameLabel.setVisible(false);
            planNumLabel.setVisible(false);
            planProfitLabel.setVisible(false);
            butNumLabel.setVisible(false);
            buyNum.setVisible(false);
            updateButton.setText("修改");
            updateButton.addActionListener(e -> {
                if (planNumText.getText() == null || "".equals(planNumText.getText())) {
                    JOptionPane.showMessageDialog(null, "请输入计划数量");
                    return;
                }
                if (planProfitText.getText() == null || "".equals(planProfitText.getText())) {
                    JOptionPane.showMessageDialog(null, "请输入计划利润");
                    return;
                }
                if (finishField.getText() == null || "".equals(finishField.getText())) {
                    JOptionPane.showMessageDialog(null, "请输入结束时间");
                    return;
                }
                int stateIndex = planStateCombobox.getSelectedIndex();
                int nameIndex = productNameCombox.getSelectedIndex();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                MissionVo missionVo = new MissionVo();
                missionVo.setPlanId(planIdLabel.getText());
                missionVo.setClientNum(Integer.parseInt(planNumText.getText()));
                missionVo.setPlanProfit(Double.parseDouble(planProfitText.getText()));
                missionVo.setPlanState(planStateCombobox.getItemAt(stateIndex).toString());
                try {
                    Date date = sdf.parse(finishField.getText());
                    missionVo.setFinishTime(date);
                } catch (ParseException parseException) {
                    System.err.println("日期出现错误");
                }
                missionVo.setProductId(ServiceFactory.getProductServiceInstance().selectProByName(productNameCombox.getItemAt(nameIndex).getProductName()).getProductId());
                ServiceFactory.getPlanServiceInstance().updatePlan(missionVo);
                JOptionPane.showMessageDialog(null, "修改成功！");
                WindowState ws = new WindowState();
                ws.setustates(false);
                this.dispose();
            });
        }

    }

    public void showDetail() {
        MissionVo missionVo = ServiceFactory.getPlanServiceInstance().selectPlanById(PlanDetailVo.getPlanDetailId());
        planIdLabel.setText(PlanDetailVo.getPlanDetailId());
        planNumText.setText(missionVo.getClientNum().toString());
        planProfitText.setText(missionVo.getPlanProfit().toString());
        planStateCombobox.addItem(missionVo.getPlanState());
        planNumLabel.setText(missionVo.getClientNum().toString());
        planProfitLabel.setText(missionVo.getPlanProfit().toString());
        planStateLabel.setText(missionVo.getPlanState());
        finishLabel.setText(missionVo.getFinishTime().toString());
        startTimeLabel.setText(missionVo.getStartTime().toString());
        productNameLabel.setText(missionVo.getProductName());
        finishField.setText(missionVo.getFinishTime().toString());
        productNameCombox.addItem(Product.builder().productName(missionVo.getProductName()).build());
        realNameLabel.setText(missionVo.getRealName());
        List<Product> productList = ServiceFactory.getProductServiceInstance().selectAllProduct();
        for (Product product : productList) {
            productNameCombox.addItem(product);
        }
        planStateCombobox.addItem("已完成");
        planStateCombobox.addItem("已失败");
        planStateCombobox.addItem("进行中");
    }

    public void init() {
        setUndecorated(true);
        setTitle("registerFrame");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setVisible(true);
    }



}
