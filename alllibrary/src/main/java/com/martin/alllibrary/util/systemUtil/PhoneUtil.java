package com.martin.alllibrary.util.systemUtil;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

/**
 * 作者：Martin on 2017/7/3 14:36
 * 邮箱：martin0207mfh@163.com
 */
public class PhoneUtil {

    /**
     * 拨打电话
     */
    public static void callPhone(Activity activity, String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            PermissionUtil.applyPermission(activity, Manifest.permission.CALL_PHONE, 100);
            return;
        }
        activity.startActivity(intent);
    }

    /**
     * 调起系统发短信功能
     */
    public static void doSendSMSTo(String message, Activity activity) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"));
        intent.putExtra("sms_body", message);
        activity.startActivity(intent);
    }

}
