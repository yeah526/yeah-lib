package com.yeah.lib.utils;

import android.content.Context;
import android.widget.Toast;

import com.yeah.lib.component.BaseApplication;

/**
 * Toast������
 */
public class ToastUtil {

    // ���һ����ʾ��Toastʵ��
    private static Toast mToast;

    /**
     * ��ʾToast��ʾ
     * ������ݽ�����contextʵ������ǰ̨������ʾ��Activity���Ͳ���Toast����ʾ
     *
     * @param context ������ʵ��
     * @param msg     ҪToast����Ϣ
     */
    public static void showMsg(Context context, String msg) {
        if (BaseApplication.getApplication().getTopActivity().equals(context)) {
            showMsgWithoutLimit(context, msg);
        }
    }

    /**
     * ��ʾToast��ʾ
     *
     * @param context ������ʵ��
     * @param msg     ҪToast����Ϣ
     */
    public static void showMsgWithoutLimit(Context context, String msg) {
        if (context == null) {
            LogUtil.logError(ToastUtil.class,
                    "method showMsgWithoutLimit()��context is null.");
            return;
        }

        if (mToast != null) {
            // �����һ�ε�Toast��δ��������ô�Ƚ�����һ�ε�Toast
            mToast.cancel();
        }
        mToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        mToast.show();
    }
}
