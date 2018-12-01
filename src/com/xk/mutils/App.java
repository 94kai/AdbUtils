package com.xk.mutils;

import com.alibaba.fastjson.JSON;
import com.xk.mutils.bean.Config;
import com.xk.mutils.view.Button;
import com.xk.mutils.view.LogArea;
import com.xk.mutils.view.OperationArea;
import com.xk.mutils.view.VariateArea;

import javax.swing.*;
import java.awt.*;


public class App extends JFrame {

    int windowWidth = 1000;
    int windowHeight = 600;
    int consoleY = 100;
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
        logArea.setBounds(0, operationArea.getHeight(), windowWidth, windowHeight - operationArea.getHeight()-getLocation().y);
        add(logArea);  //布局的北边
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


        String json = null;
        try {
            json = Utils.readStringFromFile(configPath);
            Config config = JSON.parseObject(json, Config.class);
            operationArea.setConfig(config);
        } catch (Exception e) {
            Utils.addText(Constant.help, Color.red);

        }
//        try {
//            setLayout(null);
//
//
//
//            OperationArea operationArea = new OperationArea(0, 0, windowWidth, consoleY, config);
//
//            int y = getLocation().y;
//            System.out.println(y);
//            add(operationArea);
//            VariateArea variateArea = new VariateArea(0, logArea.getHeight()+logArea.getY(), windowWidth, variateAreaHeight,config.getDefaultVariate());
//            variateArea.setBackground(Color.green);
//            add(logArea);
//            add(variateArea);
//
//
//
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();

//            JScrollPane jScrollPane = new JScrollPane(new JTextArea());
//            jScrollPane.setBounds(0, 0, windowWidth, windowHeight);
//            jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//            JTextArea textArea = (JTextArea) jScrollPane.getViewport().getView();
//            setLayout(null);
//            add(jScrollPane);
//            Utils.addText(errContent,Color.red);
//            textArea.setText(errContent);
//
//        }
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setVisible(true);


    }
}
