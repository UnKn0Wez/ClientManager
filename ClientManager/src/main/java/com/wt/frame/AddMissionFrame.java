package com.wt.frame;

import com.eltima.components.ui.DatePicker;
import com.wt.entity.Mission;
import com.wt.entity.Product;
import com.wt.factory.ServiceFactory;
import com.wt.vo.ContactVo;
import com.wt.vo.WindowState;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @ClassName AddMissionFrame
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/11 20:21
 **/
public class AddMissionFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField planProfitText;
    private JButton addButton;
    private JButton CancelButton;
    private JLabel mainXField;
    private JComboBox<String> productTypeCombox;
    private JPanel datePanel;
    private JComboBox<Product> productNameCombobox;
    private JComboBox<ContactVo> realNameCombobox;
    private JTextField clientNumText;

    public static void main(String[] args) {
        new AddMissionFrame();
    }

    AddMissionFrame() {
        init();
        mainXField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        CancelButton.addActionListener(e -> {
            dispose();
        });
        productTypeCombox.addActionListener(e -> {
            productNameCombobox.removeAllItems();
            productNameCombobox.addItem(Product.builder().productName("请选择产品").build());
            int typeIndex = productTypeCombox.getSelectedIndex();
            List<Product> productList = ServiceFactory.getProductServiceInstance().searchProduct("",productTypeCombox.getItemAt(typeIndex));
            for (Product product : productList) {
                productNameCombobox.addItem(product);
            }
        });
        DatePicker datePicker = getDatePicker();
        datePanel.setPreferredSize(new Dimension(300, 50));
        datePanel.add(datePicker);
        datePanel.revalidate();
        productTypeCombox.addItem("请选择产品类型");
        productTypeCombox.addItem("运动产品");
        productTypeCombox.addItem("电子产品");
        productTypeCombox.addItem("机械产品");
        productTypeCombox.addItem("儿童玩具");
        productTypeCombox.addItem("床上用品");
        productTypeCombox.addItem("厨房用品");
        productTypeCombox.addItem("高科技产品");
        productNameCombobox.addItem(Product.builder().productName("请选择产品").build());


        addButton.addActionListener(e -> {
            int typeIndex = productTypeCombox.getSelectedIndex();
            int nameIndex = productNameCombobox.getSelectedIndex();
            if ("".equals(planProfitText.getText()) && planProfitText.getText() == null) {
                JOptionPane.showMessageDialog(null, "请输入计划利润！");
            }
            if ("".equals(clientNumText.getText()) && clientNumText.getText() == null) {
                JOptionPane.showMessageDialog(null, "请输入计划数量！");
            }
            if (typeIndex == 0) {
                JOptionPane.showMessageDialog(null, "请选择产品类型！");
            }
            if (nameIndex == 0) {
                JOptionPane.showMessageDialog(null, "请选择产品名称！");
            }
            Mission mission=new Mission();
            mission.setClientNum(Integer.parseInt(clientNumText.getText()));
            mission.setPlanProfit(Double.parseDouble(planProfitText.getText()));
            mission.setFinishTime((Date)datePicker.getValue());
            mission.setPlanState("进行中");
            mission.setProductId(productNameCombobox.getItemAt(nameIndex).getProductId());
            ServiceFactory.getPlanServiceInstance().newPlan(mission);
            JOptionPane.showMessageDialog(null,"新增任务计划成功!");
            WindowState ws=new WindowState();
            ws.setustates(false);
            dispose();
        });
    }

    private static DatePicker getDatePicker() {
        final DatePicker datePicker;
        // 格式
        String defaultFormat = "yyyy-MM-dd";
        // 当前时间
        Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.PLAIN, 18);
        Dimension dimension = new Dimension(245, 30);
        int[] hilightDays = {1, 3, 5, 7};
        int[] disabledDays = {4, 6, 5, 9};
        //构造方法（初始时间，时间显示格式，字体，控件大小）
        datePicker = new DatePicker(date, defaultFormat, font, dimension);
        //设置起始位置
        // datePicker.setLocation(137, 83);
        //也可用setBounds()直接设置大小与位置
        //datePicker.setBounds(137, 83, 177, 24);
        // 设置一个月份中需要高亮显示的日子
        datePicker.setHightlightdays(hilightDays, Color.red);
        // 设置一个月份中不需要的日子，呈灰色显示
        datePicker.setDisableddays(disabledDays);
        // 设置国家
        datePicker.setLocale(Locale.CHINA);
        // 设置时钟面板可见
        // datePicker.setTimePanleVisible(true);
        return datePicker;
    }

    public void init() {
        setUndecorated(true);
        setTitle("registerFrame");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setVisible(true);
    }
}
