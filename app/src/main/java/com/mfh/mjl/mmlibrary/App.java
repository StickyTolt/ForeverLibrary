package com.mfh.mjl.mmlibrary;

import android.content.Context;

import com.martin.alllibrary.ToolCache;
import com.martin.alllibrary.base.mvc.BaseApplication;

/**
 * Martin on 2017/5/4.
 */

public class App extends BaseApplication {

    private static App app;

    public static Context getAppContext() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        ToolCache.init(this);
    }
}
