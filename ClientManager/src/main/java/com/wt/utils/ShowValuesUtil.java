package com.wt.utils;

import com.wt.factory.ServiceFactory;
import com.wt.vo.ContactVo;
import com.wt.vo.MyTable;
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
    public void showContact(List<ContactVo> contacts,JPanel contactContentPanel,JPanel contactBodyPanel){
        this.contactBodyPanel=contactBodyPanel;
        this.contactContentPanel=contactContentPanel;
        showContact(contacts);
    }
    public void showContact(List<ContactVo> contacts) {
        TableModel tableModel;
        tableModel = new DefaultTableModel();
        Contact_table= new JTable(tableModel){ @Override
        public boolean isCellEditable(int row, int column) { return false; }};
        DefaultTableModel model = new DefaultTableModel();
        Contact_table.setModel(model);
        model.setColumnIdentifiers(new String[]{"员工编号","员工姓名","用户名","电话号码","所属部门","负责产品","工资",""});
        for (ContactVo contact : contacts) {
            Object[] objects = new Object[]{
                    contact.getContactId(),
                    contact.getRealName(), contact.getUserName(),
                    contact.getUserPhone(), contact.getDepName(),
                    contact.getProductName(), contact.getSalary()
            };
            model.addRow(objects);
        }
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
        JScrollPane scrollPane = new JScrollPane(mypane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(Contact_table.getWidth(),Contact_table.getHeight()));
        scrollPane.setBackground(Color.white);
        contactBodyPanel.add(scrollPane);
        contactBodyPanel.revalidate();
        contactBodyPanel.repaint();
        Contact_table.getSelectionModel().addListSelectionListener(e -> {
        });
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("删除");
        JMenuItem outItem = new JMenuItem("导出");
        jPopupMenu.add(deleteItem);
        jPopupMenu.add(outItem);
        Contact_table.add(jPopupMenu);
        //删除联系人
        Contact_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3) {
                    jPopupMenu.show(Contact_table, e.getX(), e.getY());
                    if(Contact_table.getSelectedRowCount()==1){
                        contact_id=Contact_table.getSelectedRow();
                        deleteItem.addActionListener(e1->{
                            int choice = JOptionPane.showConfirmDialog(null, "确定删除吗？");
                            if (choice == 0) {
                                ServiceFactory.getUserServiceInstance().deleteContact(Contact_table.getModel().getValueAt(contact_id,0).toString());
                                JOptionPane.showMessageDialog(null,"删除联系人成功");
                                contactBodyPanel.removeAll();
                                showContact(ServiceFactory.getUserServiceInstance().selectAll(),contactContentPanel,contactBodyPanel);
                                contactBodyPanel.revalidate();
                                contactBodyPanel.repaint();
                            }
                        });
                    }
                }
            }
        });
        MyTable myTable =new MyTable();
        myTable.setuContact_table(Contact_table);
    }
    public void showDep(List<ContactVo> contacts,JPanel contactContentPanel,JPanel contactBodyPanel){

    }

}
