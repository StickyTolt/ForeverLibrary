package com.martin.alllibrary.base.mvc;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.martin.alllibrary.util.showUtil.KeyboardUtils;
import com.martin.alllibrary.util.showUtil.ProgressDialogUtils;
import com.martin.alllibrary.util.showUtil.ToastUtils;
import com.martin.alllibrary.util.showUtil.WindowUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public abstract class BaseActivity extends RxAppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.addActivity(this);
        Log.e(TAG, "onCreate: " + getClass().getSimpleName());
    }

    public Context getContext() {
        return this;
    }

    public BaseActivity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        BaseApplication.deleteActivity(this.getClass().getSimpleName());
        ProgressDialogUtils.getInstance().hideProgress();
    }

    protected void showKeyboard(boolean toShow) {
        KeyboardUtils.showKeyboard(getActivity(), toShow);
    }

    /**
     * 延时弹出键盘
     */
    protected void showKeyboardDelayed(View focus) {
        KeyboardUtils.showKeyboardDelayed(getActivity(), focus);
    }

    public boolean isDestroyedCompatible() {
        return super.isDestroyed();
    }

    public void showProgress() {
        showProgress(null, true);
    }

    public void showProgress(boolean cancelAble) {
        showProgress(null, cancelAble);
    }

    public void showProgress(String message) {
        showProgress(message, true);
    }

    public void showProgress(String message, boolean cancelAble) {
        ProgressDialogUtils.getInstance().showProgress(getActivity(), message, cancelAble);
    }

    public void hideProgress() {
        ProgressDialogUtils.getInstance().hideProgress();
    }

    public void showToast(String message) {
        showToast(message, true);
    }

    public void showToast(String message, boolean isShort) {
        if (isShort) {
            ToastUtils.showCustomToast(getContext(), message);
        } else {
            ToastUtils.showLongCustomToast(getContext(), message);
        }
    }

    public void showToast(int res) {
        showToast(getResources().getString(res));
    }

    /**
     * 设置导航栏颜色
     */
    public void setWindowStatusBarColor(int colorResId) {
        WindowUtils.setWindowStatusBarColor(colorResId, getActivity());
    }

    /**
     * 设置 全屏
     */
    public void setAllScreen() {
        WindowUtils.setAllScreen(getActivity());
    }

    public String noNull(String s) {
        return s == null ? "" : s;
    }

    public String getText(EditText editText) {
        if (editText != null) {
            return editText.getText().toString().trim();
        } else return "";
    }

    public String getText(TextView textView) {
        if (textView != null) {
            return textView.getText().toString().trim();
        } else return "";
    }

    protected <T extends View> T findView(int resId) {
        return (T) (findViewById(resId));
    }

}
