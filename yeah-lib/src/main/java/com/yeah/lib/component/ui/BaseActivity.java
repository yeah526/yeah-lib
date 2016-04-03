package com.yeah.lib.component.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.kennyc.view.MultiStateView;
import com.yeah.lib.component.BaseApplication;

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
        }

        initData();
        initViews();
        setViewListeners();

        MultiStateView mMultiStateView = PageStateManager.findMultiStateView(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0));
        if (mMultiStateView != null) {
            pageStateManager = new PageStateManager(mMultiStateView);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // unregister activity to application
        BaseApplication.getApplication().unregisterActivity(this);
    }

    @Override
    public void setPageStateView(MultiStateView mPageStateView) {
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
        pageStateManager.showContentView();
    }

    @Override
    public void showLoadingView() {
        pageStateManager.showLoadingView();
    }

    @Override
    public void showEmptyView() {
        pageStateManager.showEmptyView();
    }

    @Override
    public void showErrorView() {
        pageStateManager.showErrorView();
    }
}
