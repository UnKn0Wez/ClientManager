package com.wt.utils;

import com.wt.entity.*;
import com.wt.factory.ServiceFactory;
import com.wt.vo.ClientVo;
import com.wt.vo.ContactVo;
import com.wt.vo.MyTable;
import com.wt.vo.UserVo;
import com.wt.entity.Product;
import com.wt.factory.ServiceFactory;
import com.wt.vo.*;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * @ClassName showValuesUtil
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/5 12:17
 **/
public class ShowValuesUtil {

    private Integer contact_id;
    private JTable Contact_table;
    private JPanel contactContentPanel;
    private JPanel contactBodyPanel;
    private JTable dep_table;
    private JPanel depContentPanel;
    private JPanel depBodyPanel;
    private JTable client_table;
    private JPanel clientContentPanel;
    private JPanel clientBodyPanel;
    private JTable product_table;
    private JPanel productContentPanel;
    private JPanel productBodyPanel;
    private UserVo uv = new UserVo();

    public void showContact(List<ContactVo> contacts, JPanel contactContentPanel, JPanel contactBodyPanel) {
        this.contactBodyPanel = contactBodyPanel;
        this.contactContentPanel = contactContentPanel;
        showContact(contacts);
    }

    public void showContact(List<ContactVo> contacts) {
        TableModel tableModel;
        tableModel = new DefaultTableModel();
        Contact_table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DefaultTableModel model = new DefaultTableModel();
        Contact_table.setModel(model);
        model.setColumnIdentifiers(new String[]{"员工编号", "员工姓名", "用户名", "电话号码", "所属部门", "负责产品", "工资"});
        for (ContactVo contact : contacts) {
            Object[] objects = new Object[]{
                    contact.getContactId(),
                    contact.getRealName(), contact.getUserName(),
                    contact.getUserPhone(), contact.getDepName(),
                    contact.getProductName(), contact.getSalary()
            };
            model.addRow(objects);
        }
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
        Contact_table.setPreferredSize(new Dimension(contactContentPanel.getWidth(), contactContentPanel.getHeight()));
        JPanel mypane = new JPanel(new BorderLayout());
        mypane.setPreferredSize(new Dimension(300, (Contact_table.getRowCount()+1) * Contact_table.getRowHeight()));
        mypane.add(header, BorderLayout.NORTH);
        mypane.add(Contact_table, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(mypane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(Contact_table.getWidth(), Contact_table.getHeight()));
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
                    jPopupMenu.show(Contact_table, e.getX(), e.getY());
                    if (Contact_table.getSelectedRowCount() == 1) {
                        contact_id = Contact_table.getSelectedRow();
                        deleteItem.addActionListener(e1 -> {
                            int choice = JOptionPane.showConfirmDialog(null, "确定删除吗？");
                            if (choice == 0) {
                                ServiceFactory.getUserServiceInstance().deleteContact(Contact_table.getModel().getValueAt(contact_id, 0).toString());
                                JOptionPane.showMessageDialog(null, "删除联系人成功");
                                contactBodyPanel.removeAll();
                                showContact(ServiceFactory.getUserServiceInstance().selectAll(), contactContentPanel, contactBodyPanel);
                                contactBodyPanel.revalidate();
                                contactBodyPanel.repaint();
                            }
                        });
                    }
                }
            }
        });
        MyTable myTable = new MyTable();
        myTable.setuContact_table(Contact_table);
    }

    public void showProducts(List<Product> products, JPanel productContentPanel, JPanel productBodyPanel) {
        this.productBodyPanel = productBodyPanel;
        this.productContentPanel = productContentPanel;
        showProducts(products);
    }

    public void showProducts(List<Product> products) {
        TableModel tableModel;
        tableModel = new DefaultTableModel();
        product_table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DefaultTableModel model = new DefaultTableModel();
        product_table.setModel(model);
        model.setColumnIdentifiers(new String[]{"产品编号","产品名称","入库日期","产品类型","产品单价"});
        for (Product product : products) {
            Object[] objects = new Object[]{
                    product.getProductId(),
                    product.getProductName(), product.getProductDate(),
                    product.getProductType(), product.getPrice()
            };
            model.addRow(objects);
        }
        JTableHeader header = product_table.getTableHeader();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setFont(new Font("楷体", Font.PLAIN, 18));
        product_table.setTableHeader(header);
        product_table.setRowHeight(35);
        product_table.setBackground(Color.white);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        product_table.setDefaultRenderer(Object.class, r);
        product_table.setBackground(Color.white);
        product_table.setPreferredSize(new Dimension(productContentPanel.getWidth(), productContentPanel.getHeight()));
        JPanel mypane = new JPanel(new BorderLayout());
        mypane.setPreferredSize(new Dimension(300, (product_table.getRowCount()+1) * product_table.getRowHeight()));
        mypane.add(header, BorderLayout.NORTH);
        mypane.add(product_table, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(mypane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(product_table.getWidth(), product_table.getHeight()));
        scrollPane.setBackground(Color.white);
        productBodyPanel.add(scrollPane);
        productBodyPanel.revalidate();
        productBodyPanel.repaint();
        product_table.getSelectionModel().addListSelectionListener(e -> {
        });
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("删除");
        jPopupMenu.add(deleteItem);
        product_table.add(jPopupMenu);
        //删除联系人
        product_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3) {
                    jPopupMenu.show(product_table, e.getX(), e.getY());
                    if (product_table.getSelectedRowCount() == 1) {
                        contact_id = product_table.getSelectedRow();
                        deleteItem.addActionListener(e1 -> {
                            int choice = JOptionPane.showConfirmDialog(null, "确定删除吗？");
                            if (choice == 0) {
                                ServiceFactory.getProductServiceInstance().delProduct(product_table.getModel().getValueAt(contact_id,0).toString());
                                JOptionPane.showMessageDialog(null,"删除产品成功");
                                productBodyPanel.removeAll();
                                showProducts(ServiceFactory.getProductServiceInstance().selectAllProduct(), productContentPanel, productBodyPanel);
                                productBodyPanel.revalidate();
                                productBodyPanel.repaint();
                            }
                        });
                    }
                }
            }
        });
        MyTable myTable = new MyTable();
        myTable.setProduct_table(product_table);
    }

    public void showOrders(List<Order> orders, JPanel contactContentPanel, JPanel contactBodyPanel){
        this.contactBodyPanel = contactBodyPanel;
        this.contactContentPanel = contactContentPanel;
        showOrders(orders);
    }

    public void showOrders(List<Order> orders) {
        TableModel tableModel;
        tableModel = new DefaultTableModel();
        Contact_table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DefaultTableModel model = new DefaultTableModel();
        Contact_table.setModel(model);

        UserVo uv=new UserVo();
        String urole=uv.getuRole();
        if(urole.equals("Admin")){
            model.setColumnIdentifiers(new String[]{"订单编号", "客户名称","客户联系方式", "产品类型", "产品名称", "购买数量", "单价", "购买时间"});
            for (Order order : orders) {
                Object[] objects = new Object[]{
                        order.getOrder_id(),
                        order.getClient_name(), order.getClient_phone(),
                        order.getProduct_type(), order.getProduct_name(),
                        order.getBuy_num(), order.getPrice(),
                        order.getBuy_time()
                };
                model.addRow(objects);
            }
        }
        else if(urole.equals("Client")){
            model.setColumnIdentifiers(new String[]{"订单编号","联系人名称","联系人手机号", "产品类型", "产品名称", "购买数量", "单价", "购买时间"});
            for (Order order : orders) {
                Object[] objects = new Object[]{
                        order.getOrder_id(),
                        order.getContact_name(), order.getContact_phone(),
                        order.getProduct_type(), order.getProduct_name(),
                        order.getBuy_num(), order.getPrice(),
                        order.getBuy_time()
                };
                model.addRow(objects);
            }
        }
        else if(urole.equals("Contact")){
            model.setColumnIdentifiers(new String[]{"订单编号", "客户名称","客户联系方式", "产品类型", "产品名称", "购买数量", "单价", "购买时间"});
            for (Order order : orders) {
                Object[] objects = new Object[]{
                        order.getOrder_id(),
                        order.getClient_name(), order.getClient_phone(),
                        order.getProduct_type(), order.getProduct_name(),
                        order.getBuy_num(), order.getPrice(),
                        order.getBuy_time()
                };
                model.addRow(objects);
            }
        }


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
        Contact_table.setPreferredSize(new Dimension(contactContentPanel.getWidth(), contactContentPanel.getHeight()));
        JPanel mypane = new JPanel(new BorderLayout());
        mypane.setPreferredSize(new Dimension(300, (Contact_table.getRowCount()+1) * Contact_table.getRowHeight()));
        mypane.add(header, BorderLayout.NORTH);
        mypane.add(Contact_table, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(mypane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(Contact_table.getWidth(), Contact_table.getHeight()));
        scrollPane.setBackground(Color.white);
        contactBodyPanel.add(scrollPane);
        contactBodyPanel.revalidate();
        contactBodyPanel.repaint();
        Contact_table.getSelectionModel().addListSelectionListener(e -> {
        });
        MyTable myTable = new MyTable();
        myTable.setuOrder_table(Contact_table);
    }

    public void showRequest(List<Request> requests, JPanel contactContentPanel, JPanel contactBodyPanel){
        this.contactBodyPanel = contactBodyPanel;
        this.contactContentPanel = contactContentPanel;
        showRequest(requests);
    }
    public void showRequest(List<Request> requests){
        TableModel tableModel;
        tableModel = new DefaultTableModel();
        Contact_table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DefaultTableModel model = new DefaultTableModel();
        Contact_table.setModel(model);

        UserVo uv=new UserVo();
        String urole=uv.getuRole();
        if(urole.equals("Admin")){
            model.setColumnIdentifiers(new String[]{"反馈单号", "订单编号","客户名称", "反馈时间"});
            for (Request request : requests) {
                Object[] objects = new Object[]{
                        request.getRequest_id(),
                        request.getOrder_id(),
                        request.getClient_name(),
                        request.getRequest_time()
                };
                model.addRow(objects);
            }
        }
        else if(urole.equals("Client")){
            model.setColumnIdentifiers(new String[]{"反馈单号","订单编号","联系人名称","反馈时间"});
            for (Request request : requests) {
                Object[] objects = new Object[]{
                        request.getRequest_id(),
                        request.getOrder_id(),
                        request.getContact_name(),
                        request.getRequest_time()
                };
                model.addRow(objects);
            }
        }
        else if(urole.equals("Contact")){
            model.setColumnIdentifiers(new String[]{"反馈单号", "订单编号","客户名称", "反馈时间"});
            for (Request request : requests) {
                Object[] objects = new Object[]{
                        request.getRequest_id(),
                        request.getOrder_id(),
                        request.getClient_name(),
                        request.getRequest_time()
                };
                model.addRow(objects);
            }
        }


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
        Contact_table.setPreferredSize(new Dimension(contactContentPanel.getWidth(), contactContentPanel.getHeight()));
        JPanel mypane = new JPanel(new BorderLayout());
        mypane.setPreferredSize(new Dimension(300, (Contact_table.getRowCount()+1) * Contact_table.getRowHeight()));
        mypane.add(header, BorderLayout.NORTH);
        mypane.add(Contact_table, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(mypane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(Contact_table.getWidth(), Contact_table.getHeight()));
        scrollPane.setBackground(Color.white);
        contactBodyPanel.add(scrollPane);
        contactBodyPanel.revalidate();
        contactBodyPanel.repaint();
        Contact_table.getSelectionModel().addListSelectionListener(e -> {
        });
        MyTable myTable = new MyTable();
        myTable.setRequest_table(Contact_table);
    }

    public void showDep(List<Department> deps, JPanel depContentPanel, JPanel depBodyPanel) {
        this.depContentPanel = depContentPanel;
        this.depBodyPanel = depBodyPanel;
        showDep(deps);
    }

    public void showDep(List<Department> deps) {
        TableModel tableModel;
        tableModel = new DefaultTableModel();
        dep_table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DefaultTableModel model = new DefaultTableModel();
        dep_table.setModel(model);
        model.setColumnIdentifiers(new String[]{"部门编号", "部门名称", "部门创建日期"});
        for (Department dep : deps) {
            Object[] objects = new Object[]{
                    dep.getDepId(),
                    dep.getDepName(),
                    dep.getDepTime()
            };
            model.addRow(objects);
        }
        JTableHeader header = dep_table.getTableHeader();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setFont(new Font("楷体", Font.PLAIN, 18));
        dep_table.setTableHeader(header);
        dep_table.setRowHeight(35);
        dep_table.setBackground(Color.white);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        dep_table.setDefaultRenderer(Object.class, r);
        dep_table.setBackground(Color.white);
        dep_table.setPreferredSize(new Dimension(depContentPanel.getWidth(), depContentPanel.getHeight()));
        JPanel mypane = new JPanel(new BorderLayout());
        mypane.setPreferredSize(new Dimension(300, (dep_table.getRowCount()+1) * dep_table.getRowHeight()));
        mypane.add(header, BorderLayout.NORTH);
        mypane.add(dep_table, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(mypane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(dep_table.getWidth(), dep_table.getHeight()));
        scrollPane.setBackground(Color.white);
        depBodyPanel.add(scrollPane);
        depBodyPanel.revalidate();
        depBodyPanel.repaint();
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("删除");
        jPopupMenu.add(deleteItem);
        dep_table.add(jPopupMenu);
        //删除联系人
        dep_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3) {
                    jPopupMenu.show(dep_table, e.getX(), e.getY());
                    if (dep_table.getSelectedRowCount() == 1) {
                        contact_id = dep_table.getSelectedRow();
                        deleteItem.addActionListener(e1 -> {
                            int choice = JOptionPane.showConfirmDialog(null, "确定删除吗？");
                            if (choice == 0) {
                                ServiceFactory.getDepServiceInstance().deleteDep(dep_table.getModel().getValueAt(contact_id, 0).toString());
                                JOptionPane.showMessageDialog(null, "删除部门成功");
                                depBodyPanel.removeAll();
                                showDep(ServiceFactory.getDepServiceInstance().selectDepAll(), depContentPanel, depBodyPanel);
                                depBodyPanel.revalidate();
                                depBodyPanel.repaint();
                            }
                        });
                    }
                }
            }
        });
        MyTable myTable = new MyTable();
        myTable.setDep_table(dep_table);
    }
    public void showPlan(List<MissionVo> plans, JPanel depContentPanel, JPanel depBodyPanel) {
        this.depContentPanel = depContentPanel;
        this.depBodyPanel = depBodyPanel;
        showPlan(plans);
    }

    public void showPlan(List<MissionVo> plans) {
        TableModel tableModel;
        tableModel = new DefaultTableModel();
        dep_table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DefaultTableModel model = new DefaultTableModel();
        dep_table.setModel(model);
        if("Admin".equals(uv.getuRole())||"Contact".equals(uv.getuRole())){
            model.setColumnIdentifiers(new String[]{"计划编号", "客户数量", "计划利润","实施情况","开始时间","结束时间","产品类型","产品名称","负责人"});
            for (MissionVo plan : plans) {
                Object[] objects = new Object[]{
                        plan.getPlanId(),
                        plan.getClientNum(),
                        plan.getPlanProfit(),
                        plan.getPlanState(),
                        plan.getStartTime(),
                        plan.getFinishTime(),
                        plan.getProductType(),
                        plan.getProductName(),
                        plan.getRealName()
                };
                model.addRow(objects);
            }
        }else if("Client".equals(uv.getuRole())){
            model.setColumnIdentifiers(new String[]{"计划编号","实施情况","开始时间","结束时间","产品类型","产品名称","负责人"});
            for (MissionVo plan : plans) {
                Object[] objects = new Object[]{
                        plan.getPlanId(),
                        //plan.getClientNum(),
                        //plan.getPlanProfit(),
                        plan.getPlanState(),
                        plan.getStartTime(),
                        plan.getFinishTime(),
                        plan.getProductType(),
                        plan.getProductName(),
                        plan.getRealName()
                };
                model.addRow(objects);
            }
        }

        JTableHeader header = dep_table.getTableHeader();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setFont(new Font("楷体", Font.PLAIN, 18));
        dep_table.setTableHeader(header);
        dep_table.setRowHeight(35);
        dep_table.setBackground(Color.white);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        dep_table.setDefaultRenderer(Object.class, r);
        dep_table.setBackground(Color.white);
        dep_table.setPreferredSize(new Dimension(depContentPanel.getWidth(), depContentPanel.getHeight()));
        JPanel mypane = new JPanel(new BorderLayout());
        mypane.setPreferredSize(new Dimension(300, dep_table.getRowCount() * dep_table.getRowHeight()));
        mypane.add(header, BorderLayout.NORTH);
        mypane.add(dep_table, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(mypane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(dep_table.getWidth(), dep_table.getHeight()));
        scrollPane.setBackground(Color.white);
        depBodyPanel.add(scrollPane);
        depBodyPanel.revalidate();
        depBodyPanel.repaint();
        if("Admin".equals(uv.getuRole())||"Contact".equals(uv.getuRole())){
            JPopupMenu jPopupMenu = new JPopupMenu();
            JMenuItem deleteItem = new JMenuItem("删除");
            jPopupMenu.add(deleteItem);
            dep_table.add(jPopupMenu);
            //删除联系人
            dep_table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == 3) {
                        jPopupMenu.show(dep_table, e.getX(), e.getY());
                        if (dep_table.getSelectedRowCount() == 1) {
                            contact_id = dep_table.getSelectedRow();
                            deleteItem.addActionListener(e1 -> {
                                int choice = JOptionPane.showConfirmDialog(null, "确定删除吗？");
                                if (choice == 0) {
                                    ServiceFactory.getPlanServiceInstance().deletePlanById(dep_table.getModel().getValueAt(contact_id, 0).toString());
                                    JOptionPane.showMessageDialog(null, "删除计划成功");
                                    depBodyPanel.removeAll();
                                    showDep(ServiceFactory.getDepServiceInstance().selectDepAll(), depContentPanel, depBodyPanel);
                                    depBodyPanel.revalidate();
                                    depBodyPanel.repaint();
                                }
                            });
                        }
                    }
                }
            });
        }
        MyTable myTable = new MyTable();
        myTable.setDep_table(dep_table);
    }

    public void showClient(List<ClientVo> clientVos, JPanel clientContentPanel, JPanel clientBodyPanel) {
        this.clientContentPanel = clientContentPanel;
        this.clientBodyPanel = clientBodyPanel;
        showClient(clientVos);
    }

    public void showClient(List<ClientVo> clientVos) {
        TableModel tableModel;
        tableModel = new DefaultTableModel();
        client_table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DefaultTableModel model = new DefaultTableModel();
        client_table.setModel(model);
        model.setColumnIdentifiers(new String[]{"客户编号", "用户名", "客户姓名", "电话号码", "信任度", "家庭地址"});
        for (ClientVo clientVo : clientVos) {
            Object[] objects = new Object[]{
                    clientVo.getClientId(), clientVo.getUserName(),
                    clientVo.getRealName(), clientVo.getUserPhone(),
                    clientVo.getClientCredit(), clientVo.getClientAddress(),
            };
            model.addRow(objects);
        }
        JTableHeader header = client_table.getTableHeader();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setFont(new Font("楷体", Font.PLAIN, 18));
        client_table.setTableHeader(header);
        client_table.setRowHeight(35);
        client_table.setBackground(Color.white);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        client_table.setDefaultRenderer(Object.class, r);
        client_table.setBackground(Color.white);
        client_table.setPreferredSize(new Dimension(clientContentPanel.getWidth(), clientContentPanel.getHeight()));
        JPanel mypanel = new JPanel(new BorderLayout());
        mypanel.setPreferredSize(new Dimension(300, (client_table.getRowCount()+1) * client_table.getRowHeight()));
        mypanel.add(header, BorderLayout.NORTH);
        mypanel.add(client_table, BorderLayout.CENTER);
        JScrollPane scrollPanel = new JScrollPane(mypanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setPreferredSize(new Dimension(client_table.getWidth(), client_table.getHeight()));
        scrollPanel.setBackground(Color.white);
        clientBodyPanel.add(scrollPanel);
        clientBodyPanel.revalidate();
        clientBodyPanel.repaint();
        client_table.getSelectionModel().addListSelectionListener(e -> {
        });
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("删除");
        jPopupMenu.add(deleteItem);
        client_table.add(jPopupMenu);
        client_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3) {
                    jPopupMenu.show(client_table, e.getX(), e.getY());
                    if (client_table.getSelectedRowCount() == 1) {
                        contact_id = client_table.getSelectedRow();
                        deleteItem.addActionListener(e1 -> {
                            int choice = JOptionPane.showConfirmDialog(null, "确定删除吗？");
                            if (choice == 0) {
                                ServiceFactory.getUserServiceInstance().deleteClient(client_table.getModel().getValueAt(contact_id, 0).toString());
                                JOptionPane.showMessageDialog(null, "删除客户成功");
                                clientBodyPanel.removeAll();
                                showClient(ServiceFactory.getUserServiceInstance().selectClientAll(), clientContentPanel, clientBodyPanel);
                                clientBodyPanel.revalidate();
                                clientBodyPanel.repaint();
                            }
                        });
                    }
                }
            }
        });

        MyTable myTable = new MyTable();
        myTable.setClient_table(client_table);
    }





}
