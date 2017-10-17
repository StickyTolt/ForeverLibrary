package com.youhuikeji.martin.alllibrary.util.netUtil;


import com.youhuikeji.martin.alllibrary.util.netUtil.interfaces.NetCallBack;
import com.youhuikeji.martin.alllibrary.util.netUtil.model.ParamsUtilsBean;

import java.util.List;


/*
 * 作者：Martin on 2017/7/8 14:13
 * 邮箱：martin0207mfh@163.com
 */
public class SSApplyNet {

    private static SSApplyNet ssApplyNet = new SSApplyNet();

    private static final String TAG = SSApplyNet.class.getSimpleName();


    private SSApplyNet() {

    }

    public static SSApplyNet getInstance() {
        return ssApplyNet;
    }

    //===================================网络请求=====================================


    public void applyNet(List<ParamsUtilsBean> data, String url, final NetCallBack callback) {

     /*   String applyUrl = url;

        PostRequest post = OkHttpUtils.post(url);

        post.url(url);

        int size = data.size();
        for (int i = 0; i < size; i++) {
            post.params(data.get(i).getKey(), data.get(i).getValue());
            if (i == 0) {
                applyUrl = applyUrl + "?" + data.get(i).getKey() + "=" + data.get(i).getValue();
            } else {
                applyUrl = applyUrl + "&" + data.get(i).getKey() + "=" + data.get(i).getValue();
            }
        }
        Log.e(TAG, "applyNet: 访问的 url 》》  " + applyUrl);

        post.execute(new StringCallback() {
            @Override
            public void onResponse(boolean b, String s, Request request, @Nullable Response response) {

                if (response != null && response.code() == 200) {
                    callback.onSuccess(s);
                } else {
                    callback.onError(-99);
                }
            }

            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(isFromCache, call, response, e);
                callback.onError(-98);
            }
        });*/

    }

    //=======================================以上为网络请求工具======================================

}
