package com.xk.adbutils;

public class Constant {


    public static String example;

    static {
        try {
            // TODO: by xk 2018/12/3 上午10:59 需要写到包中
            example = FileUtils.readStringFromFile("./config.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String helpInfo = "" +
            "1.variateList字段（config.json中定义好的数据会展现在\"添加变量区\"）：\n" +
            "       1.uninstallPackageName指定安装app的包名，默认为JDApp,非必填\n" +
            "       2.adbPath指定adb的目录，必填\n" +
            "       3.apkPath指定安装的apk所在的目录，该目录下的所有apk会展现在小工具中，默认为当前目录，非必填\n" +
            "       4.可以添加任意字段，用于替换adbCmdList中${xx}定义的同名占位符。如事例json中的${videoTime}\n";

    public static String help = "\n创建\"config.json\",放置在同级目录下\n\n\n " +
            helpInfo + "\n"
            + example;
    //variate key adbpath
    public static String KEY_ADB_PATH = "adbPath";
    //variate key apkpath
    public static String KEY_APK_PATH = "apkPath";


}
