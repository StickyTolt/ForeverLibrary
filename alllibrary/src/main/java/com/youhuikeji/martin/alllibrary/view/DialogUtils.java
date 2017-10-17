package com.youhuikeji.martin.alllibrary.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.youhuikeji.martin.alllibrary.util.netUtil.interfaces.OnDialogClickListener;

/**
 * 作者：Martin on 2017/8/4 17:26
 * 邮箱：martin0207mfh@163.com
 */
public class DialogUtils {

    public static void showEasyDialog(Activity activity, String title, String msg, String positive
            , String negative, final OnDialogClickListener listener) {

        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onClickCenter();
                    }
                })
                .setNegativeButton(negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onClickRight();
                    }
                })
                .create();

        if (!activity.isFinishing()) {
            dialog.show();
        }
    }


}
