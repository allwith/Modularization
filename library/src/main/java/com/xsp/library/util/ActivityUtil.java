package com.xsp.library.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ActivityUtil {
    private static final int DEFAULT_REQUEST_CODE = 100;

    public static boolean startActivityForResult(Activity activity, Intent intent, int requestCode) {
        boolean bResult = true;
        try {
            activity.startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            bResult = false;
        }
        return bResult;
    }

    public static boolean startActivity(Activity activity, Intent intent) {
        return startActivityForResult(activity, intent, DEFAULT_REQUEST_CODE);
    }

    public static void startActivity(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            // TODO 启动失败，可以日志上报，记录
        }
    }
}
