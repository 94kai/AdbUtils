package com.xk.mutils;

import com.alibaba.fastjson.JSON;
import com.xk.mutils.view.LogArea;
import com.xk.mutils.view.OperationArea;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.File;


public class App extends JFrame {

    int windowWidth = 1000;
    int windowHeight = 600;
    int consoleY = 100;

    String configPath = "./config.json";

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
            String errContent = "ERROR!!!\n创建\"config.json\",放置在同级目录下\n\n\n {\n" +
                    "  \"adbPath\": \"/Users/xuekai1/Library/Android/sdk/platform-tools/adb\",\n" +
                    "  \"deviceList\": [\n" +
                    "    {\n" +
                    "      \"deviceName\": \"小米8\",\n" +
                    "      \"deviceId\": \"121b1037\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"deviceName\": \"金立\",\n" +
                    "      \"deviceId\": \"79KFPJLNY5OJUCSC\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"adbCmdList\": [\n" +
                    "    {\n" +
                    "      \"funName\": \"安装搜索并重启\",\n" +
                    "      \"cmdArray\": [\n" +
                    "        \"push /Users/xuekai1/project/asprojects/bundle-search/application/build/outputs/apk/application-debug.apk /sdcard/aura/lib/libcom.jd.lib.search.so\",\n" +
                    "        \"shell am force-stop  com.jingdong.app.mall\"\n" +
                    "      ],\n" +
                    "      \"type\": \"adb\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"funName\": \"摇晃手机\",\n" +
                    "      \"cmdArray\": [\n" +
                    "        \"shell input keyevent 82\"\n" +
                    "      ]\n" +
                    "    },{\n" +
                    "      \"funName\": \"连接rn\",\n" +
                    "      \"cmdArray\": [\n" +
                    "        \"reverse tcp:8081 tcp:8081\"\n" +
                    "      ]\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "\n";
            JScrollPane jScrollPane = new JScrollPane(new JTextArea());
            jScrollPane.setBounds(0, 0, windowWidth, windowHeight);
            jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            JTextArea textArea = (JTextArea) jScrollPane.getViewport().getView();
            setLayout(null);
            add(jScrollPane);
            textArea.setText(errContent);

        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


    }
}
