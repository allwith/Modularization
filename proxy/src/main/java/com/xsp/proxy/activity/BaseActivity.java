package com.xsp.proxy.activity;

import android.app.Activity;
import android.os.Bundle;

import com.xsp.proxy.BaseApplication;
import com.xsp.library.util.LogUtil;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.getIns().addActivity(this);
    }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getIns().removeActivity(this);
    }

}
