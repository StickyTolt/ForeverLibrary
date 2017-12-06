package com.mfh.mjl.mmlibrary.total;

import android.os.Bundle;
import android.widget.Button;

import com.mfh.mjl.mmlibrary.R;
import com.martin.alllibrary.base.BaseActivity;

public class TotalActivity extends BaseActivity {

    private static final String TAG = TotalActivity.class.getSimpleName();

    private Button btnJumpVp;
    private Button btnTimeSelect;
    private Button btnVideo;
    private Button btnPhoto;
    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        bindView();
        initView();
    }


    protected void bindView() {
        btnJumpVp = findView(R.id.btn_jump_vp);
        btnTimeSelect = findView(R.id.btn_time_select);
        btnVideo = findView(R.id.btn_video);
        btnPhoto = findView(R.id.btn_photo);
        btnTest = findView(R.id.btn_test);
    }

    protected void initView() {
        TotalUtil assist = TotalUtil.getInstance(this);

        assist.setOnClick(btnJumpVp);
        assist.setOnClick(btnTimeSelect);
        assist.setOnClick(btnVideo);
        assist.setOnClick(btnPhoto);
        assist.setOnClick(btnTest);
    }

}
