package com.martin.alllibrary.util.showUtil;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * 作者：Martin on 2017/12/5 14:27
 * 邮箱：martin0207mfh@163.com
 */
public class ProgressDialogUtils {

    private ProgressDialog progressDialog;

    private static ProgressDialogUtils instance = new ProgressDialogUtils();

    private ProgressDialogUtils() {
       
    }

    public static ProgressDialogUtils getInstance() {
        return instance;
    }

    public void showProgress(Activity activity) {
        showProgress(activity, null, true);
    }

    public void showProgress(Activity activity, boolean cancelAble) {
        showProgress(activity, null, cancelAble);
    }

    public void showProgress(Activity activity, String message) {
        showProgress(activity, message, true);
    }

    /**
     * 展示 加载框
     */
    public void showProgress(Activity activity, String message, boolean cancelAble) {
        try {
            if (progressDialog != null) {
                if (progressDialog.isShowing()) {
                    return;
                }
                progressDialog.setMessage(message != null ? message
                        : "请稍候...");
                progressDialog.show();
            } else {
                progressDialog = new ProgressDialog(activity);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setIndeterminate(false);
                progressDialog.setCancelable(cancelAble);
                progressDialog.setMessage(message != null ? message
                        : "请稍候...");

                if (!activity.isFinishing() && !activity.isDestroyed()) {
                    progressDialog.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏 加载框
     */
    public void hideProgress() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                try {
                    if (!progressDialog.getContext().isRestricted()) {
                        progressDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
