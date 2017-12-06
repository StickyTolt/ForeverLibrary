package com.martin.alllibrary.util.showUtil;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 作者：Martin on 2017/12/5 14:47
 * 邮箱：martin0207mfh@163.com
 */
public class KeyboardUtils {

    public static void showKeyboard(Activity activity, boolean isShow) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) throw new NullPointerException("the input Service is null");
        View focus = activity.getCurrentFocus();
        if (isShow) {
            if (focus == null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            } else {
                imm.showSoftInput(focus, 0);
            }
        } else {
            if (focus != null) {
                imm.hideSoftInputFromWindow(focus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 延时弹出键盘
     *
     * @param focus 键盘的焦点项
     */
    public static void showKeyboardDelayed(final Activity activity, View focus) {
        final View viewToFocus = focus;
        if (focus != null) {
            focus.requestFocus();
        }

        HandlerUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewToFocus == null || viewToFocus.isFocused()) {
                    showKeyboard(activity,true);
                }
            }
        }, 200);
    }

}
