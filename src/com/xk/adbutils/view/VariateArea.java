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
// TODO: by xk 2018/12/2 23:18 支持隐藏某些变量，例如adbPath，基本不会修改，希望隐藏。 问题：取value是根据table的内容取的，隐藏之后，取值会有问题。如果保存到另一个对象中的话，通过工具修改的值还是在table中。
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
        List<String> hideVariateList = config.getForbidModifyVariateList();
        String[] columnName = {"key", "value"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(columnName, variateList.keySet().size() + 7){
            @Override
            public boolean isCellEditable(int row, int column) {
                Object key = getValueAt(row, 0);
                if (hideVariateList.contains(key)) {
                    //该行不可编辑
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

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
