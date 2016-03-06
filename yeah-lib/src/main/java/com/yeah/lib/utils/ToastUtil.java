package com.yeah.lib.utils;

import android.content.Context;
import android.widget.Toast;

import com.yeah.lib.component.BaseApplication;

/**
 * Created by heweiyan on 2016/3/6.
 * <p/>
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
        if (BaseApplication.getApplication().getTopActivity().equals(context)) {
            showMsgWithoutLimit(context, msg);
        }
    }

    /**
     * Show Toast.
     *
     * @param context
     * @param msg     Message that toast.
     */
    public static void showMsgWithoutLimit(Context context, String msg) {
        if (context == null) {
            LogUtil.logError(ToastUtil.class,
                    "method showMsgWithoutLimit(): context is null.");
            return;
        }

        if (mToast != null) {
            // cancel the last shown toast first
            mToast.cancel();
        }
        mToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        mToast.show();
    }
}
