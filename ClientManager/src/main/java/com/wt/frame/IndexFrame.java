package com.wt.frame;

import com.wt.component.RoundBorder;
import com.wt.entity.Department;
import com.wt.entity.Product;
import com.wt.factory.ServiceFactory;
import com.wt.vo.ClientVo;
import com.wt.vo.UserDetailVo;
import com.wt.vo.UserVo;
import com.wt.thread.ContactDetailDispose;
import com.wt.utils.ShowValuesUtil;
import com.wt.vo.*;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JPanel productSearchPanel;
    private JTextField productNameField;
    private JComboBox productTypeCombo;
    private JButton productSearchButton;
    private JPanel productContentPanel;
    private JPanel productBodyPanel;
    private JButton addProductButton;
    private JButton productDetailButton;
    private final CardLayout C;
    private UserVo uv = new UserVo();
    private int contact_id;
    private JTable Contact_table;
    private JTable Client_table;
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
        Border border = new RoundBorder(250, Color.black);
        Border border1 = new RoundBorder(15, Color.decode("#E2E2E2"));
        Border border2 = new RoundBorder(10, Color.decode("#838383"));
        contactSearchButton.setBorder(border2);
        depSearchCombox.setBorder(border2);
        contactSearchText.setBorder(border2);
        contactProSearchCombo.setBorder(border2);
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
        productSearchPanel.setBorder(border1);
        productBodyPanel.setBorder(border1);
        headLabel.setBorder(border);
        clientCreditCombobox.addItem("信任");
        clientCreditCombobox.addItem("不信任");
        headLabel.setText("<html><img src='" + uv.getuImg() + "' width='160' height='160'/></html>");
        loginName.setText(uv.getuName());
        contactComboxInit();
        proComboxInit();
        //创建CardLayout
        C = new CardLayout();
        indexPanel.setLayout(C);
        indexPanel.add("1", contactPanel);
        indexPanel.add("2", clientPanel);
        indexPanel.add("3", productPanel);
        indexPanel.add("4", requestPanel);
        indexPanel.add("5", missionPanel);
        indexPanel.add("6", depPanel);
        indexPanel.add("7", strongPanel);
        hidePanel();
        ShowValuesUtil svu = new ShowValuesUtil();
        contactLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "1");
                svu.showContact(ServiceFactory.getUserServiceInstance().selectAll(), contactContentPanel, contactBodyPanel);
            }
        });
        clientLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "2");
                showClient(ServiceFactory.getUserServiceInstance().selectClientAll());
            }
        });
        productLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                C.show(indexPanel, "3");
                svu.showProducts(ServiceFactory.getProductServiceInstance().selectAllProduct(), productContentPanel,productBodyPanel);
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
        svu.showContact(ServiceFactory.getUserServiceInstance().selectAll(), contactContentPanel, contactBodyPanel);
        //联系人详细页面切换
        contactDetail_button.addActionListener(e -> {
            MyTable myTable = new MyTable();
            JTable Contact_table = myTable.getuContact_table();
            if (Contact_table.getSelectedRowCount() == 1) {
                contact_id = Contact_table.getSelectedRow();
                UserDetailVo udv = new UserDetailVo();
                udv.setdetail_Id(Contact_table.getModel().getValueAt(contact_id, 0).toString());
                ContactDetailDispose cdd = new ContactDetailDispose();
                new ContactDetailFrame();
                WindowState ws = new WindowState();
                ws.setustates(true);
                cdd.setCdf(true);
                cdd.setcontentPanel(contactContentPanel, contactBodyPanel, depPanel);
                new Thread(cdd).start();
            } else {
                JOptionPane.showMessageDialog(null, "请选择一行数据！");
            }
        });
        clientSelectButton.addActionListener(e -> {
            String realName = clientSearchText.getText();
            String address = clientAddressText.getText();
            int index = clientCreditCombobox.getSelectedIndex();
            ClientCredit = clientCreditCombobox.getItemAt(index);
            showClient(ServiceFactory.getUserServiceInstance().selectByClient(realName, address, ClientCredit));
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
        clientDetailButton.addActionListener(e -> {
            if(Client_table.getRowCount()==1){
                int index=Client_table.getSelectedRow();
                ClientDetailVo cdv = new ClientDetailVo();
                cdv.setClientDetailId(Client_table.getValueAt(index,0).toString());
                new ClientDetailFrame();
            }else{
                JOptionPane.showMessageDialog(null,"清选择一条数据");
                return;
            }
        });
    }

    public void showClient(List<ClientVo> clientVos) {
        clientBodyPanel.removeAll();
        TableModel tableModel;
        tableModel = new DefaultTableModel();
        Client_table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DefaultTableModel model = new DefaultTableModel();
        Client_table.setModel(model);
        model.setColumnIdentifiers(new String[]{"客户编号", "用户名", "客户姓名", "电话号码", "信任度", "家庭地址"});
        for (ClientVo clientVo : clientVos) {
            Object[] objects = new Object[]{
                    clientVo.getClientId(), clientVo.getUserName(),
                    clientVo.getRealName(), clientVo.getUserPhone(),
                    clientVo.getClientCredit(), clientVo.getClientAddress(),
            };
            model.addRow(objects);
        }
        JTableHeader header = Client_table.getTableHeader();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setFont(new Font("楷体", Font.PLAIN, 18));
        Client_table.setTableHeader(header);
        Client_table.setRowHeight(35);
        Client_table.setBackground(Color.white);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        Client_table.setDefaultRenderer(Object.class, r);
        Client_table.setBackground(Color.white);
        Client_table.setPreferredSize(new Dimension(clientContentPanel.getWidth(), clientContentPanel.getHeight()));
        JPanel mypanel = new JPanel(new BorderLayout());
        mypanel.setPreferredSize(new Dimension(300, Client_table.getRowCount() * Client_table.getRowHeight()));
        mypanel.add(header, BorderLayout.NORTH);
        mypanel.add(Client_table, BorderLayout.CENTER);
        JScrollPane scrollPanel = new JScrollPane(mypanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setPreferredSize(new Dimension(Client_table.getWidth(), Client_table.getHeight()));
        scrollPanel.setBackground(Color.white);
        clientBodyPanel.add(scrollPanel);
        clientBodyPanel.revalidate();
        clientBodyPanel.repaint();
        Client_table.getSelectionModel().addListSelectionListener(e -> {
        });
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("删除");
        JMenuItem outItem = new JMenuItem("导出");
        jPopupMenu.add(deleteItem);
        jPopupMenu.add(outItem);
        Client_table.add(jPopupMenu);
        Client_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = Client_table.getSelectedRow();
                if (e.getButton() == 3) {
                    jPopupMenu.show(Client_table, e.getX(), e.getY());
                    if (Client_table.getSelectedRowCount() == 1) {
                        clientId = Client_table.getValueAt(index, 0).toString();
                        deleteItem.addActionListener(e1 -> {
                            int choice = JOptionPane.showConfirmDialog(depPanel, "确定删除吗？");
                            if (choice == 0) {
                                ServiceFactory.getUserServiceInstance().deleteClient(clientId);
                                JOptionPane.showMessageDialog(null, "删除客户成功");
                                clientBodyPanel.removeAll();
                                showClient(ServiceFactory.getUserServiceInstance().selectClientAll());
                                clientBodyPanel.revalidate();
                                clientBodyPanel.repaint();
                            }
                        });
                    }
                }
            }
        });
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