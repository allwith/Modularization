package com.xsp.main.activity;

import android.content.Intent;
import android.os.Bundle;
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
                UrlRouter.from(this).jump("activity://native/library");
                break;
            case R.id.id_jump_to_business_two:
                startActivity(new Intent(MainActivity.this, Business02Activity.class));
                break;
            default:
                break;
        }
    }

}
