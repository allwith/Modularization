package com.xsp.business01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xsp.business01.R;
import com.xsp.library.router.UrlRouter;
import com.xsp.proxy.activity.BaseActivity;

public class Business01Activity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bussiness01_activity);

        findViewById(R.id.id_jump_to_main).setOnClickListener(this);
        findViewById(R.id.id_jump_to_self).setOnClickListener(this);
        findViewById(R.id.id_jump_to_business_two).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_jump_to_main) {
            UrlRouter.from(Business01Activity.this).requestCode(8).jump("activity://main/main");
        } else if (view.getId() == R.id.id_jump_to_main) {
            UrlRouter.from(Business01Activity.this).jump("activity://native/main");
        } else if (view.getId() == R.id.id_jump_to_business_two) {
            UrlRouter.from(Business01Activity.this).jump("activity://business02/business02");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("xsp", "requestCode :" + requestCode + " ; resultCode = " + resultCode);
    }
}
