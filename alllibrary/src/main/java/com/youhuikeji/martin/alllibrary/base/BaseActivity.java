package com.youhuikeji.martin.alllibrary.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.youhuikeji.martin.alllibrary.R;
import com.youhuikeji.martin.alllibrary.base.util.ReflectionUtil;
import com.youhuikeji.martin.alllibrary.special.ExtraName;
import com.youhuikeji.martin.alllibrary.util.systemUtil.SystemBarTintManager;

public abstract class BaseActivity extends AppCompatActivity {


    private static final String TAG = BaseActivity.class.getSimpleName();

    private boolean destroyed = false;

    private static Handler handler = new Handler(Looper.getMainLooper());

    protected ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.addActivity(this);
        Log.e(TAG, "onCreate: 打开的Activity 的名字是   ==  " + getClass().getSimpleName());

    }


    public Context getContext() {
        return this;
    }

    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String name = this.getClass().getSimpleName();
        Log.e(TAG, "onDestroy:  这个是BaseActivity 中的 删除操作    " + name);
        BaseApplication.deleteActivity(name);
        destroyed = true;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected Handler getHandler() {
        if (handler == null) {
            handler = new Handler(getMainLooper());
        }
        return handler;
    }

    protected void showKeyboard(boolean isShow) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isShow) {
            if (getCurrentFocus() == null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            } else {
                imm.showSoftInput(getCurrentFocus(), 0);
            }
        } else {
            if (getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 延时弹出键盘
     *
     * @param focus 键盘的焦点项
     */
    protected void showKeyboardDelayed(View focus) {
        final View viewToFocus = focus;
        if (focus != null) {
            focus.requestFocus();
        }

        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewToFocus == null || viewToFocus.isFocused()) {
                    showKeyboard(true);
                }
            }
        }, 200);
    }


    public boolean isDestroyedCompatible() {
        if (Build.VERSION.SDK_INT >= 17) {
            return isDestroyedCompatible17();
        } else {
            return destroyed || super.isFinishing();
        }
    }

    @TargetApi(17)
    private boolean isDestroyedCompatible17() {
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
        try {
            if (progressDialog != null) {
                if (progressDialog.isShowing()) {
                    return;
                }
                progressDialog.setMessage(message != null ? message
                        : "请稍候...");
                progressDialog.show();
            } else {
                progressDialog = new ProgressDialog(BaseActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setIndeterminate(false);
                progressDialog.setCancelable(cancelAble);
                progressDialog.setMessage(message != null ? message
                        : "请稍候...");

                if (!isFinishing() && !isDestroyedCompatible()) {
                    progressDialog.show();
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

    Toast toast;

    /**
     * 用于实现Toast功能，防止循环加入系统队列，一直等待显示完成
     *
     * @param message
     */
    public void showToast(String message) {
        showToast(message, true);
    }

    public void showToast(String message, boolean isShort) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
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
            handler.post(new Runnable() {
                @Override
                public void run() {
                    toast.show();
                }
            });
        }
    }

    public void showToast(int res) {
        showToast(getResources().getString(res));
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void invokeFragmentManagerNoteStateNotSaved() {
        ReflectionUtil.invokeMethod(getFragmentManager(), "noteStateNotSaved", null);
    }


    protected boolean isCompatible(int apiLevel) {
        return Build.VERSION.SDK_INT >= apiLevel;
    }

    protected <T extends View> T findView(int resId) {
        return (T) (findViewById(resId));
    }


    public void showWaitDialog(String title, String msg) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(R.string.ok, null)
                .setNegativeButton(R.string.cancel, null)
                .create();
        if (!isFinishing() && !isDestroyedCompatible()) {
            dialog.show();
        }
    }

    public void setWindowStatusBarColor(int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                SystemBarTintManager tintManager = new SystemBarTintManager(this);
                // 激活状态栏设置
                tintManager.setStatusBarTintEnabled(true);
                // 使用颜色资源
                tintManager.setStatusBarTintResource(colorResId);

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(colorResId));

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String noNull(String s) {
        return s == null ? "" : s;
    }

    /**
     * 在 setContentView() 之前调用
     */
    public void setAllScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //无title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  //全屏
    }


    public Bundle getBundleInt(int extra) {
        Bundle bundle = new Bundle();
        bundle.putInt(ExtraName.EXTRA_DATA, extra);
        return bundle;
    }

    public String getText(EditText editText) {
        return editText.getText().toString().trim();
    }

    public String getText(TextView textView) {
        return textView.getText().toString().trim();
    }

}
