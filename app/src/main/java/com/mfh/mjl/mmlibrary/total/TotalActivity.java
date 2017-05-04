package com.mfh.mjl.mmlibrary.total;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.mfh.mjl.mmlibrary.R;
import com.youhuikeji.martin.alllibrary.base.UI;

public class TotalActivity extends UI {

    private static final String TAG = TotalActivity.class.getSimpleName();
    private Button btnJumpVp;
    private TotalUtil assist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        bindView();
        initView();
    }


    protected void bindView() {
        btnJumpVp = findView(R.id.btn_jump_vp);
    }

    protected void initView() {
        assist = TotalUtil.getInstance(this);

        assist.setOnClick(btnJumpVp);
    }

}
