package com.xsp.proxy.activity;

import android.app.Activity;

import com.xsp.library.util.LogUtil;

public class BaseActivity extends Activity {

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d("xsp", "onResume() :" + this.getClass().getSimpleName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d("xsp", "onPause() :" + this.getClass().getSimpleName());
    }
}
