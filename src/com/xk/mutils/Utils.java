package com.xk.mutils;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {

    private static JTextArea log;
    private static JTextPane logPan;
    private static JTextArea variateArea;


    public static void setLog(JTextArea log) {
        Utils.log = log;
    }


    public static String getVariateAreaJson() {
        return variateArea.getText();
    }

    public static String readStringFromFile(String path) throws Exception {


//        File file = new File("./");
//        System.out.println(file.getAbsolutePath());
        File file = new File(path);
        if (file.exists()) {
            try {
                StringBuilder buffer = new StringBuilder();
                InputStream is = new FileInputStream(path);
                String line; // 用来保存每行读取的内容
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                line = reader.readLine(); // 读取第一行
                while (line != null) { // 如果 line 为空说明读完了
                    buffer.append(line); // 将读到的内容添加到 buffer 中
                    buffer.append("\n"); // 添加换行符
                    line = reader.readLine(); // 读取下一行
                }
                return buffer.toString();

            } catch (Exception e) {
            }
        }
        return "";

    }

    public static void executeShell(String adb, String device, String shell) {
        addText("adb " + shell, Color.BLUE);

        shell = adb + device + shell;
        Process process = null;
        List<String> processList = new ArrayList<String>();
        List<String> processListError = new ArrayList<String>();
        try {
            process = Runtime.getRuntime().exec(shell);

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String inputLine = "";
            String errorLine = "";
            while ((inputLine = inputStream.readLine()) != null) {
                processList.add(inputLine);
            }
            while ((errorLine = errorStream.readLine()) != null) {
                processListError.add(errorLine);
            }

            inputStream.close();
            errorStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : processList) {
            addText(line, Color.black);
            System.out.println(line);
        }
        for (String line : processListError) {
            addText(line, Color.red);
            System.out.println(line);
        }
    }

    public static void addText(String text, Color foreground) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        String string = simpleDateFormat.format(new Date()) + text + "\n\n";

        StyledDocument d = logPan.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr, foreground);
//        StyleConstants.setBackground(attr, background);
        try {
            d.insertString(0, string, attr);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }


    }


    public static void clear() {
        logPan.setText("");
    }

    public static void setLogPan(JTextPane logPan) {
        Utils.logPan = logPan;
    }


}
