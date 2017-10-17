package com.youhuikeji.martin.alllibrary.util.imgUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：Martin on 2017/7/10 11:22
 * 邮箱：martin0207mfh@163.com
 */
public class TakePhotoUtils {

    private static final String TAG = TakePhotoUtils.class.getSimpleName();

    /**
     * 拍照
     * 清晰度高，图片过大可能 OOM
     */
    public static Uri takePhoto(Activity mActivity, int flag) throws IOException {
        //指定拍照intent
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri imageUri = null;
        if (takePictureIntent.resolveActivity(mActivity.getPackageManager()) != null) {
            String sdcardState = Environment.getExternalStorageState();
            File outputImage = null;
            if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
                outputImage = createImageFile(mActivity);
            } else {
                Toast.makeText(mActivity.getApplicationContext(), "内存异常", Toast.LENGTH_SHORT).show();
            }
            try {
                assert outputImage != null;
                if (outputImage.exists()) {
                    outputImage.delete();
                }
                outputImage.createNewFile();
            } catch (IOException e) {
                Log.e(TAG, "takePhoto: 这里是删除文件失败      ===   或者创建文件失败");
                e.printStackTrace();
            }
            imageUri = Uri.fromFile(outputImage);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            mActivity.startActivityForResult(takePictureIntent, flag);
        }
        return imageUri;
    }

    /**
     * 系统级拍照返回
     * 返回的图片清晰度很低，若是文字类，基本看不清
     */
    public static void takePhotoSmall(Activity activity, int codeRequest) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, codeRequest);
    }

    /**
     * 选择系统内的图片
     * 两种图片选择都可使用
     */
    public static void pickPhoto(Activity activity, int codeRequest) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activity.startActivityForResult(intent, codeRequest);
        } catch (Exception e) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            activity.startActivityForResult(intent, codeRequest);
        }
    }

    /**
     * 创建 图片缓存地址
     */
    public static File createImageFile(Activity mActivity) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp;//创建以时间命名的文件名称
        File storageDir = getOwnCacheDirectory(mActivity, "takephoto");//创建保存的路径
        File image = new File(storageDir.getPath(), imageFileName + ".jpg");
        if (!image.exists()) {
            try {
                //在指定的文件夹中创建文件
                image.createNewFile();
            } catch (Exception e) {
                Log.e(TAG, "createImageFile: 创建文件夹失败   ");
            }
        }

        return image;
    }


    /**
     * 根据目录创建文件夹
     */
    public static File getOwnCacheDirectory(Context context, String cacheDir) {
        File appCacheDir = null;
        //判断sd卡正常挂载并且拥有权限的时候创建文件
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
            appCacheDir = new File(Environment.getExternalStorageDirectory(), cacheDir);
        } else {
            Toast.makeText(context, "SDK或权限异常", Toast.LENGTH_SHORT).show();
        }
        if (appCacheDir == null || !appCacheDir.exists() && !appCacheDir.mkdirs()) {
            appCacheDir = context.getCacheDir();
        } else {
            Toast.makeText(context, "SDK或权限异常，创建文件夹失败", Toast.LENGTH_SHORT).show();
        }
        return appCacheDir;
    }


    /**
     * 检查是否有权限
     */
    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        return perm == 0;
    }
}
