package com.martin.alllibrary.util.showUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.martin.alllibrary.ToolCache;

/**
 * 作者：Martin on 2017/12/5 14:24
 * 邮箱：martin0207mfh@163.com
 */
public class ToastUtils {

    private static Toast toast;
    private static Handler mHandler=new Handler(Looper.getMainLooper());

    public static void show(String msg){
        showCustomToast(ToolCache.getContext(),msg);
    }

    public static void show(int msgRes){
        showCustomToast(ToolCache.getContext(),ToolCache.getContext().getResources().getString(msgRes));
    }

    @SuppressLint("ShowToast")
    public static void showCustomToast(Context context, String text) {
        if(toast==null){
            toast=Toast.makeText(context.getApplicationContext(),text,Toast.LENGTH_SHORT);
        }
        toast.setText(text);

        if(Looper.myLooper()==Looper.getMainLooper()){
            toast.show();
        }else{
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    toast.show();
                }
            });
        }
    }

    @SuppressLint("ShowToast")
    public static void showLongCustomToast(Context context, String text) {
        if(toast==null){
            toast=Toast.makeText(context.getApplicationContext(),text,Toast.LENGTH_LONG);
        }
        toast.setText(text);

        if(Looper.myLooper()==Looper.getMainLooper()){
            toast.show();
        }else{
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    toast.show();
                }
            });
        }
    }
    public  static void cancel(){
        if(toast!=null){
            toast.cancel();
        }
    }

}
