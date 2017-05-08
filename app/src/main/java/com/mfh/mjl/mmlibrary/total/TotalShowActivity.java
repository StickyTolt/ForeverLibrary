package com.mfh.mjl.mmlibrary.total;

import android.content.Context;
import android.content.Intent;

/**
 * Martin on 2017/5/4.
 */

public class TotalShowActivity extends TotalActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, TotalShowActivity.class));
    }

}
