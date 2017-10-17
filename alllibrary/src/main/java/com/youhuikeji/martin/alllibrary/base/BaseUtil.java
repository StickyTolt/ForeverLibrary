package com.youhuikeji.martin.alllibrary.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * 作者：Martin on 2017/8/10 08:52
 * 邮箱：martin0207mfh@163.com
 */
public class BaseUtil {

    private static final String TAG = BaseUtil.class.getSimpleName();

    protected static ProgressDialog progressDialog;
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    Toast toast;

    /**
     * 用于实现Toast功能，防止循环加入系统队列，一直等待显示完成
     */
    public void showToast(Context context, String message) {
        showToast(context, message, true);
    }

    public void showToast(Context context, String message, boolean isShort) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
        }
        toast.setText(message);

        if (isShort) {
            toast.setDuration(Toast.LENGTH_SHORT);
        } else {
            toast.setDuration(Toast.LENGTH_LONG);
        }

        if (Looper.myLooper() == Looper.getMainLooper()) {
            toast.show();
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    toast.show();
                }
            });
        }
    }

    public void showToast(Context context, int res) {
        showToast(context, context.getResources().getString(res));
    }


    public void showProgress(Context context) {
        showProgress(null, context);
    }

    public void showProgress(String message, Context context) {
        try {
            if (progressDialog != null) {
                if (progressDialog.isShowing()) {
                    return;
                }
                progressDialog.setMessage(message != null ? message
                        : "加载中...");
                progressDialog.show();
            } else {
                progressDialog = new ProgressDialog(context.getApplicationContext());
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setIndeterminate(false);
                progressDialog.setCancelable(true);
                progressDialog.setMessage(message != null ? message
                        : "加载中...");
                try {
                    progressDialog.show();
                } catch (Exception e) {
                    Log.e(TAG, "showProgress:  这里 Dialog 展示失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
