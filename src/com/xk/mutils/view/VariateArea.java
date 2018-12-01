package com.xk.mutils.view;

import com.xk.mutils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * 设置变量区
 */
public class VariateArea extends JPanel {


    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel jLabel = new JLabel("添加变量：");
        add(jLabel);
        String[] columnName = {"key", "value"};
        // 创建表格
        DefaultTableModel defaultTableModel = new DefaultTableModel(columnName, 100);
        String[] strings = {"apk根目录","/Users/xuekai/Documents/第3章 retrofit网络框架源码解析"};
        defaultTableModel.insertRow(0,strings);
        JTable table = new JTable(defaultTableModel);
        // 创建包含表格的滚动窗格
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setBounds(0, 0, getWidth(), getHeight());


        add(scrollPane);
    }

    public String getValue(String key){
        return "";
    }
}
