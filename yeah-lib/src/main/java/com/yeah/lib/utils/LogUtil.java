package com.yeah.lib.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

/**
 * ��־������
 */
public class LogUtil {

    // Ĭ�ϵ�logcat��־
    private final static String DEFAULT_TAG = "yeahLib";
    // ��ǰ�Ƿ����״̬��־λ
    private static boolean IS_DEBUG = false;

    /**
     * ��ʼ��
     *
     * @param context ������
     */
    public static void init(Context context) {
        if (context == null) {
            return;
        }

        IS_DEBUG = (context.getApplicationInfo().flags
                    & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    /**
     * ��ӡDebug���͵���־
     *
     * @param cls �࣬����׷�ٸ���־�����ĸ����д�ӡ������
     * @param msg Ҫ��ӡ����Ϣ
     */
    public static void logDebug(Class<?> cls, String msg) {
        if (IS_DEBUG) {
            Log.d(cls != null ?
                    cls.getSimpleName() : DEFAULT_TAG, getFormattedMsg(msg));
        }
    }

    /**
     * ��ӡInfo���͵���־
     *
     * @param cls �࣬����׷�ٸ���־�����ĸ����д�ӡ������
     * @param msg Ҫ��ӡ����Ϣ
     */
    public static void logInfo(Class<?> cls, String msg) {
        if (IS_DEBUG) {
            Log.i(cls != null ?
                    cls.getSimpleName() : DEFAULT_TAG, getFormattedMsg(msg));
        }
    }

    /**
     * ��ӡWarn���͵���־
     *
     * @param cls �࣬����׷�ٸ���־�����ĸ����д�ӡ������
     * @param msg Ҫ��ӡ����Ϣ
     */
    public static void logWarn(Class<?> cls, String msg) {
        if (IS_DEBUG) {
            Log.w(cls != null ?
                    cls.getSimpleName() : DEFAULT_TAG, getFormattedMsg(msg));
        }
    }

    /**
     * ��ӡError���͵���־
     *
     * @param cls �࣬����׷�ٸ���־�����ĸ����д�ӡ������
     * @param msg Ҫ��ӡ����Ϣ
     */
    public static void logError(Class<?> cls, String msg) {
        if (IS_DEBUG) {
            Log.e(cls != null ?
                    cls.getSimpleName() : DEFAULT_TAG, getFormattedMsg(msg));
        }
    }

    /**
     * ��ȡ��ʽ�������Ϣ�ַ��������������Ϣ
     *
     * @param msg ��ʽ��ǰ����Ϣ
     * @return ��ʽ�������Ϣ
     */
    private static String getFormattedMsg(String msg) {
        return ">>>>>> " + msg + " <<<<<<";
    }
}
