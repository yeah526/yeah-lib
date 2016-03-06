package com.yeah.lib.component;

import android.app.Activity;
import android.app.Application;

import com.yeah.lib.utils.LogUtil;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by heweiyan on 2016/3/6.
 * <p/>
 * Base Application class.
 */
public abstract class BaseApplication extends Application {

    // application instance
    private static BaseApplication application;
    // stack that contains created activities
    private Stack<Activity> activityStack = new Stack<>();

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        initUtils();
    }

    /**
     * Init the basic utilities.
     */
    private void initUtils() {
        // init logging utility
        LogUtil.init(this);
    }

    /**
     * Get application instance．
     *
     * @return Application instance.
     */
    public static BaseApplication getApplication() {
        return application;
    }

    /**
     * Register activity.
     * Application will save this activity to the activity stack.
     *
     * @param activity Activity that need to be registered.
     */
    public void registerActivity(Activity activity) {
        activityStack.push(activity);
    }

    /**
     * Unregister activity.
     * Application will remove this activity from the activity stack.
     *
     * @param activity Activity that need to be unregistered.
     */
    public void unregisterActivity(Activity activity) {
        activityStack.remove(activity);
    }

    /**
     * Get the top acitivty.
     *
     * @return The top activity.
     */
    public Activity getTopActivity() {
        try {
            return activityStack.peek();
        } catch (EmptyStackException e) {
            return null;
        }
    }

    /**
     * Get the count of registered activities.
     *
     * @return The count of registered activities.
     */
    public int getActivitiesCount() {
        return activityStack.size();
    }

    /**
     * Log all the activities.
     * Normally call this function for debug.
     */
    public void logActivityStack() {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            LogUtil.logDebug(getClass(), "activity[" + i + "] = " + activityStack.get(i));
        }
    }

    /**
     * Finish all the running activities.
     */
    public void finishAllActivities() {
        // The collection will throw ConcurrentModificationException
        // if add or remove elements when iterate it.
        // So it needs a copy collection here.
        Stack<Activity> cloneActivityStack = (Stack<Activity>) activityStack.clone();
        for (Activity activity : cloneActivityStack) {
            activity.finish();
        }
    }

    /**
     * Exit this app.
     */
    public void exitApp() {
        finishAllActivities();
        System.exit(0);
    }

    @Override
    public void onTerminate() {
        finishAllActivities();

        super.onTerminate();
    }
}
