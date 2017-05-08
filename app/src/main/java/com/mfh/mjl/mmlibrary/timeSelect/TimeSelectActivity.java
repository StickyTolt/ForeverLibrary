package com.mfh.mjl.mmlibrary.timeSelect;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mfh.mjl.mmlibrary.R;
import com.youhuikeji.martin.alllibrary.base.UI;
import com.youhuikeji.martin.alllibrary.widget.timeSelect.CustomDatePicker;
import com.youhuikeji.martin.alllibrary.widget.timeSelect.TimeSelectUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeSelectActivity extends UI implements View.OnClickListener {

    private TextView currentDate, currentTime;
    private CustomDatePicker customDatePicker1, customDatePicker2;

    public static void start(Context context) {
        context.startActivity(new Intent(context, TimeSelectActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_select);

        bindView();

        initDatePicker();
    }

    private void bindView() {
        findView(R.id.selectTime).setOnClickListener(this);
        findView(R.id.selectDate).setOnClickListener(this);
        currentDate = findView(R.id.currentDate);
        currentTime = findView(R.id.currentTime);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectDate:
                // 日期格式为yyyy-MM-dd
                customDatePicker1.show(currentDate.getText().toString());
                break;
            case R.id.selectTime:
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePicker2.show(currentTime.getText().toString());
                break;
        }
    }

    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        currentDate.setText(now.split(" ")[0]);
        currentTime.setText(now);

        customDatePicker1 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                currentDate.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker1.showSpecificTime(false); // 不显示时和分
        customDatePicker1.setIsLoop(false); // 不允许循环滚动

        customDatePicker2 = TimeSelectUtil.getInstance().getPicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                currentTime.setText(time);
            }
        }, "2010-01-01 00:00", now, true, true);
    }

}
