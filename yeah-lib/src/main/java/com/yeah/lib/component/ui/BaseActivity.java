package com.yeah.lib.component.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.yeah.lib.component.BaseApplication;
import com.yeah.lib.component.broadcastReceiver.NetworkWatchDogReceiver;

/**
 * Created by heweiyan on 2016/3/6.
 * <p/>
 * Base activity class.
 */
public abstract class BaseActivity extends Activity implements IActivity {

    // page state manager
    private PageStateManager pageStateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // register activity to application
        BaseApplication.getApplication().registerActivity(this);

        if (getContentViewResId() > 0) {
            setContentView(getContentViewResId());

            View mMultiStateView = PageStateManager.findMultiStateView(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0));
            if (mMultiStateView != null) {
                pageStateManager = new PageStateManager(mMultiStateView);
            }
        }

        initData();
        initNavigationBar();
        initViews();
        setViewListeners();
    }

    @Override
    public void setPageStateView(View mPageStateView) {
        if (pageStateManager == null) {
            pageStateManager = new PageStateManager(mPageStateView);
        } else {
            pageStateManager.setPageStateView(mPageStateView);
        }
    }

    @Override
    public void refreshPage() {
        // let sub class override this method when you need to refresh the page
    }

    @Override
    public void showContentView() {
        if (pageStateManager == null) {
            throw new NullPointerException("There is no multiple state view in your content view.");
        }

        pageStateManager.showContentView();
    }

    @Override
    public void showLoadingView() {
        if (pageStateManager == null) {
            throw new NullPointerException("There is no multiple state view in your content view.");
        }

        pageStateManager.showLoadingView();
    }

    @Override
    public void showEmptyView() {
        if (pageStateManager == null) {
            throw new NullPointerException("There is no multiple state view in your content view.");
        }

        pageStateManager.showEmptyView();
    }

    @Override
    public void showErrorView() {
        if (pageStateManager == null) {
            throw new NullPointerException("There is no multiple state view in your content view.");
        }

        pageStateManager.showErrorView();
    }

    @Override
    public void setOnNetworkChangeListener(NetworkWatchDogReceiver.OnNetworkChangeListener listener) {
        registerReceiver(NetworkWatchDogReceiver.getInstance(), NetworkWatchDogReceiver.getIntentFilter());
        NetworkWatchDogReceiver.getInstance().addOnNetworkChangeListener(this, listener);
    }

    @Override
    public void unregiserOnNetworkChangeListener() {
        if (NetworkWatchDogReceiver.getInstance().removeOnNetworkChangeListener(this) != null) {
            unregisterReceiver(NetworkWatchDogReceiver.getInstance());
        }
    }

    @Override
    protected void onDestroy() {
        // unregister activity to application
        BaseApplication.getApplication().unregisterActivity(this);
        // unregister the network state change listener
        unregiserOnNetworkChangeListener();

        super.onDestroy();
    }
}