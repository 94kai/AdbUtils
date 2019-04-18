package com.xk.adbutils.view;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * 设置变量区
 */
public class VariateArea extends MJpanel {


    public static VariateArea instance;
    private JTable table;

    @Override
    protected void init() {
        instance = this;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel jLabel = new JLabel("添加变量：");
        add(jLabel);

        table = new JTable();
        // 创建包含表格的滚动窗格
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, getWidth(), getHeight());
        add(scrollPane);
    }

    @Override
    protected void onConfigLoaded() {
        Map<String, String> variateList = config.getVariateList();
        String[] columnName = {"key", "value"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(columnName, variateList.keySet().size() + 7);

        for (String key : variateList.keySet()) {
            Vector<String> data = new Vector<>();
            data.add(key);
            data.add(variateList.get(key));
            defaultTableModel.insertRow(0, data);

        }
        table.setModel(defaultTableModel);
    }

    public String getValue(String key) {
        int rowCount = table.getRowCount();
        TableModel model = table.getModel();
        for (int i = 0; i < rowCount; i++) {
            Object valueAt = model.getValueAt(i, 0);
            if (key.equals(valueAt)) {
                return (String) model.getValueAt(i, 1);
            }
        }
        return "";
    }
}
