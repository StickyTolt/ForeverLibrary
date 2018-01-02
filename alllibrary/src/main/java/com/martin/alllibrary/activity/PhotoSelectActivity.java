package com.martin.alllibrary.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.martin.alllibrary.R;
import com.martin.alllibrary.base.mvc.BaseActivity;
import com.martin.alllibrary.extras.ExtraName;
import com.martin.alllibrary.util.imgUtil.photoutils.util.DeviceUtil;
import com.martin.alllibrary.util.imgUtil.photoutils.util.FilenameUtils;
import com.martin.alllibrary.util.imgUtil.photoutils.util.PhotoFolderInfo;
import com.martin.alllibrary.util.imgUtil.photoutils.util.PhotoInfo;
import com.martin.alllibrary.util.imgUtil.photoutils.util.PhotoListAdapter;
import com.martin.alllibrary.util.imgUtil.photoutils.util.PhotoTools;

import java.util.ArrayList;
import java.util.List;

public class PhotoSelectActivity extends BaseActivity {

    private List<PhotoFolderInfo> mAllPhotoFolderList;
    private PhotoListAdapter listAdapter;
    private List<PhotoInfo> mCurPhotoList;
    private static final int REFRESH = 1;

    public static void startPhotoSelect(Activity activity, int codeRequest) {
        Intent intent = new Intent(activity, PhotoSelectActivity.class);
        activity.startActivityForResult(intent, codeRequest);
    }

    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REFRESH:
                    listAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoselect);
        GridView gridView = (GridView) findViewById(R.id.gv_photo_list);
        mAllPhotoFolderList = new ArrayList<>();
        mCurPhotoList = new ArrayList<>();
        DisplayMetrics dm = DeviceUtil.getDisplayMetrics(this);
        int mScreenWidth = dm.widthPixels;
        int mScreenHeight = dm.heightPixels;

        listAdapter = new PhotoListAdapter(this, mCurPhotoList, mScreenWidth);
        gridView.setAdapter(listAdapter);
        getPhotos();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhotoInfo info = mCurPhotoList.get(position);
                String ext = FilenameUtils.getExtension(info.getPhotoPath());
                if ((ext.equalsIgnoreCase("png")
                        || ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("jpeg"))) {
                    Intent intent = new Intent();
                    intent.putExtra(ExtraName.EXTRA_DATA, info.getPhotoPath());
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    showToast("图片格式不对");
                }
            }
        });
    }

    private void getPhotos() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                mAllPhotoFolderList.clear();
                List<PhotoFolderInfo> allFolderList = PhotoTools.getAllPhotoFolder(PhotoSelectActivity.this);
                mAllPhotoFolderList.addAll(allFolderList);
                mCurPhotoList.clear();
                PhotoFolderInfo photoFolderInfo = mAllPhotoFolderList.get(0);
                if (photoFolderInfo.getPhotoList() != null) {
                    mCurPhotoList.addAll(photoFolderInfo.getPhotoList());
                }
                mhandler.sendEmptyMessage(REFRESH);
            }
        }.start();
    }

}
