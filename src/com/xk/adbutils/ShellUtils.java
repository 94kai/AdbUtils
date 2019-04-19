package com.xk.adbutils;

import com.xk.adbutils.bean.ShellResult;
import com.xk.adbutils.view.LogArea;
import com.xk.adbutils.view.SelectDeviceArea;
import com.xk.adbutils.view.VariateArea;

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
        ThreadUtils.execute(() -> executeShell(shell, true));
    }

    /**
     * 执行shell脚本
     *
     * @param shell shell脚本，不包括adb 设备号
     * @return 运行结果
     */
    private static ShellResult executeShell(String shell, boolean withLog) {

        String adb = VariateArea.instance.getValue(Constant.KEY_ADB_PATH);
        String shellBlock = SelectDeviceArea.instance.getShellBlock();
        String shellCmd ;
        if (shell.startsWith("adb")) {
            shell = shell.replaceFirst("adb ", "");
            shellCmd = adb + shellBlock + shell;
        } else {
            shellCmd = shell;
        }
        Process process = null;
        List<String> resultList = new ArrayList<String>();
        List<String> errorResultList = new ArrayList<String>();
        try {
            if (withLog) {
                String shellCmdTemp = shellCmd.replace(adb, "adb");
                LogArea.addText(shellCmdTemp, Color.blue);
            }

            process = Runtime.getRuntime().exec(shellCmd);

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String inputLine = "";
            String errorLine = "";
            while ((inputLine = inputStream.readLine()) != null) {
                if (withLog) {
                    LogArea.addText(inputLine, Color.black);
                }
                resultList.add(inputLine);
            }
            while ((errorLine = errorStream.readLine()) != null) {
                if (withLog) {
                    LogArea.addText(errorLine, Color.red);
                }
                errorResultList.add(errorLine);
            }
            inputStream.close();
            errorStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            LogArea.addText(e.toString(), Color.red);
        }

        ShellResult shellResult = new ShellResult();
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
        return executeShell("adb devices", false);
    }
}
