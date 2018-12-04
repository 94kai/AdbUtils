package com.xk.adbutils.view;

import com.xk.adbutils.Constant;
import com.xk.adbutils.ShellUtils;
import com.xk.adbutils.bean.Config;

import java.awt.Color;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;

/**
 * 自定义adb操作区
 */
public class CustomAdbArea extends MJpanel {
    private String internalVariate_Date = "DATE";
    private String internalVariate_DeviceName = "DEVICE_NAME";

    @Override
    protected void init() {
        setLayout(new FlowLayout());
    }

    @Override
    protected void onConfigLoaded() {

        List<Config.AdbCmd> adbCmdList = config.getAdbCmdList();
        for (Config.AdbCmd adbCmd : adbCmdList) {
            if (adbCmd.isHide()) {
                continue;
            }
            JButton jButton = new Button(adbCmd.getFunName(), new Runnable() {
                @Override
                public void run() {
                    Date date = new Date();
                    List<String> cmdArray = adbCmd.getCmdArray();
                    for (String cmd : cmdArray) {
                        cmd = replaceInternalVariate(cmd, date);
                        cmd = replaceVariate(cmd);
                        ShellUtils.executeShellWithLog(cmd);
                    }
                }
            });
            add(jButton);
        }
        initClearButton();
        initHelpButton();
    }

    /**
     * 替换配置文件中的脚本，根据内置变量
     *
     * @param cmd
     * @param date
     * @return
     */
    private String replaceInternalVariate(String cmd, Date date) {
        String keywordDate = "${" + internalVariate_Date + "}";
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss");
        String time = format.format(date);
        if (cmd.contains(keywordDate)) {
            cmd = cmd.replace(keywordDate, time + "");
        }


        String keywordDeviceName = "${" + internalVariate_DeviceName + "}";
        String deviceName=SelectDeviceArea.instance.getSelectDeviceName();
        if (cmd.contains(keywordDeviceName)) {
            cmd = cmd.replace(keywordDeviceName, deviceName);
        }


        return cmd;
    }

    /**
     * 替换配置文件中的脚本，根据变量区的内容
     *
     * @param cmd
     * @return
     */
    private String replaceVariate(String cmd) {
        String regex = "\\$\\{[^{^}]*\\}";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(cmd);
        while (matcher.find()) {
            String keywordTemp = matcher.group();
            String keyword = keywordTemp.substring(2, keywordTemp.length() - 1);
            String value = VariateArea.instance.getValue(keyword);
            if (value != null && !"".equals(value)) {
                cmd = cmd.replace(keywordTemp, value);
            }
        }
        return cmd;

    }

    private void initHelpButton() {
        JButton jButton = new Button("帮助", new Runnable() {
            @Override
            public void run() {
                LogArea.addText(Constant.help, Color.BLUE);
            }
        });
        add(jButton);
    }


    private void initClearButton() {
        JButton jButton = new Button("清空日志", new Runnable() {
            @Override
            public void run() {
                LogArea.clear();
            }
        });
        add(jButton);
    }

}
