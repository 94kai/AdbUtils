package com.xk.mutils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * 上面的区域
 */
public class Top extends JPanel {
    public Top() {
//        setLayout(new FlowLayout());
//        setLayout(new GridBagLayout());
//
//
//        JPanel devicePanel = createDevicePanel();
//        JPanel devicePanel1 = createDevicePanel();
//        JPanel devicePanel2 = createDevicePanel();
//
//        GridBagConstraints c1 = new GridBagConstraints();
//        c1.gridx = 0;
//        c1.gridy = 0;
//        c1.weightx = 2.0;
//        c1.weighty = 1.0;
//        c1.fill = GridBagConstraints.BOTH ;
//        // 加入 topPanel
//        add(devicePanel,c1);
//        GridBagConstraints c2 = new GridBagConstraints();
//        c2.gridx = 1;
//        c2.gridy = 0;
//        c2.weightx = 1.0;
//        c2.weighty = 1.0;
//        c2.fill = GridBagConstraints.HORIZONTAL ;
//        // 加入 topPanel
//        add(devicePanel1,c2);
//
//        GridBagConstraints c3 = new GridBagConstraints();
//        c3.gridx = 2;
//        c3.gridy = 0;
//        c3.weightx = 1.0;
//        c3.weighty = 1;
//        c3.fill = GridBagConstraints.HORIZONTAL ;
//        // 加入 topPanel
//        add(devicePanel2,c3);
    }


    public JPanel createDevicePanel() {
        JPanel topPanel = new JPanel();
        String[] columnName = {"姓名", "性别", "单位", "参加项目", "备注"};
        String[][] rowData = {{"张三", "男", "计算机系", "100 米 ,200 米", ""},
                {"李四", "男", "化学系", "100 米，铅球", ""},
        };
        // 创建表格
        JTable table = new JTable(new DefaultTableModel(rowData, columnName));
        // 创建包含表格的滚动窗格
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // 定义 topPanel 的布局为 BoxLayout，BoxLayout 为垂直排列
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        // 先加入一个不可见的 Strut，从而使 topPanel 对顶部留出一定的空间
        topPanel.add(Box.createVerticalStrut(10));
        // 加入包含表格的滚动窗格
        topPanel.add(scrollPane);
        // 再加入一个不可见的 Strut，从而使 topPanel 和 middlePanel 之间留出一定的空间
        topPanel.add(Box.createVerticalStrut(10));

        return topPanel;
    }
}
