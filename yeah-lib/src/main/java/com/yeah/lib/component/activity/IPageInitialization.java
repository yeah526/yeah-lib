package com.yeah.lib.component.activity;

/**
 * Created by heweiyan on 2016/3/7.
 * <p/>
 * Every page's initialization interface.
 */
public interface IPageInitialization {

    /**
     * Init views.
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
