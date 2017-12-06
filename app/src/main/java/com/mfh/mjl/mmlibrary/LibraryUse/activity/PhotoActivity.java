package com.mfh.mjl.mmlibrary.LibraryUse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mfh.mjl.mmlibrary.R;
import com.martin.alllibrary.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotoActivity extends BaseActivity {

    private static final int REQUEST_CAMERA_CODE = 100;
    private static final int REQUEST_PREVIEW_CODE = 101;

    @BindView(R.id.btn_pick_photo)
    Button btnPickPhoto;
    @BindView(R.id.btn_take_photo)
    Button btnTakePhoto;
    @BindView(R.id.img_content)
    ImageView imgContent;

    public static void start(Context context) {
        Intent intent = new Intent(context, PhotoActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    break;
//                 预览
                case REQUEST_PREVIEW_CODE:
                    break;
            }
        }
    }

    @OnClick({R.id.btn_pick_photo, R.id.btn_take_photo, R.id.img_content})
    public void onViewClicked(View view) {

    }
}
