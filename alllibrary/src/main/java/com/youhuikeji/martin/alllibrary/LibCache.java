package com.youhuikeji.martin.alllibrary;

import android.content.Context;

/**
 * Created by dell on 2017/5/4.
 */

public class LibCache {

    private static Context context;

    public static void init(Context context) {
        LibCache.context = context;
    }

    public static Context getContext() {
        return context;
    }

}
