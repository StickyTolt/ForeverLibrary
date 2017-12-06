package com.mfh.mjl.mmlibrary;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.martin.alllibrary.ToolCache;
import com.martin.alllibrary.base.BaseApplication;

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

        /*
           解决 7.0 uri 暴露异常
         */
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }
}
