package com.xk.mutils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
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

    public static void main(String args[]) {

        int windowWidth = 1000;
        int windowHeight = 600;
        int buttonsHeight = 100;

        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("adb工具集");
        frame.setSize(windowWidth, windowHeight + 30);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);


        buttons = new JPanel();
        buttons.setBounds(0, 0, windowWidth, buttonsHeight);
        buttons.setLayout(new FlowLayout());
        JPanel logPan = new JPanel();
        logPan.setLayout(null);
        logPan.setBounds(0, buttonsHeight, windowWidth, windowHeight - buttonsHeight);
        frame.add(buttons);
        frame.add(logPan);


        log = new JTextArea();
        setupConsole(logPan);


        setupButtons();


        // 显示窗口
        frame.setVisible(true);

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

    /**
     * 设置各种按钮
     */
    private static void setupButtons() {

        createCommonButton("摇晃手机", new Runnable() {
            @Override
            public void run() {
                executeShell("adb shell input keyevent 82");
            }
        });

        createCommonButton("安装搜索插件", new Runnable() {
            @Override
            public void run() {
                executeShell("/Users/xuekai1/Library/Android/sdk/platform-tools/adb push /Users/xuekai1/project/asprojects/bundle-search/application/build/outputs/apk/application-debug.apk /sdcard/aura/lib/libcom.jd.lib.search.so");
            }
        });

        createCommonButton("安装搜索插件并重启", new Runnable() {
            @Override
            public void run() {
                executeShell("/Users/xuekai1/Library/Android/sdk/platform-tools/adb push /Users/xuekai1/project/asprojects/bundle-search/application/build/outputs/apk/application-debug.apk /sdcard/aura/lib/libcom.jd.lib.search.so");
                executeShell("adb shell am force-stop  com.jingdong.app.mall");
                executeShell("adb shell am start -n com.jingdong.app.mall/com.jingdong.app.mall.main.MainActivity");
            }
        });

        createCommonButton("安装搜索插件并重启到搜索落地页", new Runnable() {
            @Override
            public void run() {
                executeShell("/Users/xuekai1/Library/Android/sdk/platform-tools/adb push /Users/xuekai1/project/asprojects/bundle-search/application/build/outputs/apk/application-debug.apk /sdcard/aura/lib/libcom.jd.lib.search.so");
                executeShell("adb shell am force-stop  com.jingdong.app.mall");
                executeShell("adb shell am start -n com.jingdong.app.mall/com.jd.lib.search.view.Activity.ProductListActivity --es keyWord iphone --ei sortKey -2");
            }
        });

        createCommonButton("清空控制台", new Runnable() {
            @Override
            public void run() {
                log.setText("");
            }
        });
    }

    private static void createCommonButton(String btnValue, Runnable runnable) {
        JButton btn = new JButton(btnValue);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        addText(btn.getText());
                        runnable.run();
                    }
                }).start();
            }
        });
        buttons.add(btn);
    }


    public static void executeShell(String shell) {
        addText("====================>" + shell);

        Process process = null;
        List<String> processList = new ArrayList<String>();
        try {
            process = Runtime.getRuntime().exec(shell);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : processList) {
            addText(line);
            System.out.println(line);
        }
    }


    public static void addText(String text) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
//        log.setText(log.getText() + "\n\n" + simpleDateFormat.format(new Date()) + text);
        log.setText(simpleDateFormat.format(new Date()) + text + "\n\n" + log.getText());
        log.setCaretPosition(0);

    }


}
