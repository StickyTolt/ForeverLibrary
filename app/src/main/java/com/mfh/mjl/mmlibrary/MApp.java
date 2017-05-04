package com.mfh.mjl.mmlibrary;

import android.app.Application;

import com.youhuikeji.martin.alllibrary.LibCache;

/**
 * Martin on 2017/5/4.
 */

public class MApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LibCache.init(this);
    }
}
