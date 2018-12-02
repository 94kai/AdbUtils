package com.xk.adbutils;

import com.xk.adbutils.bean.Config;
import com.xk.adbutils.bean.ShellResult;
import com.xk.adbutils.view.LogArea;
import com.xk.adbutils.view.SelectDeviceArea;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShellUtils {


    /**
     * 执行shell脚本 带日志的
     *
     * @param shell shell脚本，不包括adb 设备号
     */
    public static void executeShellWithLog(String shell) {
        ShellResult shellResult = executeShell(shell);
        String shellCmd = shellResult.getShell();
        List<String> resultList = shellResult.getResultList();
        List<String> errorResultList = shellResult.getErrorResultList();

        LogArea.addText(shellCmd, Color.BLUE);
        for (String line : resultList) {
            LogArea.addText(line, Color.black);
        }
        for (String line : errorResultList) {
            LogArea.addText(line, Color.red);
        }
    }

    /**
     * 执行shell脚本
     *
     * @param shell shell脚本，不包括adb 设备号
     * @return 运行结果
     */
    public static ShellResult executeShell(String shell) {
        String adb = Config.instance.getVariate(Constant.KEY_ADB_PATH);
        String shellBlock = SelectDeviceArea.instance.getShellBlock();
        String shellCmd = adb + shellBlock + shell;
        Process process = null;
        List<String> resultList = new ArrayList<String>();
        List<String> errorResultList = new ArrayList<String>();
        try {
            process = Runtime.getRuntime().exec(shellCmd);

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String inputLine = "";
            String errorLine = "";
            while ((inputLine = inputStream.readLine()) != null) {
                resultList.add(inputLine);
            }
            while ((errorLine = errorStream.readLine()) != null) {
                errorResultList.add(errorLine);
            }
            inputStream.close();
            errorStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ShellResult shellResult = new ShellResult();
        shellResult.setShell(shellCmd.replace(adb,"adb"));
        shellResult.setResultList(resultList);
        shellResult.setErrorResultList(errorResultList);

        return shellResult;
    }

    /**
     * 列出所有可用设备
     *
     * @return 运行结果
     */
    public static ShellResult listDevices() {
        return executeShell("devices");
    }
}
