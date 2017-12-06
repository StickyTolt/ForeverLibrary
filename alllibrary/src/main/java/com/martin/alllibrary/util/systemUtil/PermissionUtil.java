package com.martin.alllibrary.util.systemUtil;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * 作者：Martin on 2017/7/3 15:43
 * 邮箱：martin0207mfh@163.com
 */
public class PermissionUtil {

    private static final String TAG = PermissionUtil.class.getSimpleName();

    /**
     * 验证是否有权限
     */
    public static boolean havePermission(Context context, String permission) {
        int checkSelfPermission = ContextCompat.checkSelfPermission(context, permission);
        boolean b = checkSelfPermission == PackageManager.PERMISSION_GRANTED;
        Log.e(TAG, "havePermission: 判定 权限是否同意了  " + checkSelfPermission + "  -----   " + b);
        return b;
    }

    /**
     * 请求权限
     */
    public static void applyPermission(Activity activity, String permission, int requestCode) {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
    }

    /**
     * 直接获取权限
     * 需要重写 onRequestPermissionsResult 方法
     */
    public static void getPermission(Activity activity, String permission, int requestCode, OnGetPermissionListener listener) {
        if (havePermission(activity, permission)) {
            if (listener != null) {
                listener.onSuccess();
            }
        } else {
            applyPermission(activity, permission, requestCode);
        }
    }


    public interface OnGetPermissionListener {
        void onSuccess();
    }
}
