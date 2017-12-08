package com.mfh.mjl.mmlibrary.uis.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.Result;
import com.martin.alllibrary.base.BaseActivity;
import com.martin.alllibrary.util.zxing.Constant;
import com.martin.alllibrary.util.zxing.ScanListener;
import com.martin.alllibrary.util.zxing.ScanManager;
import com.martin.alllibrary.view.MyImageView;
import com.mfh.mjl.mmlibrary.R;
import com.mfh.mjl.mmlibrary.uis.kotlins.activity.ShowTextActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：Martin on 2017/12/8 16:51
 * 邮箱：martin0207mfh@163.com
 */
public class ScanToolActivity extends BaseActivity {

    @BindView(R.id.sfv_content)
    SurfaceView sfvContent;
    @BindView(R.id.txt_input)
    TextView txtInput;
    @BindView(R.id.txt_light)
    TextView txtLight;
    @BindView(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @BindView(R.id.img_line)
    ImageView imgLine;
    @BindView(R.id.img_scan)
    MyImageView imgScan;
    @BindView(R.id.rl_capture)
    RelativeLayout rlCapture;
    @BindView(R.id.rl_all)
    RelativeLayout rlAll;

    //===============================================================

    private ScanManager scanManager;

    public static void start(Context context) {
        Intent intent = new Intent(context, ScanToolActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_tool);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        initScan();
    }

    /**
     * 初始化扫描
     */
    private void initScan() {
        scanManager = new ScanManager(this, sfvContent, rlAll, rlCapture, imgLine,
                Constant.REQUEST_SCAN_MODE_ALL_MODE, new ScanListener() {
            @Override
            public void scanResult(Result rawResult, Bundle bundle) {
                String scanData = rawResult.getText().trim();
                ShowTextActivity.Companion.start(getActivity(), scanData);
            }

            @Override
            public void scanError(Exception e) {
                showToast("扫描失败，请重试！");
            }
        });
    }

    @OnClick({R.id.txt_input, R.id.txt_light})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_input:

                break;
            case R.id.txt_light:
                scanManager.switchLight();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        scanManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scanManager.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scanManager.onDestroy();
    }
}
