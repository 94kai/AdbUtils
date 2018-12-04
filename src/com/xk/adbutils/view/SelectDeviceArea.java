package com.xk.adbutils.view;

import com.xk.adbutils.ShellUtils;
import com.xk.adbutils.bean.Config;
import com.xk.adbutils.bean.ShellResult;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * 选择设备区
 */
public class SelectDeviceArea extends MJpanel {
    private JList sourceList;
    //当前连接的deviceId
    private List<String> devicesId;

    public static SelectDeviceArea instance;
    private List<String> showList;

    @Override
    protected void init() {
        instance=this;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel jLabel = new JLabel("选择设备：");
        add(jLabel);
        sourceList = new JList();
        sourceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane sourceListScroller = new JScrollPane(sourceList);
        add(sourceListScroller);

        JButton refresh = new JButton("刷新设备");

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listDevices();
            }
        });
        add(refresh);

    }

    /**
     * 列出当前连接的设备
     */
    private void listDevices() {
        ShellResult result = ShellUtils.listDevices();
        devicesId = new ArrayList<>();
        if (result.getResultList() != null) {
            for (String line : result.getResultList()) {
                if (line.endsWith("device") && line.contains("\t")) {
                    String[] deviceId = line.split("\t");
                    devicesId.add(deviceId[0]);
                }
            }
        }
        DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("无");
        //列表显示的数据源
        showList = new ArrayList<>();
        if (config != null) {
            List<Config.Device> deviceList = config.getDeviceList();
            for (String deviceId : devicesId) {
                boolean hasAdd = false;
                for (Config.Device device : deviceList) {
                    if (device.getDeviceId().equals(deviceId)) {
                        showList.add(device.getDeviceName());
                        hasAdd = true;
                        break;
                    }
                }
                if (!hasAdd) {
                    showList.add(deviceId);
                }
            }
            for (String showName : showList) {
                listModel.addElement(showName);
            }
        }
        sourceList.setModel(listModel);

    }

    @Override
    protected void onConfigLoaded() {
        listDevices();
    }


    /**
     * 获取当前选中的设备id
     * @return 无、没选中返回空字符串
     */
    public String getSelectDeviceId() {
        int selectedIndex = sourceList.getSelectedIndex();
        if (selectedIndex == 0 || selectedIndex == -1) {
            return "";
        }
        String deviceId = devicesId.get(selectedIndex - 1);
        return deviceId;
    }

    /**
     * 获取当前选中的设备名
     * @return 无、没选中返回空字符串，有名字返回名字，无名字返回id
     */
    public String getSelectDeviceName() {
        int selectedIndex = sourceList.getSelectedIndex();
        if (selectedIndex == 0 || selectedIndex == -1) {
            return "";
        }
        String deviceName = showList.get(selectedIndex - 1);
        return deviceName;
    }




    /**
     * 返回命令块（设备id部分）
     */
    public String getShellBlock() {
        String selectDeviceId = getSelectDeviceId();
        if (selectDeviceId.isEmpty()) {
            return " ";
        }
        return " -s " + selectDeviceId + " ";
    }
}
