package com.xsp.business01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xsp.business01.R;
import com.xsp.business01.util.RequestCodeUtil;
import com.xsp.library.router.Route;
import com.xsp.library.router.UrlRouter;
import com.xsp.library.util.LogUtil;
import com.xsp.library.util.ParamsKey;
import com.xsp.proxy.activity.BaseActivity;

public class Business01Activity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bussiness01_activity);

        Route startedRoute = UrlRouter.getStartedRoute(this);
        Route currentRoute = UrlRouter.getCurrentRoute(this);

        LogUtil.e("xsp", "Business01:startedRoute:"
                + (null == startedRoute ? "null == startedRoute" : startedRoute.toString()));
        LogUtil.e("xsp", "Business01:currentRoute:"
                + (null == currentRoute ? "null == currentRoute" : currentRoute.toString()));

        if (null != currentRoute && null != currentRoute.bundle) {
            Bundle bundle = currentRoute.bundle;
            String para01 = bundle.getString(ParamsKey.MAIN_JUMP_TO_BUSINESS01_PARAMS_ONE);
            int para02 = bundle.getInt(ParamsKey.MAIN_JUMP_TO_BUSINESS01_PARAMS_TWO);
            LogUtil.e("xsp", para01 + " ; " + para02);
        }

        findViewById(R.id.id_jump_to_main).setOnClickListener(this);
        findViewById(R.id.id_jump_to_self).setOnClickListener(this);
        findViewById(R.id.id_jump_to_business_two).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_jump_to_main) {
            // 模块间通信用 Router
            UrlRouter.from(Business01Activity.this).requestCode(8).jumpToMain("activity://main/main");
        } else if (view.getId() == R.id.id_jump_to_self) {
            // 模块内通信 可以launch，也可以直接startActivity
            Business01SubActivity.launch(Business01Activity.this);
        } else if (view.getId() == R.id.id_jump_to_business_two) {
            // 模块间通信用 Router
            UrlRouter.from(Business01Activity.this).jump("activity://business02/business02");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RequestCodeUtil.BUSINESS_CODE:
                LogUtil.d("sam", "Get back requestCode ： " + RequestCodeUtil.BUSINESS_CODE);
                break;
        }
    }
}
