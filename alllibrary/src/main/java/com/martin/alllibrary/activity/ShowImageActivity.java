package com.martin.alllibrary.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.martin.alllibrary.R;
import com.martin.alllibrary.util.imgUtil.glide.ImageLoader;
import com.martin.alllibrary.view.TouchImageView.ImageViewTouch;

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

        initView();
    }

    private void initView() {
        String imgPath = getIntent().getStringExtra(EXTRA_IMG_PATH);

        Log.e(TAG, "initView:  传进来的 图片地址  ==  " + imgPath);

        imgContent = findViewById(R.id.img_content);

        findViewById(R.id.img_back).setOnClickListener(this);
        imgContent.setOnClickListener(this);

        if (imgPath != null) {
            ImageLoader.getInstance().loadUrlImage(this, imgPath, imgContent);
        } else {
            Toast.makeText(this, "数据获取为空", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        finish();
    }
}
