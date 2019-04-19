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
        Config config;
        String json;
        boolean loadLocal = false;
        try {
            json = FileUtils.readStringFromFile(configPath);
            if ("".equals(json)) {
                loadLocal = true;
                json = Constant.configFile;
            }
            config = JSON.parseObject(json, Config.class);
        } catch (Exception e) {
            json = Constant.configFile;
            config = JSON.parseObject(json, Config.class);
            loadLocal = true;
        }
        if (loadLocal) {
            LogArea.addText("\n没有在小工具当前目录找到config.json文件，或配置有误。已使用默认配置。\n\n如需使用adb命令，需修改变量区adbPath。\n\n建议点击获取配置文件按钮，获取配置文件，命名为config.json存放在小工具同级目录。\n\n详细使用说明请点帮助", Color.red);
        }
        Config.setInstance(config);
        operationArea.setConfig();
    }
}
