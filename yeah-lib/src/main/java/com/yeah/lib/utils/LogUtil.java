package com.yeah.lib.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

/**
 * Created by heweiyan on 2016/3/6.
 *
 * Logging utility class.
 */
public class LogUtil {

    // default logging tag
    private final static String DEFAULT_TAG = "yeahLib";
    // flag that control whether output logging
    private static boolean IS_DEBUG = false;

    public static void init(Context context) {
        if (context == null) {
            return;
        }

        IS_DEBUG = (context.getApplicationInfo().flags
                & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    /**
     * Output Debug type logging.
     *
     * @param cls The class where the logging output.
     * @param msg Message that display on the logcat.
     */
    public static void logDebug(Class<?> cls, String msg) {
        if (IS_DEBUG) {
            Log.d(cls != null ?
                    cls.getSimpleName() : DEFAULT_TAG, getFormattedMsg(msg));
        }
    }

    /**
     * Output Info type logging.
     *
     * @param cls The class where the logging output.
     * @param msg Message that display on the logcat.
     */
    public static void logInfo(Class<?> cls, String msg) {
        if (IS_DEBUG) {
            Log.i(cls != null ?
                    cls.getSimpleName() : DEFAULT_TAG, getFormattedMsg(msg));
        }
    }

    /**
     * Output Warn type logging.
     *
     * @param cls The class where the logging output.
     * @param msg Message that display on the logcat.
     */
    public static void logWarn(Class<?> cls, String msg) {
        if (IS_DEBUG) {
            Log.w(cls != null ?
                    cls.getSimpleName() : DEFAULT_TAG, getFormattedMsg(msg));
        }
    }

    /**
     * Output Error type logging.
     *
     * @param cls The class where the logging output.
     * @param msg Message that display on the logcat.
     */
    public static void logError(Class<?> cls, String msg) {
        if (IS_DEBUG) {
            Log.e(cls != null ?
                    cls.getSimpleName() : DEFAULT_TAG, getFormattedMsg(msg));
        }
    }

    /**
     * Format messages to facilitate filtering
     *
     * @param msg Original message.
     * @return Fomatted message.
     */
    private static String getFormattedMsg(String msg) {
        return "------ " + msg + " ------";
    }
}
