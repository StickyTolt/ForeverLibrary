package com.youhuikeji.martin.alllibrary.util.netUtil.interfaces;

import android.util.Log;
import android.widget.Toast;

import com.youhuikeji.martin.alllibrary.LibCache;

/**
 * 作者：Martin on 2017/6/30 15:35
 * 邮箱：martin0207mfh@163.com
 */
public abstract class NetCallBack {

    private static final String TAG = NetCallBack.class.getSimpleName();

    public void onSuccess(String res) {
        Log.e(TAG, "onSuccess:  >>>   " + res);
    }

    public void onError(int errCode) {
        Log.e(TAG, "onError: >>>    " + errCode);
        Toast.makeText(LibCache.getContext(), "网络访问失败，请重试", Toast.LENGTH_SHORT).show();
    }

}
