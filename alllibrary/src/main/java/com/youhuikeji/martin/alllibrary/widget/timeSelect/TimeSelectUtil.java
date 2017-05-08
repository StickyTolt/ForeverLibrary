package com.youhuikeji.martin.alllibrary.widget.timeSelect;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 作者：dell on 2017/5/8 09:12
 * <p>
 * 邮箱：martin0207mfh@163.com
 */
public class TimeSelectUtil {

    private static TimeSelectUtil assist;

    private TimeSelectUtil() {
    }

    public static TimeSelectUtil getInstance() {
        if (assist == null) {
            assist = new TimeSelectUtil();
        }
        return assist;
    }

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
    public CustomDatePicker getPicker(Context context, CustomDatePicker.ResultHandler handler, String startData, String endData, boolean showSpecificTime, boolean isLoop) {
        CustomDatePicker datePicker = new CustomDatePicker(context, handler, startData, endData);
        datePicker.setIsLoop(isLoop);
        datePicker.showSpecificTime(showSpecificTime);
        return datePicker;
    }

    /**
     * 快速创建
     * 默认从  1920年  到  当前
     * 带有时分，默认循环
     * 可以获取picker后设置取消
     */
    public CustomDatePicker getPicker(Context context, CustomDatePicker.ResultHandler handler) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        CustomDatePicker picker = new CustomDatePicker(context, handler, "1920-01-01 00:00", now);
        return picker;
    }

}
