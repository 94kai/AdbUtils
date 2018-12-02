package com.xk.adbutils;

public class Constant {


    public static String example = "{\n" +
            "  \"deviceList\": [\n" +
            "    {\n" +
            "      \"deviceName\": \"小米8\",\n" +
            "      \"deviceId\": \"121b1037\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"deviceName\": \"金立\",\n" +
            "      \"deviceId\": \"79KFPJLNY5OJUCSC\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"deviceName\": \"模拟器1号\",\n" +
            "      \"deviceId\": \"emulator-5558\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"adbCmdList\": [\n" +
            "    {\n" +
            "      \"funName\": \"安装搜索插件并重启\",\n" +
            "      \"cmdArray\": [\n" +
            "        \"push /Users/xuekai1/project/asprojects/bundle-search/application/build/outputs/apk/application-debug.apk /sdcard/aura/lib/libcom.jd.lib.search.so\",\n" +
            "        \"shell am force-stop  com.jingdong.app.mall\",\n" +
            "        \"adb shell am start -n com.jingdong.app.mall/com.jingdong.app.mall.main.MainActivity\"\n" +
            "      ],\n" +
            "      \"type\": \"adb\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"funName\": \"摇晃手机\",\n" +
            "      \"cmdArray\": [\n" +
            "        \"shell input keyevent 82\"\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"funName\": \"连接rn\",\n" +
            "      \"cmdArray\": [\n" +
            "        \"reverse tcp:8081 tcp:8081\"\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"funName\": \"杀死京东进程\",\n" +
            "      \"cmdArray\": [\n" +
            "        \"shell am force-stop  com.jingdong.app.mall\"\n" +
            "      ],\n" +
            "      \"type\": \"adb\",\n" +
            "      \"isHide\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"funName\": \"卸载京东app\",\n" +
            "      \"cmdArray\": [\n" +
            "        \"uninstall com.jingdong.app.mall\"\n" +
            "      ],\n" +
            "      \"type\": \"adb\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"funName\": \"清除缓存并重启\",\n" +
            "      \"cmdArray\": [\n" +
            "        \"shell pm clear com.jingdong.app.mall\",\n" +
            "        \"shell am start -n com.jingdong.app.mall/com.jingdong.app.mall.main.MainActivity\"\n" +
            "      ],\n" +
            "      \"type\": \"adb\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"funName\": \"截屏\",\n" +
            "      \"cmdArray\": [\n" +
            "        \"shell /system/bin/screencap -p /sdcard/screenshot${DATE}.png\",\n" +
            "        \"pull /sdcard/screenshot${DATE}.png\",\n" +
            "        \"shell rm /sdcard/screenshot${DATE}.png\"\n" +
            "      ],\n" +
            "      \"type\": \"adb\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"funName\": \"录制视频并保存到电脑\",\n" +
            "      \"cmdArray\": [\n" +
            "        \"shell screenrecord  --time-limit ${videoTime} /sdcard/demo.mp4\",\n" +
            "        \"pull /sdcard/demo.mp4\",\n" +
            "        \"shell rm /sdcard/demo.mp4\"\n" +
            "      ],\n" +
            "      \"type\": \"adb\",\n" +
            "      \"isHide\": false\n" +
            "    },\n" +
            "    {\n" +
            "      \"funName\": \"跑monkey\",\n" +
            "      \"cmdArray\": [\n" +
            "        \"root\",\n" +
            "        \"shell su -c 'am start -W -n com.jingdong.app.mall/com.jd.lib.search.view.Activity.SearchActivity --ei to 4'\",\n" +
            "        \"shell monkey -p com.jingdong.app.mall --throttle 0 --pct-touch 20 --pct-motion 20 --pct-trackball 20 --pct-majornav 20 --pct-appswitch 20 --ignore-timeouts --ignore-crashes --ignore-security-exceptions --monitor-native-crashes -v -v -v 10\"\n" +
            "      ],\n" +
            "      \"type\": \"adb\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"variateList\": {\n" +
            "    \"adbPath\": \"/Users/xuekai/Library/Android/sdk/platform-tools/adb\",\n" +
            "    \"apkPath\": \"/Users/xuekai/projects/idea_workspace/MUtils\",\n" +
            "    \"videoTime\":\"10\"\n" +
            "  }\n" +
            "}";

    public static String helpInfo = "" +
            "1.variateList字段（config.json中定义好的数据会展现在\"添加变量区\"）：\n" +
            "       1.uninstallPackageName指定安装app的包名，默认为JDApp,非必填\n" +
            "       2.adbPath指定adb的目录，必填\n" +
            "       3.apkPath指定安装的apk所在的目录，该目录下的所有apk会展现在小工具中，默认为当前目录，非必填\n" +
            "       4.可以添加任意字段，用于替换adbCmdList中${xx}定义的同名占位符。如事例json中的${videoTime}\n";

    public static String help = "\n创建\"instance.json\",放置在同级目录下\n\n\n " +
            helpInfo + "\n"
            + example;
    //variate key adbpath
    public static String KEY_ADB_PATH = "adbPath";
    //variate key apkpath
    public static String KEY_APK_PATH = "apkPath";


}
