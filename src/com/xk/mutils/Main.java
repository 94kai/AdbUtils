package com.xk.mutils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

//    /**
//     * 设置各种按钮
//     */
//    private static void setupButtons() {
//
//        createCommonButton("摇晃手机", new Runnable() {
//            @Override
//            public void run() {
//                executeShell(adb + deviceId + " shell input keyevent 82");
//            }
//        });
//
//        createCommonButton("安装搜索插件", new Runnable() {
//            @Override
//            public void run() {
//                executeShell(adb + deviceId + " push /Users/xuekai1/project/asprojects/bundle-search/application/build/outputs/apk/application-debug.apk /sdcard/aura/lib/libcom.jd.lib.search.so");
//            }
//        });
//
//        createCommonButton("安装搜索插件并重启", new Runnable() {
//            @Override
//            public void run() {
//                executeShell(adb + deviceId + " push /Users/xuekai1/project/asprojects/bundle-search/application/build/outputs/apk/application-debug.apk /sdcard/aura/lib/libcom.jd.lib.search.so");
//                executeShell(adb + deviceId + " shell am force-stop  com.jingdong.app.mall");
//                executeShell(adb + deviceId + " shell am start -n com.jingdong.app.mall/com.jingdong.app.mall.main.MainActivity");
//            }
//        });
//
//        createCommonButton("安装搜索插件并重启到搜索落地页", new Runnable() {
//            @Override
//            public void run() {
//                executeShell(adb + deviceId + " push /Users/xuekai1/project/asprojects/bundle-search/application/build/outputs/apk/application-debug.apk /sdcard/aura/lib/libcom.jd.lib.search.so");
//                executeShell(adb + deviceId + " shell am force-stop  com.jingdong.app.mall");
//                executeShell(adb + deviceId + " shell am start -n com.jingdong.app.mall/com.jd.lib.search.view.Activity.ProductListActivity --es keyWord iphone --ei sortKey -2");
//            }
//        });
//
//        createCommonButton("金立连接rn", new Runnable() {
//            @Override
//            public void run() {
//                executeShell(adb + deviceId + " reverse tcp:8081 tcp:8081");
//            }
//        });
//
//
//        createCommonButton("清空控制台", new Runnable() {
//            @Override
//            public void run() {
//                log.setText("");
//            }
//        });
//    }
//
//    private static void createCommonButton(String btnValue, Runnable runnable) {
//        JButton btn = new JButton(btnValue);
//        btn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        addText(btn.getText());
//                        runnable.run();
//                    }
//                }).start();
//            }
//        });
//        buttons.add(btn);
//    }






}
