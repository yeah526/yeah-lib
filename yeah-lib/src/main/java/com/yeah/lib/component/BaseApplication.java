package com.yeah.lib.component;

import android.app.Activity;
import android.app.Application;

import com.yeah.lib.utils.LogUtil;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by heweiyan on 2016/3/6.
 * <p/>
 * Application组件的抽象基类
 */
public abstract class BaseApplication extends Application {

    // Application实例
    private static BaseApplication application;
    // 所有正在运行的Activities的实例
    private Stack<Activity> activityStack = new Stack<>();

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        initUtils();
    }

    /**
     * 初始化各个工具类
     */
    private void initUtils() {
        // 初始化日志工具类
        LogUtil.init(this);
    }

    /**
     * 获取Application实例
     *
     * @return Application实例
     */
    public static BaseApplication getApplication() {
        return application;
    }

    /**
     * 注册Activity
     *
     * @param activity 要注册的Activity实例
     */
    public void registerActivity(Activity activity) {
        activityStack.push(activity);
    }

    /**
     * 反注册Activity
     *
     * @param activity 要反注册的Activity实例
     */
    public void unregisterActivity(Activity activity) {
        activityStack.remove(activity);
    }

    /**
     * 获取处于栈顶的Acitivty
     *
     * @return 处于栈顶的Acitivty实例。如果Activity栈为空，那么就返回null。
     */
    public Activity getTopActivity() {
        try {
            return activityStack.peek();
        } catch (EmptyStackException e) {
            return null;
        }
    }

    /**
     * 获取Activity栈的大小
     *
     * @return Activity栈中的Activity数量
     */
    public int getActivitiesCount() {
        return activityStack.size();
    }

    /**
     * 打印整个Activity栈结构
     * 用于Debug
     */
    public void logActivityStack() {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            LogUtil.logDebug(getClass(), "activity[" + i + "] = " + activityStack.get(i));
        }
    }

    /**
     * finish掉所有的Activities
     */
    public void finishAllActivities() {
        // 因为如果用activityStack去迭代finish Activity，在Activity onDestroy时，又会对activityStack进行删除操作，
        // 这时就会报ConcurrentModificationException异常，即在集合迭代时是不允许对集合进行删除或者添加操作，
        // 所以要复制一个集合，对这个复制集合进行迭代操作。
        Stack<Activity> cloneActivityStack = (Stack<Activity>) activityStack.clone();
        for (Activity activity : cloneActivityStack) {
            activity.finish();
        }
    }

    /**
     * 完全退出应用
     */
    public void exitApp() {
        // finish掉所有的Activities
        finishAllActivities();

        System.exit(0);
    }

    @Override
    public void onTerminate() {
        // finish掉所有的Activities
        finishAllActivities();

        super.onTerminate();
    }
}
