package com.yeah.lib.component.ui;

import com.yeah.lib.component.broadcastReceiver.NetworkWatchDogReceiver;

/**
 * Created by heweiyan on 2016/4/5.
 *
 * Network state interfaces.
 */
public interface INetworkState {

    /**
     * Register a callback to be invoked when the network state is changed.
     *
     * @param listener The callback that will run.
     */
    void setOnNetworkChangeListener(NetworkWatchDogReceiver.OnNetworkChangeListener listener);

    /**
     * Unregister the network state change listener.
     */
    void unregiserOnNetworkChangeListener();
}
