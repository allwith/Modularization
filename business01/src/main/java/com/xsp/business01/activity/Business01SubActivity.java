package com.xsp.business01.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.xsp.business01.R;
import com.xsp.business01.util.RequestCodeUtil;
import com.xsp.library.util.ActivityUtil;
import com.xsp.proxy.activity.BaseActivity;

public class Business01SubActivity extends BaseActivity {

    public static void launch(Activity activity) {
        if (null == activity) {
            return;
        }
        Intent intent = new Intent(activity, Business01SubActivity.class);
        ActivityUtil.startActivityForResult(activity, intent, RequestCodeUtil.BUSINESS_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bussiness01_sub_activity);
    }

}
