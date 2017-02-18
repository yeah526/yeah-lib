package com.yeah.lib.component.broadcastReceiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by heweiyan on 2016/4/3.
 * <p/>
 * Monitor network status broadcast receiver.
 */
public class NetworkWatchDogReceiver extends BaseBroadcastReceiver {

    private Map<Object, OnNetworkChangeListener> listeners = new HashMap<>();

    /**
     * Interface definition for a callback to be invoked when network changed.
     */
    public interface OnNetworkChangeListener {
        /**
         * Called when network connection changed.
         *
         * @param isConnected {@code true} if network connectivity exists, {@code false} otherwise.
         */
        void onNetworkConnectionChange(boolean isConnected);

        /**
         * Called when network type changed.
         *
         * @param networkInfo The current network information or
         *                    {@code null} if WIFI and mobile data connection is not active.
         */
        void onNetworkTypeChange(NetworkInfo networkInfo);
    }

    private NetworkWatchDogReceiver() {
    }

    private static NetworkWatchDogReceiver receiver;

    /**
     * Get NetworkWatchDogReceiver instance.
     *
     * @return The singleton instance of NetworkWatchDogReceiver.
     */
    public static NetworkWatchDogReceiver getInstance() {
        /* Double-Check Locking */
        if (receiver == null) {
            synchronized (NetworkWatchDogReceiver.class) {
                if (receiver == null) {
                    receiver = new NetworkWatchDogReceiver();
                }
            }
        }

        return receiver;
    }

    private static IntentFilter intentFilter;

    /**
     * Get the receiver's intent filter.
     *
     * @return The intent filter.
     */
    public static IntentFilter getIntentFilter() {
        if (intentFilter == null) {
            intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        }

        return intentFilter;
    }

    /**
     * Add one more OnNetworkChangeListener.
     *
     * @param key      The listener's key.
     * @param listener The listener.
     */
    public void addOnNetworkChangeListener(Object key, OnNetworkChangeListener listener) {
        if (listener == null) {
            throw new NullPointerException("The listener is null.");
        }

        listeners.put(key, listener);
    }

    /**
     * Removes the listener with the specified key from the listeners.
     *
     * @param key The key of the listeners to remove.
     * @return The instance of the removed listener or {@code null} if no listener
     * for the specified key was found.
     */
    public Object removeOnNetworkChangeListener(Object key) {
        return listeners.remove(key);
    }


    // the last network information
    private NetworkInfo lastNetworkInfo;

    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        boolean isLastConnected = false;
        int lastNetworkType = -1;
        if (lastNetworkInfo != null) {
            if (lastNetworkInfo.isConnected()) {
                isLastConnected = true;
            }
            lastNetworkType = lastNetworkInfo.getType();
        }
        boolean isCurrentConnected = false;
        int currentNetworkType = -1;
        if (activeNetworkInfo != null) {
            if (activeNetworkInfo.isConnected()) {
                isCurrentConnected = true;
            }
            currentNetworkType = activeNetworkInfo.getType();
        }

        if (isLastConnected != isCurrentConnected) {
            for (Map.Entry<Object, OnNetworkChangeListener> entry : listeners.entrySet()) {
                entry.getValue().onNetworkConnectionChange(isCurrentConnected);
            }
        }
        if (lastNetworkType != currentNetworkType) {
            for (Map.Entry<Object, OnNetworkChangeListener> entry : listeners.entrySet()) {
                entry.getValue().onNetworkTypeChange(activeNetworkInfo);
            }
        }

        lastNetworkInfo = activeNetworkInfo;
    }
}
