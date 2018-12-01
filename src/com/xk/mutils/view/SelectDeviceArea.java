package com.xk.mutils.view;

import com.sun.javaws.security.AppContextUtil;
import com.xk.mutils.bean.Config;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 选择设备区
 */
public class SelectDeviceArea extends JPanel {
    private JList sourceList;
    private Config config;

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel jLabel = new JLabel("选择设备：");
        add(jLabel);
        sourceList = new JList();
        sourceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane sourceListScroller = new JScrollPane(sourceList);
        add(sourceListScroller);
    }

    public void setConfig(Config config) {
        this.config = config;
        if (config != null) {
            List<Config.Device> deviceList = config.getDeviceList();
            DefaultListModel listModel = new DefaultListModel();
            listModel.addElement("无");
            for (Config.Device device : deviceList) {
                listModel.addElement(device.getDeviceName());
            }
            sourceList.setModel(listModel);
        }
    }

    public String getSelectDeviceId() {
        int selectedIndex = sourceList.getSelectedIndex();
        if (selectedIndex == 0) {
            return "";
        }
        List<Config.Device> deviceList = config.getDeviceList();
        Config.Device device = deviceList.get(selectedIndex - 1);
        return device.getDeviceId();
    }

    /**
     * 返回命令块（设备id部分）
     *
     */
    public String getShellBlock() {
        String selectDeviceId = getSelectDeviceId();
        if (selectDeviceId.isEmpty()) {
            return "";
        }
        return " -s " + selectDeviceId+" ";
    }
}
