package com.xsp.proxy;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.xsp.library.util.LogUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BaseApplication extends Application{
    private static BaseApplication mIns = null;
    private Handler mHandler = null;
    private ArrayList<Activity> mActivityList = new ArrayList<>();

    public static BaseApplication getIns() {
        return mIns;
    }

    public Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mIns = this;
    }

    public void addActivity(Activity act) {
        synchronized (mActivityList) {
            if (!mActivityList.contains(act)) {
                mActivityList.add(act);
            }
        }
    }

    public void removeActivity(Activity act) {
        synchronized (mActivityList) {
            mActivityList.remove(act);
            if (mActivityList.size() == 0) {
                trimMemory();
            }
        }
    }

    public void removeActivitys(HashMap<Class<?>, Integer> map) {
        Iterator<Activity> iterator = mActivityList.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity == null) {
                continue;
            }

            if (map.containsKey(activity.getClass())) {
                activity.finish();
                iterator.remove();
            }
        }
    }

    public ArrayList<Activity> getActivityList() {
        return mActivityList;
    }

    /**
     * 退出时候调用trimMemory释放内存（主要是硬件加速部分）
     */
    public void trimMemory() {
        try {
            Class<?> activityThreadClazz = Class.forName("android.app.ActivityThread");
            Class<?> IApplicationThreadClazz = Class.forName("android.app.IApplicationThread");
            Method currentActivityThreadMethod = activityThreadClazz.getMethod("currentActivityThread");
            Method getApplicationThreadMethod = activityThreadClazz.getMethod("getApplicationThread");
            Method scheduleTrimMemoryMethod = IApplicationThreadClazz.getMethod("scheduleTrimMemory", int.class);

            Object activityThread = currentActivityThreadMethod.invoke(null);
            Object appThread = getApplicationThreadMethod.invoke(activityThread);
            scheduleTrimMemoryMethod.invoke(appThread, TRIM_MEMORY_COMPLETE);
        } catch (Exception t) {
            LogUtil.e("sam", "trimMemory exception");
        }
    }

    public void finishApp(boolean forceExit) {
        synchronized (mActivityList) {
            if (!mActivityList.isEmpty()) {
                for (int i = mActivityList.size() - 1; i >= 0; --i) {
                    Activity act = mActivityList.get(i);
                    if (null != act) {
                        act.finish();
                    }
                }
                mActivityList.clear();
            }
            if (forceExit)
                System.exit(0);
        }
    }

    public void finishApp() {
        finishApp(true);
    }

    public int getActivityNumber() {
        synchronized (mActivityList) {
            return mActivityList.size();
        }
    }

    public String getForegroundActName() {
        synchronized (mActivityList) {
            if (mActivityList.size() == 0)
                return "";
            else {
                Activity act = mActivityList.get(mActivityList.size() - 1);
                return act.getClass().getName();
            }
        }
    }

}
