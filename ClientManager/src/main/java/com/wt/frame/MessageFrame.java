package com.wt.frame;

import com.wt.entity.Message;
import com.wt.factory.ServiceFactory;
import com.wt.utils.ShowValuesUtil;
import com.wt.vo.MyTable;
import com.wt.vo.UserVo;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * @ClassName Message
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/12 16:20
 **/
public class MessageFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel messageTitlePanel;
    private JPanel messageContentPanel;
    private JPanel messageBodyPanel;
    private JTextPane textPane1;
    private JTextPane textPane2;
    private JLabel mainXLabel;
    private final UserVo uv = new UserVo();

    MessageFrame() {
        init();
        showMessage(ServiceFactory.getMessageServiceInstance().selectAll(uv.getclientId()));
        mainXLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               dispose();
            }
        });
    }
    public void init() {
        setContentPane(mainPanel);
        setUndecorated(true);
        setTitle("主页");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1200, 800));
        setVisible(true);
    }

    private JTable message_table;
    private int message_id;

    public void showMessage(List<Message> clientVos) {
        TableModel tableModel;
        tableModel = new DefaultTableModel();
        message_table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DefaultTableModel model = new DefaultTableModel();
        message_table.setModel(model);
        model.setColumnIdentifiers(new String[]{"编号", "标题"});
        for (Message clientVo : clientVos) {
            Object[] objects = new Object[]{
                    clientVo.getMessageId(), clientVo.getMessageTitle()
            };
            model.addRow(objects);
        }
        JTableHeader header = message_table.getTableHeader();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setFont(new Font("楷体", Font.PLAIN, 18));
        message_table.setTableHeader(header);
        message_table.setRowHeight(35);
        message_table.setBackground(Color.white);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        message_table.setDefaultRenderer(Object.class, r);
        message_table.setBackground(Color.white);
        message_table.setPreferredSize(new Dimension(messageContentPanel.getWidth(), messageContentPanel.getHeight()));
        JPanel mypanel = new JPanel(new BorderLayout());
        mypanel.setPreferredSize(new Dimension(300, (message_table.getRowCount() + 1) * message_table.getRowHeight()));
        mypanel.add(header, BorderLayout.NORTH);
        mypanel.add(message_table, BorderLayout.CENTER);
        JScrollPane scrollPanel = new JScrollPane(mypanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setPreferredSize(new Dimension(message_table.getWidth(), message_table.getHeight()));
        scrollPanel.setBackground(Color.white);
        messageBodyPanel.add(scrollPanel);
        messageBodyPanel.revalidate();
        messageBodyPanel.repaint();
        message_table.getSelectionModel().addListSelectionListener(e -> {
        });
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("详情");
        jPopupMenu.add(deleteItem);
        message_table.add(jPopupMenu);
        message_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3) {
                    jPopupMenu.show(message_table, e.getX(), e.getY());
                    if (message_table.getSelectedRowCount() == 1) {
                        message_id = message_table.getSelectedRow();
                        deleteItem.addActionListener(e1 -> {
                            Message message;
                            message = ServiceFactory.getMessageServiceInstance().selectMessageDetail(message_table.getModel().getValueAt(message_id, 0).toString());
                            textPane1.setText(message.getMessageTitle());
                            textPane2.setText(message.getMessageContent());
                        });
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        new IndexFrame();
    }
}
