package com.martin.alllibrary;

import android.content.Context;


public class ToolCache {

    private static Context context;

    public static void init(Context context) {
        ToolCache.context = context;
    }

    public static Context getContext() {
        if (context != null) {
        return context;
        }
        throw new NullPointerException("you must init ToolCache before do this");
    }

}
