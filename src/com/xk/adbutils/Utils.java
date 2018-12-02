package com.xk.adbutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {






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




}
