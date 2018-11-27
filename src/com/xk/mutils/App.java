package com.xk.mutils;

import com.alibaba.fastjson.JSON;
import com.xk.mutils.view.LogArea;
import com.xk.mutils.view.OperationArea;

import javax.swing.*;
import java.io.File;


public class App extends JFrame {

    int windowWidth = 1000;
    int windowHeight = 600;
    int consoleY = 100;

    String configPath = "/Users/xuekai1/IdeaProjects/MUtils/src/config.json";

    public App() {
        super("adb工具集");

        JFrame.setDefaultLookAndFeelDecorated(true);

        setSize(windowWidth, windowHeight);

        try {
            setLayout(null);
            String json = Utils.readStringFromFile(configPath);
            Config config = JSON.parseObject(json, Config.class);


            OperationArea operationArea = new OperationArea(0, 0, windowWidth, consoleY, config);

            int y = getLocation().y;
            System.out.println(y);
            LogArea logArea = new LogArea(0, consoleY, windowWidth, windowHeight - consoleY - getLocation().y);
//            JMenuBar.HEIGHT
            add(operationArea);
//            File file = new File("./abc.txt");
//            if (file.exists()) {
                add(logArea);
//            }

        } catch (Exception e) {
            e.printStackTrace();


            //TODO:弹框提醒文件错误
        }

//        // 菜单栏
//        JMenuBar menuBar = new JMenuBar();
//        JMenu setting = new JMenu("设置");
//
//        JMenuItem configPath = new JMenuItem("配置文件路径");
//        setting.add(configPath);
//        menuBar.add(setting);
//        // 设置菜单栏，使用这种方式设置菜单栏可以不占用布局空间
//        setJMenuBar(menuBar);
//
//
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


    }
}
