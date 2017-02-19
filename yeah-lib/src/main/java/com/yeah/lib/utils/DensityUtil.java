package com.yeah.lib.utils;

import android.content.Context;

/**
 * Created by heweiyan on 2016/3/21.
 *
 * Density utility class.
 */
public class DensityUtil {
    /**
     * Convert a pixel value to a dp value.
     *
     * @param context
     * @param pxValue The pixel value.
     * @return The converted dp value.
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f); //plus 0.5 for rounding the result
    }

    /**
     * Convert a dp value to a pixel value.
     *
     * @param context
     * @param dpValue The dp value.
     * @return The converted pixel value.
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f); //plus 0.5 for rounding the result
    }

    /**
     * Convert a pixel value to a sp value.
     *
     * @param context
     * @param pxValue The pixel value.
     * @return The converted sp value.
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f); //plus 0.5 for rounding the result
    }

    /**
     * Convert a sp value to a pixel value.
     *
     * @param context
     * @param spValue The sp value.
     * @return The converted pixel value.
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f); //plus 0.5 for rounding the result
    }
}
