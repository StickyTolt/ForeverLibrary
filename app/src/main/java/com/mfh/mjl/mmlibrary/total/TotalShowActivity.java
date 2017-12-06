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

    @Override
    public void onBackPressed() {
        //实现Home键效果
        //super.onBackPressed();这句话一定要注掉,不然又去调用默认的back处理方式了
        Intent i= new Intent(Intent.ACTION_MAIN);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);

    }

}
