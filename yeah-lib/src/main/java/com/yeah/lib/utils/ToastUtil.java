package com.yeah.lib.utils;

import android.content.Context;
import android.widget.Toast;

import com.yeah.lib.component.BaseApplication;

/**
 * Toast工具类
 */
public class ToastUtil {

    // 最后一次显示的Toast实例
    private static Toast mToast;

    /**
     * 显示Toast提示
     * 如果传递进来的context实例不是前台正在显示的Activity，就不会Toast该提示
     *
     * @param context 上下文实例
     * @param msg     要Toast的信息
     */
    public static void showMsg(Context context, String msg) {
        if (BaseApplication.getApplication().getTopActivity().equals(context)) {
            showMsgWithoutLimit(context, msg);
        }
    }

    /**
     * 显示Toast提示
     *
     * @param context 上下文实例
     * @param msg     要Toast的信息
     */
    public static void showMsgWithoutLimit(Context context, String msg) {
        if (context == null) {
            LogUtil.logError(ToastUtil.class,
                    "method showMsgWithoutLimit()：context is null.");
            return;
        }

        if (mToast != null) {
            // 如果上一次的Toast还未结束，那么先结束上一次的Toast
            mToast.cancel();
        }
        mToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        mToast.show();
    }
}
