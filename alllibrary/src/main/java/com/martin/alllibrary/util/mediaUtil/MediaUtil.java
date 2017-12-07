package com.martin.alllibrary.util.mediaUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;

import com.martin.alllibrary.util.mediaUtil.videoUtil.VideoListActivity;
import com.martin.alllibrary.view.ActionSheet;

/**
 * 作者：Martin on 2017/7/11 09:36
 * 邮箱：martin0207mfh@163.com
 */
public class MediaUtil {

    public static final int VIDEO_RECORD_REQUEST = 1000;
    public static final int VIDEO_PICK_REQUEST = 1001;

    /**
     * 视频 选择dialog
     */
    /* switch (requestCode) {
            case VIDEO_RECORD_REQUEST:
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
                    }
                } else {
                    Log.e(TAG, "onActivityResult: 返回的  data 为空");
                }
                break;
            case VIDEO_PICK_REQUEST:
                if (resultCode == 102 && data != null) {
                    filPaths = data.getStringExtra("path");
                    vvContent.setVideoPath("file://" +filPaths);
//                    vvContent.setVideoURI(Uri.parse( filPaths));
                    vvContent.start();
                }
                break;
        }*/
    public static void showVideoDialog(
            final Activity activity, final int recordCode, final int pickCode) {

        ActionSheet.showSheetDialog(activity, "取消"
                , new String[]{"拍摄视频", "选择视频"}, true, new ActionSheet.MenuItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (position == 0) {
                            takeVideo(activity, recordCode);
                        } else if (position == 1) {
                            pickVideo(activity, pickCode);
                        }
                    }
                });
    }


    /**
     * 拍摄视频
     */
    public static void takeVideo(Activity activity, int code) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);// 创建一个请求视频的意图
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0.95);// 设置视频的质量，值为0-1，
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 15);// 设置视频的录制长度，s为单位
        intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 5 * 1024 * 1024L);// 设置视频文件大小，字节为单位
        activity.startActivityForResult(intent, code);// 设置请求码，在onActivityResult()方法中接收结果
    }

    /**
     * 选择视频
     */
    public static void pickVideo(Activity activity, int code) {
        Intent intent = new Intent(activity, VideoListActivity.class);
        activity.startActivityForResult(intent, code);
    }

    /**
     * @param videoPath 视频路径
     *                  * @param width 图片宽度
     *                  * @param height 图片高度
     *                  * @param kind eg:MediaStore.Video.Thumbnails.MICRO_KIND MINI_KIND: 512 x 384，MICRO_KIND: 96 x 96
     *                  * @return
     */
    public static Bitmap getVideoImg(String videoPath, int width, int height, int kind) {
        // 获取视频的缩略图
        Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
        //extractThumbnail 方法二次处理,以指定的大小提取居中的图片,获取最终我们想要的图片
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }


}
