package com.xsp.business02.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xsp.business02.R;
import com.xsp.library.router.Route;
import com.xsp.library.router.UrlRouter;
import com.xsp.library.util.LogUtil;
import com.xsp.proxy.activity.BaseActivity;

public class Business02Activity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bussiness02_activity);

        Route startedRoute = UrlRouter.getStartedRoute(this);
        Route currentRoute = UrlRouter.getCurrentRoute(this);
        if (null != startedRoute) {
            LogUtil.e("xsp", "Main:startedRoute:" + startedRoute.toString());
        }

        if (null != currentRoute) {
            LogUtil.e("xsp", "Main:currentRoute:" + currentRoute.toString());
        }

        findViewById(R.id.id_jump_to_main).setOnClickListener(this);
        findViewById(R.id.id_jump_to_self).setOnClickListener(this);
        findViewById(R.id.id_jump_to_business_one).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_jump_to_main) {
            // 模块间通信用 Router
            UrlRouter.from(Business02Activity.this).requestCode(8).jumpToMain("activity://main/main");
        } else if (view.getId() == R.id.id_jump_to_self) {
            // 模块内通信 可以launch，也可以直接startActivity
            Intent intent = new Intent(this, Business02SubActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.id_jump_to_business_one) {
            // 模块间通信用 Router
            UrlRouter.from(Business02Activity.this).jump("activity://business01/business01");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("xsp", "requestCode :" + requestCode + " ; resultCode = " + resultCode);
    }
}
