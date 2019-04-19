package com.xk.adbutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {


    public static String readStringFromFile(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                InputStream is = new FileInputStream(path);
                return readStringFromInputStream(is);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }


    private static String readStringFromInputStream(InputStream inputStream) {
        try {
            StringBuilder buffer = new StringBuilder();

            String line; // 用来保存每行读取的内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            line = reader.readLine(); // 读取第一行

            while (line != null) { // 如果 line 为空说明读完了
                buffer.append(line); // 将读到的内容添加到 buffer 中
                buffer.append("\n"); // 添加换行符
                line = reader.readLine(); // 读取下一行
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String readStringFromRes(String fileName) {
        InputStream is = FileUtils.class.getClassLoader().getResourceAsStream("com/xk/adbutils/res/" + fileName);
        return readStringFromInputStream(is);
    }
}

