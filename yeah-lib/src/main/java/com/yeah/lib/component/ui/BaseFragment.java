package com.yeah.lib.component.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by heweiyan on 2016/3/20.
 *
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

            View mMultiStateView = PageStateManager.findMultiStateView(mRootView);
            if (mMultiStateView != null) {
                pageStateManager = new PageStateManager(mMultiStateView);
            }
        }
        initData();
        initViews();
        setViewListeners();

        return mRootView;
    }

    @Override
    public void refreshPage() {
        // let sub class override this method when you need to refresh the page
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
}
