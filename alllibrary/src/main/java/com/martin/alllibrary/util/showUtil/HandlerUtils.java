package com.martin.alllibrary.util.showUtil;

import android.os.Handler;
import android.os.Looper;

/**
 * 作者：Martin on 2017/12/5 14:53
 * 邮箱：martin0207mfh@163.com
 */
public class HandlerUtils {

    private static Handler handler = new Handler(Looper.getMainLooper());

    public static Handler getHandler() {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        return handler;
    }

}
