package com.yeah.lib.component.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kennyc.view.MultiStateView;

/**
 * Created by heweiyan on 2016/3/20.
 * <p/>
 * Base fragment class.
 */
public abstract class BaseFragment extends Fragment implements IFragment {

    // root view
    protected View mRootView;
    // page state manager
    private PageStateManager pageStateManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getContentViewResId() > 0) {
            mRootView = inflater.inflate(getContentViewResId(), container, false);
        }

        initData();
        initViews();
        setViewListeners();

        MultiStateView mMultiStateView = PageStateManager.findMultiStateView(mRootView);
        if (mMultiStateView != null) {
            pageStateManager = new PageStateManager(mMultiStateView);
        }

        return mRootView;
    }

    @Override
    public void refreshPage() {
        // let sub class override this method when you need to refresh the page
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
