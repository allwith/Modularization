package com.xsp.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xsp.business02.activity.Business02Activity;
import com.xsp.library.router.UrlRouter;
import com.xsp.main.R;
import com.xsp.proxy.activity.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                UrlRouter.from(this).jump("activity://business02/business02");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("xsp", "requestCode :" + requestCode + " ; resultCode = " + resultCode);
    }
}
