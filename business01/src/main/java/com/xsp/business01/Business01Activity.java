package com.xsp.business01;

import android.os.Bundle;
import android.view.View;

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

    }
}
