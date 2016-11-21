package com.xsp.library.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;

import java.util.List;

class UrlRouterUtil {

    static void setupReferrer(Context context, Intent intent) {
        if (null != context && context instanceof Activity) {
            Route currentRoute = parseCurrentRoute(context);
            intent.putExtra(UrlRouter.URL_ROUTER_REFERRER, currentRoute);
        }
    }

    static Route parseStartedRoute(Context context) {
        if (null != context && context instanceof Activity) {
            Intent startedIntent = ((Activity) context).getIntent();
            if (startedIntent.hasExtra(UrlRouter.URL_ROUTER_REFERRER)) {
                return startedIntent.getParcelableExtra(UrlRouter.URL_ROUTER_REFERRER);
            }
        }
        return null;
    }

    static Route parseCurrentRoute(Context context) {
        if (null != context && context instanceof Activity) {
            Intent startedIntent = ((Activity) context).getIntent();
            Uri uri = startedIntent.getData();
            if (null == uri) {
                return null;
            }

            Route route = Route.newInstance();
            route.scheme = getScheme(uri);
            route.host = getHost(uri);
            route.path = getPath(uri);
            route.bundle = startedIntent.getExtras();
            ResolveInfo resolveInfo = queryActivity(context, startedIntent);
            if (null == resolveInfo) {
                return route;
            }

            route.packageName = resolveInfo.activityInfo.packageName;
            route.activityName = resolveInfo.activityInfo.name;
            return route;
        }
        return null;
    }

    static ResolveInfo queryActivity(Context context, Intent intent) {
        if (null == context || null == intent) {
            return null;
        }

        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        // 返回满足action和category 的所有ResolveInfo对象(本质上是Activity)，集合对象
        List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        if (null == resolveInfoList || resolveInfoList.size() == 0) {
            return null;
        }

        int size = resolveInfoList.size();
        if (size == 1) {
            return resolveInfoList.get(0);
        }
        String appPackageName = context.getApplicationContext().getPackageName();
        for (ResolveInfo resolveInfo : resolveInfoList) {
            String activityName = resolveInfo.activityInfo.name;
            if (TextUtils.isEmpty(activityName)) {
                continue;
            }
            if (activityName.startsWith(appPackageName)) {
                return resolveInfo;
            }
        }
        return resolveInfoList.get(0);
    }

    private static String getScheme(Uri uri) {
        if (null == uri) {
            return null;
        }
        return uri.getScheme();
    }

    private static String getHost(Uri uri) {
        if (null == uri) {
            return null;
        }
        return uri.getHost();
    }

    private static String getPath(Uri uri) {
        if (null == uri) {
            return null;
        }
        String path = uri.getPath();
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        path = path.replace("/", "");
        return path;
    }
}
