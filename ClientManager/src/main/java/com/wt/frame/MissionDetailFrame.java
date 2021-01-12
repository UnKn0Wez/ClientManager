package com.wt.frame;

import com.wt.entity.Product;
import com.wt.factory.ServiceFactory;
import com.wt.vo.MissionVo;
import com.wt.vo.PlanDetailVo;
import com.wt.vo.UserVo;
import com.wt.vo.WindowState;

import javax.swing.*;
import java.awt.event.*;
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
    private JLabel planNumLabel;
    private JLabel planProfitLabel;
    private JLabel label1;
    private JLabel label2;
    private UserVo uv = new UserVo();

    public static void main(String[] args) {
        new MissionDetailFrame();
    }

    MissionDetailFrame() {
        init();
        showDetail();
        mainXField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        CancelButton.addActionListener(e -> {
            dispose();
        });
        CancelButton.addActionListener(e -> {
            dispose();
        });

        if("Client".equals(uv.getuRole())) {
            updateButton.addActionListener(e->{
                dispose();
            });
            planNumText.setVisible(false);
            planProfitText.setVisible(false);
            planNumLabel.setVisible(false);
            planProfitLabel.setVisible(false);
            label1.setVisible(false);
            label2.setVisible(false);
        }
        if("Admin".equals(uv.getuRole())||"Contact".equals(uv.getuRole())){
            planNumText.setVisible(true);
            planProfitText.setVisible(true);
            planNumLabel.setVisible(true);
            planProfitLabel.setVisible(true);
            label1.setVisible(true);
            label2.setVisible(true);
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
        startTimeLabel.setText(missionVo.getStartTime().toString());
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
        setSize(600, 800);
        setVisible(true);
    }


}
