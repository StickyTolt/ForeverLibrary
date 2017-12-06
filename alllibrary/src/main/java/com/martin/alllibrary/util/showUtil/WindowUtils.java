package com.martin.alllibrary.util.showUtil;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.martin.alllibrary.util.systemUtil.SystemBarTintManager;

/**
 * 作者：Martin on 2017/12/5 14:59
 * 邮箱：martin0207mfh@163.com
 */
public class WindowUtils {

    public static void setWindowStatusBarColor(int colorRes, Activity activity) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                SystemBarTintManager tintManager = new SystemBarTintManager(activity);
                // 激活状态栏设置
                tintManager.setStatusBarTintEnabled(true);
                // 使用颜色资源
                tintManager.setStatusBarTintResource(colorRes);

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorRes));

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置全屏
     */
    public static void setAllScreen(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);  //无title
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  //全屏
    }

}
