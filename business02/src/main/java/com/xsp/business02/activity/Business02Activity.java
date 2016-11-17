package com.xsp.business02.activity;

import android.os.Bundle;
import android.view.View;

import com.xsp.business02.R;
import com.xsp.library.router.UrlRouter;
import com.xsp.proxy.activity.BaseActivity;

public class Business02Activity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bussiness02_activity);

        findViewById(R.id.id_jump_to_main).setOnClickListener(this);
        findViewById(R.id.id_jump_to_self).setOnClickListener(this);
        findViewById(R.id.id_jump_to_business_one).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_jump_to_main) {
            UrlRouter.from(Business02Activity.this).jump("activity://activity/main");
        } else if (view.getId() == R.id.id_jump_to_main) {
            UrlRouter.from(Business02Activity.this).jump("activity://native/main");
        } else if (view.getId() == R.id.id_jump_to_business_one) {
            UrlRouter.from(Business02Activity.this).jump("activity://main");
        }
    }
}
