package com.mfh.mjl.mmlibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.martin.alllibrary.base.BaseActivity;
import com.martin.alllibrary.util.systemUtil.TimeUtil;
import com.martin.alllibrary.view.timeSelect.CustomDatePicker;
import com.martin.alllibrary.view.timeSelect.TimeSelectUtil;
import com.mfh.mjl.mmlibrary.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimeSelectActivity extends BaseActivity {

    @BindView(R.id.img_top_back)
    ImageView imgTopBack;
    @BindView(R.id.txt_top_title)
    TextView txtTopTitle;
    @BindView(R.id.txt_top_right)
    TextView txtTopRight;
    @BindView(R.id.btn_select_ymd)
    Button btnSelectYmd;
    @BindView(R.id.et_ymd)
    EditText etYmd;
    @BindView(R.id.btn_select_ymd_hms)
    Button btnSelectYmdHms;
    @BindView(R.id.et_ymd_hms)
    EditText etYmdHms;

    //===========================================================
    private CustomDatePicker pickerYMD;
    private CustomDatePicker pickerYMDHMS;

    public static void start(Context context) {
        Intent intent = new Intent(context, TimeSelectActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_select);
        ButterKnife.bind(this);

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        txtTopTitle.setText("选择时间");

        etYmd.setText(TimeUtil.getYMD(System.currentTimeMillis()));
        etYmdHms.setText(TimeUtil.getAllTime(System.currentTimeMillis()));

        pickerYMD = TimeSelectUtil.getPicker(getContext(), new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                etYmd.setText(time);
            }
        });
        pickerYMD.setIsLoop(true);
        pickerYMD.showSpecificTime(false);

        pickerYMDHMS = TimeSelectUtil.getPicker(getContext(), new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                etYmdHms.setText(time);
            }
        });
        pickerYMDHMS.showSpecificTime(true);
    }

    @OnClick({R.id.img_top_back, R.id.btn_select_ymd, R.id.btn_select_ymd_hms})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_top_back:
                finish();
                break;
            case R.id.btn_select_ymd:
                pickerYMD.show(getText(etYmd));
                break;
            case R.id.btn_select_ymd_hms:
                pickerYMDHMS.show(getText(etYmdHms));
                break;
        }
    }
}
