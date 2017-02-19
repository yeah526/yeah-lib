package com.yeah.lib.component.ui;

/**
 * Created by heweiyan on 2016/3/7.
 *
 * Every page's initialization interfaces.
 */
public interface IPageInitialization {

    /**
     * Get content view resourse id.
     *
     * @return The content view resource id.
     */
    int getContentViewResId();

    /**
     * Initialize data, especially the data from intent.
     */
    void initData();

    /**
     * Initialize views.
     */
    void initViews();

    /**
     * Set view listeners.
     */
    void setViewListeners();

    /**
     * Refresh page.
     */
    void refreshPage();
}
