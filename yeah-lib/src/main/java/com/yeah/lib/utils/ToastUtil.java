package com.yeah.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.yeah.lib.component.BaseApplication;

/**
 * Created by heweiyan on 2016/3/6.
 *
 * Toast utility class.
 */
public class ToastUtil {

    // the last shown Toast instance
    private static Toast mToast;

    /**
     * Show toast only if the context equals the showing activity.
     *
     * @param context
     * @param msg     Message that toast.
     */
    public static void showMsg(Context context, String msg) {
        Activity topActivity = BaseApplication.getApplication().getTopActivity();
        if (topActivity == null || !topActivity.equals(context)) {
            return;
        }

        showMsgWithoutLimit(context, msg);
    }

    /**
     * Show Toast.
     *
     * @param context
     * @param msg     Message that toast.
     */
    public static void showMsgWithoutLimit(Context context, String msg) {
        if (context == null) {
            throw new NullPointerException("Context is null.");
        }

        if (mToast != null) {
            // cancel the last shown toast first
            mToast.cancel();
        }
        mToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        mToast.show();
    }
}
