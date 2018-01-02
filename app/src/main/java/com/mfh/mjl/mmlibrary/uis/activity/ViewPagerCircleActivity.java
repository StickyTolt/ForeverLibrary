package com.mfh.mjl.mmlibrary.uis.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.martin.alllibrary.base.mvc.BaseActivity;
import com.martin.alllibrary.util.viewUtils.vpUtils.ViewPagerImgAdapter;
import com.martin.alllibrary.util.viewUtils.vpUtils.ViewPagerScroller;
import com.martin.alllibrary.util.viewUtils.vpUtils.ViewPagerUtils;
import com.mfh.mjl.mmlibrary.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewPagerCircleActivity extends BaseActivity {

    private static final String TAG = ViewPagerCircleActivity.class.getSimpleName();

    @BindView(R.id.img_top_back)
    ImageView imgTopBack;
    @BindView(R.id.txt_top_title)
    TextView txtTopTitle;
    @BindView(R.id.txt_top_right)
    TextView txtTopRight;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.btn_stop_start)
    Button btnStopStart;

    //======================================================
    private ViewPagerUtils pagerUtils;
    private boolean isLoop = true;

    public static void start(Context context) {
        Intent intent = new Intent(context, ViewPagerCircleActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_circle);
        ButterKnife.bind(this);

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        txtTopTitle.setText("ViewPagerUtils展示");

        initViewPager();

        pagerUtils = new ViewPagerUtils();
        setLoop();
        setViewPagerClick();
    }

    private void setViewPagerClick() {
        pagerUtils.setVpClick(vpContent, new ViewPagerUtils.IVpClickListener() {
            @Override
            public void onClick(int position) {
                showToast("点击的条目是 " + position);
            }
        });
    }

    /**
     * 设置 循环
     */
    private void setLoop() {
        pagerUtils.setVpLoop(vpContent, 3000);
    }

    private void initViewPager() {
        ImageView img0 = new ImageView(getContext());
        ImageView img1 = new ImageView(getContext());
        ImageView img2 = new ImageView(getContext());
        ImageView img3 = new ImageView(getContext());
        ImageView img4 = new ImageView(getContext());

        img0.setBackgroundColor(getResources().getColor(R.color.main_color));
        img1.setBackgroundColor(getResources().getColor(R.color.red));
        img2.setBackgroundColor(getResources().getColor(R.color.gray));
        img3.setBackgroundColor(getResources().getColor(R.color.main_color));
        img4.setBackgroundColor(getResources().getColor(R.color.red));

        List<ImageView> data = new ArrayList<>();
        data.add(img0);
        data.add(img1);
        data.add(img2);
        data.add(img3);
        data.add(img4);

        vpContent.setAdapter(new ViewPagerImgAdapter(data));

        ViewPagerScroller scroller = new ViewPagerScroller(getContext());
        scroller.setScrollDuration(1500);
        scroller.initViewPagerScroll(vpContent);
    }

    @OnClick({R.id.img_top_back, R.id.btn_stop_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_top_back:
                finish();
                break;
            case R.id.btn_stop_start:
                if (isLoop) {
                    pagerUtils.stopLoop();
                } else {
                    pagerUtils.startLoop();
                }
                isLoop = !isLoop;
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        pagerUtils.startLoop(1000);
        isLoop = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        pagerUtils.stopLoop();
        isLoop = false;
    }

}
