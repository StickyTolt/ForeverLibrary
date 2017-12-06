package com.martin.alllibrary.util.mediaUtil.videoUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.martin.alllibrary.R;

import java.util.ArrayList;

public class VideoListActivity extends AppCompatActivity {

    private static final String TAG = VideoListActivity.class.getSimpleName();
    ListView lv_content;

    ArrayList<VideoInfo> vList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        initView();
    }

    private void initView() {
        lv_content = (ListView) findViewById(R.id.lv_content);
        lv_content.setOnItemClickListener(new ItemClick());
        initData();
    }


    private void initData() {
        vList = new ArrayList<>();
        String[] mediaColumns = new String[]{MediaStore.MediaColumns.DATA,
                BaseColumns._ID, MediaStore.MediaColumns.TITLE, MediaStore.MediaColumns.MIME_TYPE,
                MediaStore.Video.VideoColumns.DURATION, MediaStore.MediaColumns.SIZE};
        @SuppressLint("Recycle") Cursor cursor = getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI, mediaColumns,
                null, null, null);
        assert cursor != null;
        if (cursor.moveToFirst()) {
            do {
                VideoInfo info = new VideoInfo();
                info.setFilePath(cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)));
                info.setMimeType(cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.MediaColumns.MIME_TYPE)));
                info.setTitle(cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.MediaColumns.TITLE)));
                int id = cursor.getInt(cursor
                        .getColumnIndexOrThrow(BaseColumns._ID));
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inDither = false;
                options.inPreferredConfig = Bitmap.Config.ARGB_4444;
                info.setB(MediaStore.Video.Thumbnails.getThumbnail(getContentResolver(), id,
                        MediaStore.Images.Thumbnails.MICRO_KIND, options));
                vList.add(info);
            } while (cursor.moveToNext());
        }

        VideoListAdapter adapter = new VideoListAdapter(this, vList);
        lv_content.setAdapter(adapter);

    }

    private class ItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            lv_content.getItemAtPosition(position);
            String filePath = vList.get(position).getFilePath();
            Log.e(TAG, "onItemClick: 这个是地址    " + filePath );
            Intent intent = new Intent();
            intent.putExtra("path", filePath);
            setResult(RESULT_OK, intent);
            finish();
        }

    }

}
