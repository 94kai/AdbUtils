package com.xk.adbutils;

import com.alibaba.fastjson.JSON;
import com.xk.adbutils.bean.Config;
import com.xk.adbutils.view.LogArea;
import com.xk.adbutils.view.OperationArea;

import java.awt.Color;

import javax.swing.JFrame;


public class App extends JFrame {

    int windowWidth = 1000;
    int windowHeight = 600;
    int operationAreaHeight = 150;

    String configPath = "./config.json";

    public App() {
        super("adb工具集");
        JFrame.setDefaultLookAndFeelDecorated(true);

        setLayout(null);
        setSize(windowWidth, windowHeight);

        OperationArea operationArea = new OperationArea();
        operationArea.setBounds(0, 0, windowWidth, operationAreaHeight);
        add(operationArea);

        LogArea logArea = new LogArea();
        logArea.setBounds(0, operationArea.getHeight(), windowWidth, windowHeight - operationArea.getHeight() - getLocation().y);
        add(logArea);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        String json = null;
        try {
            json = FileUtils.readStringFromFile(configPath);
            Config config = JSON.parseObject(json, Config.class);
            Config.setInstance(config);
            operationArea.setConfig();
        } catch (Exception e) {
            LogArea.addText(Constant.helpError, Color.red);
        }
    }
}
