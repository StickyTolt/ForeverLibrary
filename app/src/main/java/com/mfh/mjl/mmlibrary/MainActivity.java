package com.mfh.mjl.mmlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.martin.alllibrary.base.BaseActivity;
import com.mfh.mjl.mmlibrary.activity.TimeSelectActivity;
import com.mfh.mjl.mmlibrary.activity.ViewPagerCircleActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        txtTopTitle.setText("工具库演示");
        imgTopBack.setVisibility(View.GONE);

    }


    @OnClick({R.id.btn_vp_util, R.id.btn_time_select, R.id.btn_media})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_vp_util:
                ViewPagerCircleActivity.start(getContext());
                break;
            case R.id.btn_time_select:
                TimeSelectActivity.start(getContext());
                break;
            case R.id.btn_media:

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
