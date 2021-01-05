package com.wt.frame;

import com.wt.component.RoundBorder;
import com.wt.entity.Department;
import com.wt.entity.User;
import com.wt.factory.ServiceFactory;
import com.wt.vo.ContactVo;
import com.wt.vo.UserDetailVo;
import com.wt.vo.UserVo;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;
import javax.xml.ws.Service;
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
    private JTextField contactProSearchText;
    private JButton contactSearchButton;
    private JComboBox<Department> depSearchCombox;
    private JButton addContact_button;
    private JButton contactDetail_button;
    private JPanel contactBodyPanel;
    private JLabel xField;
    private JPanel clientContentPanel;
    private JPanel clientBodyPanel;
    private JPanel clientSearchPanel;
    private final CardLayout C;
<<<<<<< HEAD
    private UserVo uv = new UserVo();
=======
    private UserVo uv=new UserVo();
    private Integer contact_id;
    JTable Contact_table;
>>>>>>> 7b606bf03209e3e17884ef219e493800932c52dc

    IndexFrame() {
        init();
        xField.addMouseListener(new MouseAdapter() {
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
        contactProSearchText.setBorder(border2);
        addContact_button.setBorder(border2);
        contactDetail_button.setBorder(border2);
        contactSearchPanel.setBorder(border1);
        contactContentPanel.setBorder(border1);
        headLabel.setBorder(border);
        headLabel.setText("<html><img src='" + uv.getuImg() + "' width='160' height='160'/></html>");
        loginName.setText(uv.getuName());
        contactComboxInit();
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
        //联系人详细页面切换
        contactDetail_button.addActionListener(e->{
            if(Contact_table.getSelectedRowCount()==1){
                contact_id=Contact_table.getSelectedRow();
                UserDetailVo udv=new UserDetailVo();
                udv.setdetail_Id(Contact_table.getModel().getValueAt(contact_id,0).toString());

            }
            else {
                JOptionPane.showMessageDialog(null,"请选择一行数据！");
            }
        });
        showContact(ServiceFactory.getUserServiceInstance().selectAll());
    }

    public void showContact(List<ContactVo> contacts) {
        TableModel tableModel;
        tableModel = new DefaultTableModel();
<<<<<<< HEAD
        JTable table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        model.setColumnIdentifiers(new String[]{"员工编号", "用户名", "员工姓名", "电话号码", "所属部门", "负责产品", "工资", ""});
=======
        Contact_table= new JTable(tableModel){ @Override
        public boolean isCellEditable(int row, int column) { return false; }};
        DefaultTableModel model = new DefaultTableModel();
        Contact_table.setModel(model);
        model.setColumnIdentifiers(new String[]{"员工编号","用户名","员工姓名","电话号码","所属部门","负责产品","工资",""});
>>>>>>> 7b606bf03209e3e17884ef219e493800932c52dc
        for (ContactVo contact : contacts) {
            Object[] objects = new Object[]{
                    contact.getContactId(),
                    contact.getRealName(), contact.getUserName(),
                    contact.getUserPhone(), contact.getDepName(),
                    contact.getProductName(), contact.getSalary()
            };
            model.addRow(objects);
        }
<<<<<<< HEAD
        JTableHeader header= table.getTableHeader();
        scrollPanel(table,header);
        table.setPreferredSize(new Dimension(contactContentPanel.getWidth(), contactContentPanel.getHeight()));
        JPanel myPanel = new JPanel(new BorderLayout());
        myPanel.setPreferredSize(new Dimension(300, table.getRowCount() * table.getRowHeight()));
        myPanel.add(header, BorderLayout.NORTH);
        myPanel.add(table, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(myPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(table.getWidth(), table.getHeight()));
=======
        TableColumn tc = Contact_table.getColumnModel().getColumn(7);
        tc.setMaxWidth(0);
        tc.setMinWidth(0);
        JTableHeader header = Contact_table.getTableHeader();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setFont(new Font("楷体", Font.PLAIN, 18));
        Contact_table.setTableHeader(header);
        Contact_table.setRowHeight(35);
        Contact_table.setBackground(Color.white);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        Contact_table.setDefaultRenderer(Object.class, r);
        Contact_table.setBackground(Color.white);
        Contact_table.setPreferredSize(new Dimension(contactContentPanel.getWidth(),contactContentPanel.getHeight()));
        JPanel mypane=new JPanel(new BorderLayout());
        mypane.setPreferredSize(new Dimension(300,Contact_table.getRowCount()*Contact_table.getRowHeight()));
        mypane.add(header,BorderLayout.NORTH);
        mypane.add(Contact_table,BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(mypane, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(Contact_table.getWidth(),Contact_table.getHeight()));
>>>>>>> 7b606bf03209e3e17884ef219e493800932c52dc
        scrollPane.setBackground(Color.white);
        contactBodyPanel.add(scrollPane);
        contactBodyPanel.revalidate();
        contactBodyPanel.repaint();
        Contact_table.getSelectionModel().addListSelectionListener(e -> {
        });
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("删除");
        jPopupMenu.add(deleteItem);
        Contact_table.add(jPopupMenu);
        //删除联系人
        Contact_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3) {
                    if(Contact_table.getSelectedRowCount()==1){
                        contact_id=Contact_table.getSelectedRow();
                        int choice = JOptionPane.showConfirmDialog(depPanel, "确定删除吗？");
                        if (choice == 0) {
                            ServiceFactory.getUserServiceInstance().deleteContact(Contact_table.getModel().getValueAt(contact_id,0).toString());
                            JOptionPane.showMessageDialog(null,"删除联系人成功");
                            contactBodyPanel.removeAll();
                            showContact(ServiceFactory.getUserServiceInstance().selectAll());
                            contactBodyPanel.revalidate();
                            contactBodyPanel.repaint();
                        }
                    }
                }
            }
        });
    }

    public static void scrollPanel(JTable table,JTableHeader header) {
        TableColumn tc = table.getColumnModel().getColumn(7);
        tc.setMaxWidth(0);
        tc.setMinWidth(0);
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setFont(new Font("楷体", Font.PLAIN, 18));
        table.setTableHeader(header);
        table.setRowHeight(35);
        table.setBackground(Color.white);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        table.setBackground(Color.white);

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