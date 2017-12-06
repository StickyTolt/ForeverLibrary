package com.martin.alllibrary.util.systemUtil;

import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;

/**
 * 作者：Martin on 2017/7/31 18:57
 * 邮箱：martin0207mfh@163.com
 */
public class ScreenUtil {

    public static int px2dip(int pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static int dip2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 获取屏幕区域
     */
    public static Rect getScreenRect() {
        DisplayMetrics displayMetric = Resources.getSystem().getDisplayMetrics();
        Rect rect = new Rect(0, 0, displayMetric.widthPixels, displayMetric.heightPixels);
        return rect;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth() {
        return getScreenRect().width();
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight() {
        return getScreenRect().height();
    }
}
