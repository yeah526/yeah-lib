package com.yeah.lib.component.ui;

import android.view.View;
import android.view.ViewGroup;

import com.kennyc.view.MultiStateView;

/**
 * Created by heweiyan on 2016/3/16.
 * <p/>
 * Provides methods to manage the page state.
 */
public class PageStateManager implements IPageState {

    // multiple page state view
    private MultiStateView mPageStateView;

    public PageStateManager(View mPageStateView) {
        setPageStateView(mPageStateView);
    }

    /**
     * Find the first multiple state view recursively.
     *
     * @param mRootView The root view that start to find the multiple state view.
     * @return The first multiple state view or null if there is no multiple state view in the root view.
     */
    public static View findMultiStateView(View mRootView) {
        if (mRootView == null || !(mRootView instanceof ViewGroup)) {
            return null;
        }

        if (mRootView instanceof MultiStateView) {
            return mRootView;
        }

        for (int i = 0; i < ((ViewGroup) mRootView).getChildCount(); i++) {
            View view = ((ViewGroup) mRootView).getChildAt(i);
            if (view instanceof MultiStateView) {
                return view;
            } else if (view instanceof ViewGroup) {
                return findMultiStateView(view);
            }
        }

        return null;
    }

    @Override
    public void setPageStateView(View mPageStateView) {
        if (!(mPageStateView instanceof MultiStateView)) {
            throw new ClassCastException("The mPageStateView is not a MultiStateView.");
        }

        this.mPageStateView = (MultiStateView) mPageStateView;
    }

    @Override
    public void showContentView() {
        if (mPageStateView == null) {
            throw new NullPointerException("The page state view is null.");
        }

        mPageStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }

    @Override
    public void showLoadingView() {
        if (mPageStateView == null) {
            throw new NullPointerException("The page state view is null.");
        }

        mPageStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void showEmptyView() {
        if (mPageStateView == null) {
            throw new NullPointerException("The page state view is null.");
        }

        mPageStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
    }

    @Override
    public void showErrorView() {
        if (mPageStateView == null) {
            throw new NullPointerException("The page state view is null.");
        }

        mPageStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
    }
}
