package com.martin.alllibrary.view.timeSelect;

import android.content.Context;

/**
 * 作者：dell on 2017/5/8 09:12
 * <p>
 * 邮箱：martin0207mfh@163.com
 */
public class TimeSelectUtil {

    private static final String TAG = TimeSelectUtil.class.getSimpleName();

    /**
     * 事件选择器
     * 初始化工具
     *
     * @param context          上下文对象
     * @param handler          接口回调对象
     * @param startData        开始时间
     * @param endData          结束时间
     * @param showSpecificTime 是否显示分秒
     * @param isLoop           是否循环
     * @return 时间选择器对象
     */
    public static CustomDatePicker getPicker(Context context, CustomDatePicker.ResultHandler handler, String startData, String endData, boolean showSpecificTime, boolean isLoop) {
        CustomDatePicker datePicker = new CustomDatePicker(context, handler, startData, endData);
        datePicker.setIsLoop(isLoop);
        datePicker.showSpecificTime(showSpecificTime);
        return datePicker;
    }

    /**
     * 快速创建
     * 默认从  1994年  到  当前
     * 带有时分，默认循环
     * 可以获取picker后设置取消
     */
    public static CustomDatePicker getPicker(Context context, CustomDatePicker.ResultHandler handler) {
        CustomDatePicker picker = new CustomDatePicker(context, handler, "1994-01-01 00:00", "2100-01-01 00:00");
        return picker;
    }

}
