package com.youhuikeji.martin.alllibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.youhuikeji.martin.alllibrary.R;
import com.youhuikeji.martin.alllibrary.base.BaseActivity;
import com.youhuikeji.martin.alllibrary.special.ExtraName;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class ShowAVActivity extends BaseActivity {

    private static final String TAG = ShowImageActivity.class.getSimpleName();

    private JCVideoPlayerStandard jcPlayer;

    private int stateShow;

    public static void start(Context context, String path, int extraAV) {
        Intent intent = new Intent(context, ShowAVActivity.class);
        intent.putExtra(ExtraName.EXTRA_PATH, path);
        Log.e(TAG, "start:  这里传进来的多媒体文件地址    " + path);
        intent.putExtra(ExtraName.EXTRA_NAME_TYPE, extraAV);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_audio);

        initView();
    }

    private void initView() {
        String path = getIntent().getStringExtra(ExtraName.EXTRA_PATH);
        stateShow = getIntent().getIntExtra(ExtraName.EXTRA_NAME_TYPE, 0);
        jcPlayer = (JCVideoPlayerStandard) findViewById(R.id.jc_video);

        String title;

        if (stateShow == 2) {
            title = "视频播放";
        } else {
            title = "语音播放";
        }

        ((TextView) findView(R.id.txt_top_title)).setText(title);
        if (path != null) {
            jcPlayer.setUp(path, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, title);
        }

        findView(R.id.img_top_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (jcPlayer != null) {
            jcPlayer.release();
            jcPlayer = null;
        }
    }
}
