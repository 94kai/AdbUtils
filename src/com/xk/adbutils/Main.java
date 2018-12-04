package com.xk.adbutils;

import javax.swing.*;

/**
 * TODO:1.指定配置文件，通过配置文件配置adb路径、设备列表、命令列表
 * @author xuekai1
 * @date 2018/11/20
 */
public class Main {

    /**
     * log区
     */
    private static JTextArea log;
    /**
     * 按钮父容器
     */
    private static JPanel buttons;

    /**
     * adb
     */
    private static String adb = "/Users/xuekai1/Library/Android/sdk/platform-tools/adb ";


    /**
     * adb device
     */
    private static String deviceId = " -s 79KFPJLNY5OJUCSC ";

    public static void main(String args[]) {

        new App();
    }

    /**
     * 设置控制台
     */
    private static void setupConsole(JPanel logPan) {
        log.setLineWrap(true);
        JScrollPane scrollpane = new JScrollPane(log);
        scrollpane.setBounds(0, 0, (int) logPan.getBounds().getWidth(), (int) logPan.getBounds().getHeight());
        logPan.add(scrollpane);
    }
}
