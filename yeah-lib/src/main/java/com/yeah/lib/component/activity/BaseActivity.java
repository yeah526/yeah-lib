package com.yeah.lib.component.activity;

import android.app.Activity;
import android.os.Bundle;

import com.yeah.lib.component.BaseApplication;

/**
 * Created by heweiyan on 2016/3/6.
 * <p/>
 * Base activity class.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseApplication.getApplication().registerActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        BaseApplication.getApplication().unregisterActivity(this);
    }
}
