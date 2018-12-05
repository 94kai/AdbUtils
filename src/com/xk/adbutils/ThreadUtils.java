package com.xk.adbutils;

import com.xk.adbutils.view.LogArea;

import java.awt.Color;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 执行耗时任务（adb、io）的子线程，保证所有adb有序执行
 *
 * @author xuekai1
 * @date 2018/12/4
 */
public class ThreadUtils {

    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());


    public static void execute(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    public static void executeDelay(Runnable runnable, long delayTime) {
        new Thread(() -> {
            try {
                Thread.sleep(delayTime);
                threadPoolExecutor.execute(runnable);
            } catch (Exception e) {
                LogArea.addText(e.getMessage(), Color.red);
            }
        }).start();
    }

}
