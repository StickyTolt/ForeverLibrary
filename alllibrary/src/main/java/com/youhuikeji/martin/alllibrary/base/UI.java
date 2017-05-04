package com.youhuikeji.martin.alllibrary.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.youhuikeji.martin.alllibrary.R;
import com.youhuikeji.martin.alllibrary.base.uis.ReflectionUtil;
import com.youhuikeji.martin.alllibrary.base.uis.ToolBarOptions;

public abstract class UI extends AppCompatActivity {


    private boolean destroyed = false;

    private static Handler handler;

    private Toolbar toolbar;

    protected ProgressDialog progressDialog;

    @Override
    protected void onStart() {
        super.onStart();
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("activity: ", "onCreate: 打开的Activity 的名字是   ==  " + getClass().getSimpleName());

    }

    @Override
    public void onBackPressed() {
        try {
            invokeFragmentManagerNoteStateNotSaved();
            super.onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        destroyed = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onNavigateUpClicked();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setToolBar(int toolBarId, ToolBarOptions options) {
        toolbar = (Toolbar) findViewById(toolBarId);
        if (null == toolbar) return;
        if (options.titleId != 0) {
            toolbar.setTitle(options.titleId);
        }
        if (!TextUtils.isEmpty(options.titleString)) {
            toolbar.setTitle(options.titleString);
        }
//        if (options.logoId != 0) {
//            toolbar.setLogo(options.logoId);
//        }
        setSupportActionBar(toolbar);

        if (options.isNeedNavigate) {
//            toolbar.setNavigationIcon(options.navigateId);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigateUpClicked();
                }
            });
        }
    }

    public void setToolBar(int toolbarId, int titleId, int logoId) {
        toolbar = (Toolbar) findViewById(toolbarId);
        if (null == toolbar) return;
        /*------------wgd20170209由于想把标题显示在tab中间，所以放弃使用自带的左边的标题，所以这里加==0判断------------------------*/
        if (0 == titleId) {
            toolbar.setTitle("");
        } else {
            toolbar.setTitle(titleId);
        }

//        if (0!=logoId)toolbar.setLogo(logoId);
        setSupportActionBar(toolbar);
    }

    public Toolbar getToolBar() {
        return toolbar;
    }

    public int getToolBarHeight() {
        if (toolbar != null) {
            return toolbar.getHeight();
        }
        return 0;
    }

    public void onNavigateUpClicked() {
        onBackPressed();
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (toolbar != null) {
            toolbar.setTitle(title);
        }
    }

    protected final Handler getHandler() {
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
        showProgress(null);
    }

    public void showProgress(String message) {
        try {
            if (progressDialog != null) {
                if (progressDialog.isShowing()) {
                    return;
                }
                progressDialog.setMessage(message != null ? message
                        : "loading...");
                progressDialog.show();
            } else {
                progressDialog = new ProgressDialog(UI.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setIndeterminate(false);
                progressDialog.setCancelable(true);
                progressDialog.setMessage(message != null ? message
                        : "loading...");
                progressDialog.show();
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
        try {
            if (toast != null) {
                toast.setText(null == message ? "......" : message);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
            } else {
                toast = Toast.makeText(UI.this, null == message ? "......" : message, Toast.LENGTH_SHORT);
                toast.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showToast(int res) {
        showToast(getResources().getString(res));
    }

    public void showLogi(String message) {
        Log.i("-----", message);
    }

    protected boolean displayHomeAsUpEnabled() {
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                return onMenuKeyDown();

            default:
                return super.onKeyDown(keyCode, event);
        }
    }

    protected boolean onMenuKeyDown() {
        return false;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void invokeFragmentManagerNoteStateNotSaved() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ReflectionUtil.invokeMethod(getFragmentManager(), "noteStateNotSaved", null);
        }
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

}
