package com.wt.frame;

import com.wt.component.RoundBorder;
import com.wt.entity.Department;
import com.wt.entity.Product;
import com.wt.factory.ServiceFactory;
import com.wt.thread.*;
import com.wt.vo.UserDetailVo;
import com.wt.vo.UserVo;
import com.wt.utils.ShowValuesUtil;
import com.wt.vo.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDate;
import java.util.List;

/**
 * @ClassName IndexFrame
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/30 9:21
 **/
public class IndexFrame extends JFrame {
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
    private JButton contactSearchButton;
    private JComboBox<Department> depSearchCombox;
    private JButton addContact_button;
    private JButton contactDetail_button;
    private JPanel contactBodyPanel;
    private JLabel xField;
    private JPanel clientContentPanel;
    private JPanel clientBodyPanel;
    private JPanel clientSearchPanel;
    private JTextField clientSearchText;
    private JTextField buyTimeText;
    private JTextField clientAddressText;
    private JComboBox<String> clientCreditCombobox;
    private JComboBox<Product> contactProSearchCombo;
    private JPanel rightPanel;
    private JButton clientSelectButton;
    private JLabel mainXLabel;
    private JButton clientDetailButton;
    private JButton newClientButton;
    private JPanel depBodyPanel;
    private JPanel depContentPanel;
    private JPanel depSearchPanel;
    private JPanel productSearchPanel;
    private JTextField productNameField;
    private JComboBox<String> productTypeCombo;
    private JButton productSearchButton;
    private JPanel productContentPanel;
    private JPanel productBodyPanel;
    private JButton addProductButton;
    private JButton productDetailButton;
    private JTextField depSearchText;
    private JTextField depTimeSearch;
    private JButton depSearchButton;
    private JButton depDetailButton;
    private JButton newDepButton;
    private JComboBox<String> depTimeCombobox;
    private JPanel orderPanel;
    private JLabel orderLabel;
    private JPanel orderSearchPanel;
    private JPanel orderContentPanel;
    private JPanel orderBodyPanel;
    private JLabel 产品名称;
    private JTextField orderContactText;
    private JComboBox orderTypeCombox;
    private JTextField orderProdoductText;
    private JButton orderSearchBtn;
    private JButton orderDetailBtn;
    private JPanel planContentPanel;
    private JPanel planSearchPanel;
    private JPanel planBodyPanel;
    private JTextField planRealNameField;
    private JTextField planProductName;
    private JComboBox<String> planProductType;
    private JComboBox<String> planState;
    private JButton addPlanButton;
    private JButton planSearchButton;
    private JButton planDetailButton;
    private JButton requestDetailBtn;
    private JPanel requestSearchPanel;
    private JTextField requestClientText;
    private JTextField requestOrderText;
    private JButton requestSearchBtn;
    private JPanel requestContentPanel;
    private JPanel requestBodyPanel;
    private final CardLayout C;
    private final UserVo uv = new UserVo();
    private int contact_id;
    private String ClientCredit;
    private String clientId;

    IndexFrame() {
        init();
        mainXLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        depTimeCombobox.addItem("请选择年份");
        for (int i = 2010; i <= LocalDate.now().getYear(); i++) {
            depTimeCombobox.addItem(String.valueOf(i));
        }
        Border border = new RoundBorder(250, Color.black);
        Border border1 = new RoundBorder(15, Color.decode("#E2E2E2"));
        Border border2 = new RoundBorder(10, Color.decode("#838383"));

        requestClientText.setBorder(border2);
        requestOrderText.setBorder(border2);
        requestSearchBtn.setBorder(border2);
        requestDetailBtn.setBorder(border2);
        orderSearchBtn.setBorder(border2);
        orderContactText.setBorder(border2);
        orderTypeCombox.setBorder(border2);
        orderProdoductText.setBorder(border2);
        orderDetailBtn.setBorder(border2);
        contactSearchButton.setBorder(border2);
        depSearchCombox.setBorder(border2);
        contactSearchText.setBorder(border2);
        contactProSearchCombo.setBorder(border2);
        planSearchPanel.setBorder(border1);
        planContentPanel.setBorder(border1);
        addContact_button.setBorder(border2);
        contactDetail_button.setBorder(border2);
        clientSearchText.setBorder(border2);
        clientAddressText.setBorder(border2);
        clientCreditCombobox.setBorder(border2);
        productNameField.setBorder(border2);
        productTypeCombo.setBorder(border2);
        addProductButton.setBorder(border2);
        productSearchButton.setBorder(border2);
        productDetailButton.setBorder(border2);
        contactSearchPanel.setBorder(border1);
        contactContentPanel.setBorder(border1);
        clientContentPanel.setBorder(border1);
        clientSearchPanel.setBorder(border1);
        clientDetailButton.setBorder(border2);
        clientSelectButton.setBorder(border2);
        newClientButton.setBorder(border2);
        productSearchPanel.setBorder(border1);
        productContentPanel.setBorder(border1);
        orderSearchPanel.setBorder(border1);
        orderContentPanel.setBorder(border1);
        headLabel.setBorder(border);
        depContentPanel.setBorder(border1);
        depSearchPanel.setBorder(border1);
        requestSearchPanel.setBorder(border1);
        requestContentPanel.setBorder(border1);
        depSearchText.setBorder(border2);
        depTimeCombobox.setBorder(border2);
        depSearchButton.setBorder(border2);
        depDetailButton.setBorder(border2);
        planDetailButton.setBorder(border2);
        newDepButton.setBorder(border2);
        planRealNameField.setBorder(border2);
        planProductType.setBorder(border2);
        planState.setBorder(border2);
        planSearchButton.setBorder(border2);
        addPlanButton.setBorder(border2);
        clientCreditCombobox.addItem("信任");
        clientCreditCombobox.addItem("不信任");
        headLabel.setText("<html><img src='" + uv.getuImg() + "' width='160' height='160'/></html>");
        loginName.setText(uv.getuName());
        contactComboxInit();
        proComboxInit();
        proTypeComboxInit();
        planProductTypeInit();
        planStateInit();
        //创建CardLayout
        C = new CardLayout();
        indexPanel.setLayout(C);
        indexPanel.add("1", contactPanel);
        indexPanel.add("2", clientPanel);
        indexPanel.add("3", productPanel);
        indexPanel.add("4", requestPanel);
        indexPanel.add("5", missionPanel);
        indexPanel.add("6", depPanel);
        indexPanel.add("7", orderPanel);
        indexPanel.add("8", strongPanel);
        hidePanel();
        ShowValuesUtil svu = new ShowValuesUtil();
        contactLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "1");
                contactBodyPanel.removeAll();
                svu.showContact(ServiceFactory.getUserServiceInstance().selectAll(), contactContentPanel, contactBodyPanel);
                contactBodyPanel.revalidate();
                contactBodyPanel.repaint();
            }
        });
        clientLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "2");
                svu.showClient(ServiceFactory.getUserServiceInstance().selectClientAll(), clientContentPanel, clientBodyPanel);
            }
        });
        productLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "3");
                productBodyPanel.removeAll();
                svu.showProducts(ServiceFactory.getProductServiceInstance().selectAllProduct(), productContentPanel,productBodyPanel);
                productBodyPanel.revalidate();
                productBodyPanel.repaint();
            }
        });
        requestLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "4");
                requestBodyPanel.removeAll();
                svu.showRequest(ServiceFactory.getRequestServiceInstanct().selectAllRequest(), requestContentPanel,requestBodyPanel);
                requestBodyPanel.revalidate();
                requestBodyPanel.repaint();
            }
        });
        missionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "5");
                svu.showPlan(ServiceFactory.getPlanServiceInstance().selectAll(), planContentPanel, planBodyPanel);
            }
        });
        depLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "6");
                svu.showDep(ServiceFactory.getDepServiceInstance().selectDepAll(), depContentPanel, depBodyPanel);
            }
        });
        orderLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "7");
                orderBodyPanel.removeAll();
                    svu.showOrders(ServiceFactory.getOrderServiceInstance().selectAllOrder(), orderContentPanel,orderBodyPanel);
                orderBodyPanel.revalidate();
                orderBodyPanel.repaint();
            }
        });
        strongLael.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "8");
            }
        });
        svu.showContact(ServiceFactory.getUserServiceInstance().selectAll(), contactContentPanel, contactBodyPanel);
        svu.showClient(ServiceFactory.getUserServiceInstance().selectClientAll(), clientContentPanel, clientBodyPanel);
        svu.showRequest(ServiceFactory.getRequestServiceInstanct().selectAllRequest(), requestContentPanel, requestBodyPanel);

        addContact_button.addActionListener(e -> {
            ContactDetailDispose cdd = new ContactDetailDispose();
            new AddContactFrame();
            WindowState ws = new WindowState();
            ws.setustates(true);
            cdd.setCdf(true);
            cdd.setcontentPanel(contactContentPanel, contactBodyPanel);
            new Thread(cdd).start();
            new Thread(cdd).stop();
        });
        clientSelectButton.addActionListener(e -> {
            String realName = clientSearchText.getText();
            String address = clientAddressText.getText();
            int index = clientCreditCombobox.getSelectedIndex();
            ClientCredit = clientCreditCombobox.getItemAt(index);
            svu.showClient(ServiceFactory.getUserServiceInstance().selectByClient(realName, address, ClientCredit));
            clientBodyPanel.revalidate();
            clientBodyPanel.repaint();

        });
        contactSearchButton.addActionListener(e -> {
            int index = contactProSearchCombo.getSelectedIndex();
            int index1 = depSearchCombox.getSelectedIndex();
            contactBodyPanel.removeAll();
            svu.showContact(ServiceFactory.getUserServiceInstance().searchInfo(contactSearchText.getText(), depSearchCombox.getItemAt(index1).getDepId(), contactProSearchCombo.getItemAt(index).getProductId()), contactContentPanel, contactBodyPanel);
            contactBodyPanel.revalidate();
            contactBodyPanel.repaint();
        });
        //联系人详细页面切换
        contactDetail_button.addActionListener(e -> {
            ContactDetailDispose cdd = new ContactDetailDispose();
            MyTable myTable = new MyTable();
            JTable Contact_table = myTable.getuContact_table();
            if (Contact_table.getSelectedRowCount() == 1) {
                contact_id = Contact_table.getSelectedRow();
                UserDetailVo udv = new UserDetailVo();
                udv.setdetail_Id(Contact_table.getModel().getValueAt(contact_id, 0).toString());
                new ContactDetailFrame();
                WindowState ws = new WindowState();
                ws.setustates(true);
                cdd.setCdf(true);
                cdd.setcontentPanel(contactContentPanel, contactBodyPanel);
                new Thread(cdd).start();
                new Thread(cdd).stop();
            } else {
                JOptionPane.showMessageDialog(null, "请选择一行数据！");
            }
        });
        //客户详细页面切换
        clientDetailButton.addActionListener(e -> {
            ClientDetailDispose cdd = new ClientDetailDispose();
            MyTable myTable = new MyTable();
            JTable clientTable = myTable.getClient_table();
            if (clientTable.getSelectedRowCount() == 1) {
                int index = clientTable.getSelectedRow();
                ClientDetailVo cdv = new ClientDetailVo();
                cdv.setClientDetailId(clientTable.getValueAt(index, 0).toString());
                WindowState ws = new WindowState();
                ws.setustates(true);
                new ClientDetailFrame();
                cdd.setAll(true, clientContentPanel, clientBodyPanel);
                new Thread(cdd).start();
                new Thread(cdd).stop();
            } else {
                JOptionPane.showMessageDialog(null, "请选择一条数据");
                return;
            }
        });
        //新增客户
        newClientButton.addActionListener(e -> {
            new AddClientFrame();
            ClientDetailDispose cdd = new ClientDetailDispose();
            WindowState ws = new WindowState();
            ws.setustates(true);
            cdd.setAll(true, clientContentPanel, clientBodyPanel);
            new Thread(cdd).start();
            new Thread(cdd).stop();
        });
        //部门查询按钮监听
        depSearchButton.addActionListener(e -> {
            int index = depTimeCombobox.getSelectedIndex();
            depBodyPanel.removeAll();
            if (index != 0) {
                svu.showDep(ServiceFactory.getDepServiceInstance().selectDep(depSearchText.getText(), Integer.parseInt(depTimeCombobox.getItemAt(index))));
            } else {
                svu.showDep(ServiceFactory.getDepServiceInstance().selectDep(depSearchText.getText(), 0));
            }
            depBodyPanel.revalidate();
            depBodyPanel.repaint();
        });
        //任务计划页面查询
        planSearchButton.addActionListener(e -> {
            planBodyPanel.removeAll();
            int typeIndex = planProductType.getSelectedIndex();
            int stateIndex = planState.getSelectedIndex();
            svu.showPlan(ServiceFactory.getPlanServiceInstance().searchPlan(planState.getItemAt(stateIndex), planProductType.getItemAt(typeIndex), planRealNameField.getText()));
            planBodyPanel.revalidate();
            planBodyPanel.repaint();
        });
        addProductButton.addActionListener(e -> {
            ProductDetailDispose pdd = new ProductDetailDispose();
            new AddProductFrame();
            WindowState ws = new WindowState();
            ws.setustates(true);
            pdd.setAll(true, productContentPanel, productBodyPanel);
            new Thread(pdd).start();
            new Thread(pdd).stop();
        });

        //新增部门按钮监听
        newDepButton.addActionListener(e -> {
            DepDetailDispose pdd = new DepDetailDispose();
            new AddDepFrame();
            WindowState ws = new WindowState();
            ws.setustates(true);
            pdd.setAll(true, depContentPanel, depBodyPanel);
            new Thread(pdd).start();
            new Thread(pdd).stop();
        });
        //部门详细信息界面切换
        depDetailButton.addActionListener(e -> {
            DepDetailDispose ddd = new DepDetailDispose();
            MyTable myTable = new MyTable();
            JTable Contact_table = myTable.getDep_table();
            if (Contact_table.getSelectedRowCount() == 1) {
                int index = Contact_table.getSelectedRow();
                DepDetailVo.setDepId(Contact_table.getValueAt(index, 0).toString());
                WindowState ws = new WindowState();
                ws.setustates(true);
                new DeoDetailFrame();
                ddd.setAll(true, depContentPanel, depBodyPanel);
                new Thread(ddd).start();
                new Thread(ddd).stop();
            } else {
                JOptionPane.showMessageDialog(null, "请选择一条数据");
                return;
            }
        });
        //订单查询按钮监听
        orderSearchBtn.addActionListener(e->{
                orderBodyPanel.removeAll();
                    svu.showOrders(ServiceFactory.getOrderServiceInstance().searchOrder(orderContactText.getText(),orderTypeCombox.getSelectedItem().toString(),orderProdoductText.getText()),orderContentPanel,orderBodyPanel);
                orderBodyPanel.revalidate();
                orderBodyPanel.repaint();
        });
        //订单查看详细按钮监听
        orderDetailBtn.addActionListener(e->{
            OrderDetailDispose odd = new OrderDetailDispose();
            MyTable myTable = new MyTable();
            JTable Contact_table = myTable.getuOrder_table();
            if(Contact_table.getSelectedRowCount()==1){
                int index=Contact_table.getSelectedRow();
                OrderDetailVo.setorder_id(Contact_table.getValueAt(index,0).toString());
                WindowState ws=new WindowState();
                ws.setustates(true);
                ws.setaddStates(true);
                new OrderDetailFrame();
                PanelVo.setrequestBodyPanel(requestBodyPanel);
                PanelVo.setrequestContentPanel(requestContentPanel);
                PanelVo.setorderBodyPanel(orderBodyPanel);
                PanelVo.setorderContentPanel(orderContentPanel);
                odd.setAll(true,orderContentPanel,orderBodyPanel);
                new Thread(odd).start();
                new Thread(odd).stop();
            }else{
                JOptionPane.showMessageDialog(null,"清选择一条数据");
                return;
            }
        });
        //任务计划详细信息界面切换
        planDetailButton.addActionListener(e -> {
            PlanDetailDispose pdd = new PlanDetailDispose();
            MyTable myTable = new MyTable();
            JTable Contact_table = myTable.getDep_table();
            if (Contact_table.getSelectedRowCount() == 1) {
                int index = Contact_table.getSelectedRow();
                PlanDetailVo.setPlanDetailId(Contact_table.getValueAt(index, 0).toString());
                WindowState ws = new WindowState();
                ws.setustates(true);
                new MissionDetailFrame();
                pdd.setAll(true, planContentPanel, planBodyPanel);
                new Thread(pdd).start();
                new Thread(pdd).stop();
            } else {
                JOptionPane.showMessageDialog(null, "请选择一条数据");
                return;
            }
        });
        if("Admin".equals(uv.getuRole())||"Contact".equals(uv.getuRole())){
            addPlanButton.setVisible(true);
        }
        if("Client".equals(uv.getuRole())){
            addPlanButton.setVisible(false);
        }
        //反馈信息查询按钮监听
        requestSearchBtn.addActionListener(e->{
            requestBodyPanel.removeAll();
            svu.showRequest(ServiceFactory.getRequestServiceInstanct().searchRequest(requestClientText.getText(),requestOrderText.getText()),requestContentPanel,requestBodyPanel);
            requestBodyPanel.revalidate();
            requestBodyPanel.repaint();
        });
        //反馈详细按钮监听
        requestDetailBtn.addActionListener(e->{
            RequestDetailDispose rdd = new RequestDetailDispose();
            MyTable myTable = new MyTable();
            JTable Contact_table = myTable.getRequest_table();
            if(Contact_table.getSelectedRowCount()==1){
                int index=Contact_table.getSelectedRow();
                RequestDetailVo.setrequestId(Contact_table.getValueAt(index,0).toString());
                WindowState ws=new WindowState();
                ws.setaddStates(true);
                new RequestDetailFrame();
                rdd.setAll(true,requestContentPanel,requestBodyPanel);
                new Thread(rdd).start();
                new Thread(rdd).stop();
            }else{
                JOptionPane.showMessageDialog(null,"请选择一条数据");
                return;
            }
        });
        //产品详细按钮监听
        productDetailButton.addActionListener(e->{
            ProductDetailDispose pdd = new ProductDetailDispose();
            MyTable myTable = new MyTable();
            JTable Contact_table = myTable.getProduct_table();
            if (Contact_table.getSelectedRowCount() == 1) {
                int index = Contact_table.getSelectedRow();
                ProDetailVo pdv=new ProDetailVo();
                pdv.setproId(Contact_table.getValueAt(index, 0).toString());
                WindowState ws = new WindowState();
                ws.setustates(true);
                new ProductDetailFrame();
                pdd.setAll(true, productContentPanel, productBodyPanel);
                new Thread(pdd).start();
                new Thread(pdd).stop();
            } else {
                JOptionPane.showMessageDialog(null, "请选择一条数据");
                return;
            }
        });
    }

    public void proTypeComboxInit() {
        productTypeCombo.addItem("请选择产品类型");
        productTypeCombo.addItem("运动产品");
        productTypeCombo.addItem("电子产品");
        productTypeCombo.addItem("机械产品");
        productTypeCombo.addItem("儿童玩具");
        productTypeCombo.addItem("床上用品");
        productTypeCombo.addItem("厨房用品");
        productTypeCombo.addItem("高科技产品");


        orderTypeCombox.addItem("请选择产品类型");
        orderTypeCombox.addItem("运动产品");
        orderTypeCombox.addItem("电子产品");
        orderTypeCombox.addItem("机械产品");
        orderTypeCombox.addItem("儿童玩具");
        orderTypeCombox.addItem("床上用品");
        orderTypeCombox.addItem("厨房用品");
        orderTypeCombox.addItem("高科技产品");
    }

    public void planProductTypeInit() {
        planProductType.addItem("请选择产品类型");
        planProductType.addItem("运动产品");
        planProductType.addItem("电子产品");
        planProductType.addItem("机械产品");
        planProductType.addItem("儿童玩具");
        planProductType.addItem("床上用品");
        planProductType.addItem("厨房用品");
        planProductType.addItem("高科技产品");
    }

    public void planStateInit() {
        planState.addItem("请选择完成情况");
        planState.addItem("已完成");
        planState.addItem("已失败");
        planState.addItem("进行中");
    }

    public void proComboxInit() {
        contactProSearchCombo.addItem(Product.builder().productName("请选择产品").productId("1").build());
        List<Product> productList = ServiceFactory.getProductServiceInstance().selectAllProduct();
        for (Product product : productList) {
            contactProSearchCombo.addItem(product);
        }
    }


    public void contactComboxInit() {
        depSearchCombox.addItem(Department.builder().depName("请选择部门").depId("1").build());
        List<Department> departmentList = ServiceFactory.getDepServiceInstance().selectDepAll();
        for (Department department : departmentList) {
            depSearchCombox.addItem(department);
        }
    }

    //根据身份显示不同的页面
    public void hidePanel() {
        String role = uv.getuRole();
        if ("Admin".equals(role)) {
            C.show(indexPanel, "1");
            return;
        }
        if ("Client".equals(role)) {
            C.show(indexPanel, "4");
            clientLabel.setVisible(false);
            contactLabel.setVisible(false);
            productLabel.setVisible(false);
            depLabel.setVisible(false);
            return;
        }
        if ("Contact".equals(role)) {
            C.show(indexPanel, "2");
            contactLabel.setVisible(false);
            productLabel.setVisible(false);
            depLabel.setVisible(false);
            return;
        }
    }

    public void init() {
        setContentPane(mainPanel);
        setUndecorated(true);
        setTitle("主页");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1200, 800));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new IndexFrame();
    }
}