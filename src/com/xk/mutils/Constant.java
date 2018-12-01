package com.xk.mutils;

public class Constant {

    public static String help = "\n创建\"config.json\",放置在同级目录下\n\n\n {\n" +
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
}
