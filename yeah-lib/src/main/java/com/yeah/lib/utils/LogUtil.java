package com.yeah.lib.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

/**
 * 日志工具类
 */
public class LogUtil {

    // 默认的logcat标志
    private final static String DEFAULT_TAG = "yeahLib";
    // 当前是否调试状态标志位
    private static boolean IS_DEBUG = false;

    /**
     * 初始化
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        if (context == null) {
            return;
        }

        IS_DEBUG = (context.getApplicationInfo().flags
                    & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    /**
     * 打印Debug类型的日志
     *
     * @param cls 类，方便追踪该日志是在哪个类中打印出来的
     * @param msg 要打印的信息
     */
    public static void logDebug(Class<?> cls, String msg) {
        if (IS_DEBUG) {
            Log.d(cls != null ?
                    cls.getSimpleName() : DEFAULT_TAG, getFormattedMsg(msg));
        }
    }

    /**
     * 打印Info类型的日志
     *
     * @param cls 类，方便追踪该日志是在哪个类中打印出来的
     * @param msg 要打印的信息
     */
    public static void logInfo(Class<?> cls, String msg) {
        if (IS_DEBUG) {
            Log.i(cls != null ?
                    cls.getSimpleName() : DEFAULT_TAG, getFormattedMsg(msg));
        }
    }

    /**
     * 打印Warn类型的日志
     *
     * @param cls 类，方便追踪该日志是在哪个类中打印出来的
     * @param msg 要打印的信息
     */
    public static void logWarn(Class<?> cls, String msg) {
        if (IS_DEBUG) {
            Log.w(cls != null ?
                    cls.getSimpleName() : DEFAULT_TAG, getFormattedMsg(msg));
        }
    }

    /**
     * 打印Error类型的日志
     *
     * @param cls 类，方便追踪该日志是在哪个类中打印出来的
     * @param msg 要打印的信息
     */
    public static void logError(Class<?> cls, String msg) {
        if (IS_DEBUG) {
            Log.e(cls != null ?
                    cls.getSimpleName() : DEFAULT_TAG, getFormattedMsg(msg));
        }
    }

    /**
     * 获取格式化后的信息字符串，方便过滤信息
     *
     * @param msg 格式化前的信息
     * @return 格式化后的信息
     */
    private static String getFormattedMsg(String msg) {
        return ">>>>>> " + msg + " <<<<<<";
    }
}
