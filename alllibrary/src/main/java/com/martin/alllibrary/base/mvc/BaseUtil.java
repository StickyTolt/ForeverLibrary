package com.martin.alllibrary.base.mvc;

import android.app.Activity;
import android.content.Context;

import com.martin.alllibrary.util.showUtil.ProgressDialogUtils;
import com.martin.alllibrary.util.showUtil.ToastUtils;

/**
 * 作者：Martin on 2017/8/10 08:52
 * 邮箱：martin0207mfh@163.com
 */
public class BaseUtil {

    private static final String TAG = BaseUtil.class.getSimpleName();

    public void showToast(Context context, String message) {
        showToast(context, message, true);
    }

    public void showToast(Context context, String message, boolean isShort) {
        if (isShort) {
            ToastUtils.showCustomToast(context, message);
        } else
            ToastUtils.showLongCustomToast(context, message);
    }

    public void showToast(Context context, int res) {
        showToast(context, context.getResources().getString(res));
    }


    public void showProgress(Activity activity) {
        showProgress(null, activity);
    }

    public void showProgress(String message, Activity activity) {
        ProgressDialogUtils.getInstance().showProgress(activity, message);
    }

    public void hideProgress() {
        ProgressDialogUtils.getInstance().hideProgress();
    }

    public String noNull(String s) {
        return s == null ? "" : s;
    }
}
