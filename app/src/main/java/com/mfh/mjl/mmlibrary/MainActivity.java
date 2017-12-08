package com.mfh.mjl.mmlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.martin.alllibrary.base.BaseActivity;
import com.mfh.mjl.mmlibrary.uis.activity.MediaActivity;
import com.mfh.mjl.mmlibrary.uis.activity.NetApplyActivity;
import com.mfh.mjl.mmlibrary.uis.activity.ScanToolActivity;
import com.mfh.mjl.mmlibrary.uis.activity.TestActivity;
import com.mfh.mjl.mmlibrary.uis.activity.TimeSelectActivity;
import com.mfh.mjl.mmlibrary.uis.activity.ViewPagerCircleActivity;
import com.mfh.mjl.mmlibrary.uis.kotlins.activity.FirstKotlinActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @BindView(R.id.img_top_back)
    ImageView imgTopBack;
    @BindView(R.id.txt_top_title)
    TextView txtTopTitle;
    @BindView(R.id.txt_top_right)
    TextView txtTopRight;
    @BindView(R.id.btn_vp_util)
    Button btnVpUtil;
    @BindView(R.id.btn_time_select)
    Button btnTimeSelect;
    @BindView(R.id.btn_media)
    Button btnMedia;
    @BindView(R.id.btn_net)
    Button btnNet;
    @BindView(R.id.btn_kotlin)
    Button btnKotlin;
    @BindView(R.id.btn_scan)
    Button btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        txtTopTitle.setText("工具库演示");
        txtTopRight.setText("测试");
        imgTopBack.setVisibility(View.GONE);
    }


    @OnClick({R.id.btn_vp_util, R.id.btn_time_select, R.id.btn_media, R.id.btn_net
            , R.id.txt_top_right, R.id.btn_kotlin, R.id.btn_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_vp_util:
                ViewPagerCircleActivity.start(getContext());
                break;
            case R.id.btn_time_select:
                TimeSelectActivity.start(getContext());
                break;
            case R.id.btn_media:
                MediaActivity.start(getContext());
                break;
            case R.id.btn_net:
                NetApplyActivity.start(getContext());
                break;
            case R.id.txt_top_right:
                TestActivity.start(getContext());
                break;
            case R.id.btn_kotlin:
                FirstKotlinActivity.Companion.start(getContext());
                break;
            case R.id.btn_scan:
                ScanToolActivity.start(getContext());
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //实现Home键效果
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
