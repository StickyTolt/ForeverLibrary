package com.martin.alllibrary.netUtil.basic;

import android.app.Activity;
import android.text.TextUtils;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.martin.alllibrary.R;
import com.martin.alllibrary.util.showUtil.LogUtils;
import com.martin.alllibrary.util.showUtil.ProgressDialogUtils;
import com.martin.alllibrary.util.showUtil.ToastUtils;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.martin.alllibrary.netUtil.basic.DefaultObserver.ExceptionReason.CONNECT_ERROR;
import static com.martin.alllibrary.netUtil.basic.DefaultObserver.ExceptionReason.CONNECT_TIMEOUT;
import static com.martin.alllibrary.netUtil.basic.DefaultObserver.ExceptionReason.PARSE_ERROR;
import static com.martin.alllibrary.netUtil.basic.DefaultObserver.ExceptionReason.UNKNOWN_ERROR;


public abstract class DefaultObserver<T extends BasicResponse> implements Observer<T> {
    private Activity activity;
    //  Activity 是否在执行onStop()时取消订阅
    private boolean isAddInStop = false;
    private ProgressDialogUtils dialogUtils;

    public DefaultObserver(Activity activity) {
        this.activity = activity;
        dialogUtils = ProgressDialogUtils.getInstance();
        dialogUtils.showProgress(activity);
    }

    public DefaultObserver(Activity activity, boolean isShowLoading) {
        this.activity = activity;
        dialogUtils = ProgressDialogUtils.getInstance();
        if (isShowLoading) {
            dialogUtils.showProgress(activity, "Loading...");
        }
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T response) {
        dismissProgress();
        if (response.isSuccess()) {
            onSuccess(response);
        } else {
            onFail(response);
        }
        /*if (response.getCode() == 200) {
            onSuccess(response);
        } else {
            onFail(response);
        }*/
    }

    private void dismissProgress() {
        if (dialogUtils != null) {
            dialogUtils.hideProgress();
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.e("Retrofit", e.getMessage());
        dismissProgress();
        if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(PARSE_ERROR);
        } else {
            onException(UNKNOWN_ERROR);
        }
    }

    @Override
    public void onComplete() {
    }

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    abstract public void onSuccess(T response);

    /**
     * 服务器返回数据，但响应码不为200
     *
     * @param response 服务器返回的数据
     */
    public void onFail(T response) {
        String message = response.getMsg();
        if (TextUtils.isEmpty(message)) {
            ToastUtils.show(R.string.response_return_error);
        } else {
            ToastUtils.show(message);
        }
    }

    /**
     * 请求异常
     */
    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                ToastUtils.show(R.string.connect_error);
                break;

            case CONNECT_TIMEOUT:
                ToastUtils.show(R.string.connect_timeout);
                break;

            case BAD_NETWORK:
                ToastUtils.show(R.string.bad_network);
                break;

            case PARSE_ERROR:
                ToastUtils.show(R.string.parse_error);
                break;
            case UNKNOWN_ERROR:
            default:
                ToastUtils.show(R.string.unknown_error);
                break;
        }
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}
