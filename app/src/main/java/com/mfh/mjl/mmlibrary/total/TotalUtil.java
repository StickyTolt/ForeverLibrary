package com.mfh.mjl.mmlibrary.total;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.mfh.mjl.mmlibrary.R;
import com.mfh.mjl.mmlibrary.timeSelect.TimeSelectActivity;
import com.mfh.mjl.mmlibrary.viewpager.VPDemoActivity;

/**
 * Martin on 2017/5/4.
 */

public class TotalUtil implements View.OnClickListener {

    private static final String TAG = TotalUtil.class.getSimpleName();
    private static TotalUtil assist;
    private static Context context;

    private TotalUtil() {
    }

    public static TotalUtil getInstance(Context context) {
        TotalUtil.context = context;
        if (assist == null) {
            assist = new TotalUtil();
        }
        return assist;
    }

    public void setOnClick(View view) {
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jump_vp:
                VPDemoActivity.start(context);
                break;
            case R.id.btn_time_select:
                TimeSelectActivity.start(context);
                break;
        }
    }
}
