package com.youhuikeji.martin.alllibrary.base;

import android.app.Application;
import android.util.Log;

import com.youhuikeji.martin.alllibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 作者：Martin on 2017/7/8 13:39
 * 邮箱：martin0207mfh@163.com
 */
public class BaseApplication extends Application {

    private static final String TAG = BaseApplication.class.getSimpleName();

    private static Map<String, BaseActivity> activityMap = new HashMap<>();


    //-----------------------------做程序的安全退出 start --------------------------------------------
    public static void addActivity(BaseActivity activity) {
        String simpleName = activity.getClass().getSimpleName();
        if (!activityMap.containsKey(simpleName)) {
            activityMap.put(simpleName, activity);
        }
    }

    public static void deleteActivity(String activityName) {
        if (activityMap.containsKey(activityName)) {
            activityMap.remove(activityName);
        }
    }

    /**
     * 指定 关闭单个Activity
     */
    public static void closeActivity(String tagActivity) {
        if (activityMap.containsKey(tagActivity)) {
            activityMap.get(tagActivity).finish();
            activityMap.remove(tagActivity);
        }
    }

    /**
     * 关闭所有Activity
     * 安全退出
     */
    public static void closeAllActivity() {
        Iterator<String> iterator = activityMap.keySet().iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            BaseActivity activity = activityMap.get(next);
            if (activity != null) {
                activity.finish();
            }
        }
        activityMap.clear();
    }
    //----------------------------------程序的安全退出  end --------------------------------------------

}