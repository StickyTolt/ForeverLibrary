package com.youhuikeji.martin.alllibrary.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.youhuikeji.martin.alllibrary.R;
import com.youhuikeji.martin.alllibrary.util.imgUtil.photoutils.util.ImageManager;
import com.youhuikeji.martin.alllibrary.view.TouchImageView.ImageViewTouch;

import butterknife.ButterKnife;

public class ShowImageActivity extends Activity implements View.OnClickListener {

    private static final String EXTRA_IMG_PATH = "imgPath";
    private static final String TAG = ShowImageActivity.class.getSimpleName();

    ImageViewTouch imgContent;

    public static void start(Context context, String imgPath) {
        Intent intent = new Intent(context, ShowImageActivity.class);
        intent.putExtra(EXTRA_IMG_PATH, imgPath);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        String imgPath = getIntent().getStringExtra(EXTRA_IMG_PATH);

        Log.e(TAG, "initView:  传进来的 图片地址  ==  " + imgPath);

        imgContent = (ImageViewTouch) findViewById(R.id.img_content);

        findViewById(R.id.img_back).setOnClickListener(this);
        imgContent.setOnClickListener(this);

        if (imgPath != null) {
            ImageManager.getInstance().loadUrlImage(this, imgPath, imgContent);
        } else {
            Toast.makeText(this, "数据获取为空", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        finish();
    }
}