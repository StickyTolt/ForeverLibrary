package com.mfh.mjl.mmlibrary.LibraryUse.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.mfh.mjl.mmlibrary.R;
import com.martin.alllibrary.base.BaseActivity;
import com.martin.alllibrary.extras.ExtraCode;
import com.martin.alllibrary.util.mediaUtil.MediaUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoUseActivity extends BaseActivity {

    private static final String TAG = VideoUseActivity.class.getSimpleName();

    @BindView(R.id.btn_pick_recode)
    Button btnPickRecode;
    @BindView(R.id.vv_content)
    VideoView vvContent;

    String filPaths = "";

    public static void start(Context context) {
        Intent intent = new Intent(context, VideoUseActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_use);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

    }

    @OnClick({R.id.btn_pick_recode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pick_recode:
                MediaUtil.showVideoDialog(getActivity(), ExtraCode.EXTRA_REQUEST_RECORD, ExtraCode.EXTRA_REQUEST_PICK);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ExtraCode.EXTRA_REQUEST_RECORD:
                    if (null != data) {
                        Uri uri = data.getData();
                        if (uri == null) {
                            Log.e(TAG, "onActivityResult: 返回的 uri 为空");
                            return;
                        } else {
                            Cursor c = getContentResolver().query(uri,
                                    new String[]{MediaStore.MediaColumns.DATA},
                                    null, null, null);
                            if (c != null && c.moveToFirst()) {
                                filPaths = c.getString(0);
                            }
                            vvContent.setVideoPath("file://" + filPaths);
                            vvContent.start();
                        }
                    } else {
                        Log.e(TAG, "onActivityResult: 返回的  data 为空");
                    }
                    break;
                case ExtraCode.EXTRA_REQUEST_PICK:
                    if (data != null) {
                        filPaths = data.getStringExtra("path");
                        vvContent.setVideoPath("file://" + filPaths);
                        vvContent.start();
                    }
                    break;
            }
        }
    }
}
