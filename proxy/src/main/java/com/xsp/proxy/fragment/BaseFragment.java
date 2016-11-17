package com.xsp.proxy.fragment;

import android.support.v4.app.Fragment;

import com.xsp.library.util.LogUtil;

public class BaseFragment extends Fragment {


    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d("xsp", "onResume() :" + this.getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.d("xsp", "onPause() :" + this.getClass().getSimpleName());
    }

}
