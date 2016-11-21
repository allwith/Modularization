package com.xsp.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xsp.library.router.Route;
import com.xsp.library.router.UrlRouter;
import com.xsp.library.util.LogUtil;
import com.xsp.library.util.ParamsKey;
import com.xsp.main.R;
import com.xsp.proxy.activity.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Route startedRoute = UrlRouter.getStartedRoute(this);
        Route currentRoute = UrlRouter.getCurrentRoute(this);
        if (null != startedRoute) {
            LogUtil.e("xsp", "Main:startedRoute:" + startedRoute.toString());
        }

        if (null != currentRoute) {
            LogUtil.e("xsp", "Main:currentRoute:" + currentRoute.toString());
        }

        findViewById(R.id.id_jump_to_business_one).setOnClickListener(this);
        findViewById(R.id.id_jump_to_business_two).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_jump_to_business_one:
                UrlRouter.from(this).requestCode(10).jump("activity://business01/business01");
                break;
            case R.id.id_jump_to_business_two:
                Bundle bundle = new Bundle();
                bundle.putString(ParamsKey.MAIN_JUMP_TO_BUSINESS01_PARAMS_ONE, "test01");
                bundle.putInt(ParamsKey.MAIN_JUMP_TO_BUSINESS01_PARAMS_TWO, 50);
                UrlRouter.from(this).params(bundle).jump("activity://business02/business02");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.d("xsp", "requestCode :" + requestCode + " ; resultCode = " + resultCode);
    }
}
