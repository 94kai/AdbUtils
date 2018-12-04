package com.xk.adbutils;

public class Constant {


    public static String example;
    public static String helpInfo;
    public static String JDPackageName = "com.jingdong.app.mall";

    static {
        try {
            // TODO: by xk 2018/12/3 上午10:59 需要写到包中
            helpInfo = FileUtils.readStringFromRes("helpinfo");
            example = FileUtils.readStringFromRes("example.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String help = helpInfo +
            "\n创建\"config.json\",放置在同级目录下\n\n\n Example\n\n\n"
            + example;
    //variate key adbpath
    public static String KEY_ADB_PATH = "adbPath";
    //variate key apkpath
    public static String KEY_APK_PATH = "apkPath";


}
