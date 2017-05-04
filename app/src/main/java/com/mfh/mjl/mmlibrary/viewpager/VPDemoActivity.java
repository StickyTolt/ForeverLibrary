package com.mfh.mjl.mmlibrary.viewpager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mfh.mjl.mmlibrary.R;
import com.youhuikeji.martin.alllibrary.base.UI;

public class VPDemoActivity extends UI {

    private ViewPager vpTop;
    private VPDemoUtil assist;

    public static void start(Context context) {
        context.startActivity(new Intent(context, VPDemoActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpdemo);

        bindView();
        initView();
    }

    protected void bindView() {
        vpTop = findView(R.id.vp_top);
    }

    protected void initView() {
        assist = VPDemoUtil.getInstance(this);

        assist.setTopVp(vpTop);

    }

    @Override
    protected void onResume() {
        super.onResume();
        assist.startVpLoop(VPDemoUtil.CODE_VP_TOP);
    }

    @Override
    protected void onPause() {
        super.onPause();
        assist.stopVpLoop(VPDemoUtil.CODE_VP_TOP);
    }

    public void stopLoop(View view) {
        assist.stopVpLoop(VPDemoUtil.CODE_VP_TOP);
    }
}
