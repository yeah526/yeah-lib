package com.yeah.lib.component;

import android.app.Activity;
import android.app.Application;

import com.yeah.lib.utils.LogUtil;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by heweiyan on 2016/3/6.
 * <p/>
 * Application����ĳ������
 */
public abstract class BaseApplication extends Application {

    // Applicationʵ��
    private static BaseApplication application;
    // �����������е�Activities��ʵ��
    private Stack<Activity> activityStack = new Stack<>();

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        initUtils();
    }

    /**
     * ��ʼ������������
     */
    private void initUtils() {
        // ��ʼ����־������
        LogUtil.init(this);
    }

    /**
     * ��ȡApplicationʵ��
     *
     * @return Applicationʵ��
     */
    public static BaseApplication getApplication() {
        return application;
    }

    /**
     * ע��Activity
     *
     * @param activity Ҫע���Activityʵ��
     */
    public void registerActivity(Activity activity) {
        activityStack.push(activity);
    }

    /**
     * ��ע��Activity
     *
     * @param activity Ҫ��ע���Activityʵ��
     */
    public void unregisterActivity(Activity activity) {
        activityStack.remove(activity);
    }

    /**
     * ��ȡ����ջ����Acitivty
     *
     * @return ����ջ����Acitivtyʵ�������ActivityջΪ�գ���ô�ͷ���null��
     */
    public Activity getTopActivity() {
        try {
            return activityStack.peek();
        } catch (EmptyStackException e) {
            return null;
        }
    }

    /**
     * ��ȡActivityջ�Ĵ�С
     *
     * @return Activityջ�е�Activity����
     */
    public int getActivitiesCount() {
        return activityStack.size();
    }

    /**
     * ��ӡ����Activityջ�ṹ
     * ����Debug
     */
    public void logActivityStack() {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            LogUtil.logDebug(getClass(), "activity[" + i + "] = " + activityStack.get(i));
        }
    }

    /**
     * finish�����е�Activities
     */
    public void finishAllActivities() {
        // ��Ϊ�����activityStackȥ����finish Activity����Activity onDestroyʱ���ֻ��activityStack����ɾ��������
        // ��ʱ�ͻᱨConcurrentModificationException�쳣�����ڼ��ϵ���ʱ�ǲ�����Լ��Ͻ���ɾ��������Ӳ�����
        // ����Ҫ����һ�����ϣ���������Ƽ��Ͻ��е���������
        Stack<Activity> cloneActivityStack = (Stack<Activity>) activityStack.clone();
        for (Activity activity : cloneActivityStack) {
            activity.finish();
        }
    }

    /**
     * ��ȫ�˳�Ӧ��
     */
    public void exitApp() {
        // finish�����е�Activities
        finishAllActivities();

        System.exit(0);
    }

    @Override
    public void onTerminate() {
        // finish�����е�Activities
        finishAllActivities();

        super.onTerminate();
    }
}
