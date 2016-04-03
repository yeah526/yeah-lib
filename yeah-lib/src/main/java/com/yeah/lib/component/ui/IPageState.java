package com.yeah.lib.component.ui;

import com.kennyc.view.MultiStateView;

/**
 * Created by heweiyan on 2016/3/15.
 * <p/>
 * Page state operation interfaces.
 */
public interface IPageState {

    /**
     * Set the specified multiple page state view.
     *
     * @param mPageStateView The specified multiple page state view.
     */
    void setPageStateView(MultiStateView mPageStateView);

    /**
     * Show the content view.
     */
    void showContentView();

    /**
     * Show the loading view.
     */
    void showLoadingView();

    /**
     * Show the empty view.
     */
    void showEmptyView();

    /**
     * Show the error view.
     */
    void showErrorView();
}