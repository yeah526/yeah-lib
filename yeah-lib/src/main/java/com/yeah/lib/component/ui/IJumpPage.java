package com.yeah.lib.component.ui;

import android.content.Intent;

/**
 * Created by heweiyan on 2016/3/19.
 * <p/>
 * Jump page interfaces.
 */
public interface IJumpPage {

    /**
     * Jump to the target activity.
     *
     * @param targetAcvitityClass Target activity class.
     * @param isShowAnim          Whether show jump animation.
     */
    void jumpToActivity(Class<?> targetAcvitityClass, boolean isShowAnim);

    /**
     * Jump to the target activity.
     *
     * @param intent     The intent to jump to the activity.
     * @param isShowAnim Whether show jump animation.
     */
    void jumpToActivity(Intent intent, boolean isShowAnim);

    /**
     * Jump to the target activity.
     *
     * @param targetAcvitityClass Target activity class.
     * @param requestCode         If >= 0, this code will be returned in onActivityResult() when the activity exits.
     * @param isShowAnim          Whether show jump animation.
     */
    void jumpToActivity(Class<?> targetAcvitityClass, int requestCode, boolean isShowAnim);

    /**
     * Jump to the target activity.
     *
     * @param intent      The intent to jump to the activity.
     * @param requestCode If >= 0, this code will be returned in onActivityResult() when the activity exits.
     * @param isShowAnim  Whether show jump animation.
     */
    void jumpToActivity(Intent intent, int requestCode, boolean isShowAnim);
}
