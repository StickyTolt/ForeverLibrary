package com.martin.alllibrary.util.imgUtil.photoutils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;

import com.martin.alllibrary.ToolCache;
import com.martin.alllibrary.activity.PhotoSelectActivity;
import com.martin.alllibrary.util.imgUtil.photoutils.util.StorePhotos;
import com.martin.alllibrary.view.ActionSheet;

import java.io.File;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by dell on 2017/5/4.
 */
public class PhotoUtil {

    private static final String TAG = PhotoUtil.class.getSimpleName();

    /**
     * 拍照
     */
    public static Uri takePhoto(Activity activity, int codeRequest) {

        String mir = Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + "ZYDHImage" + File.separator;
        File scanner5Directory = new File(mir);
        if (!scanner5Directory.exists())
            scanner5Directory.mkdir();
        Uri mOutput = Uri.fromFile(new File(mir, System.currentTimeMillis() + "image.jpg"));
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent2.putExtra(MediaStore.EXTRA_OUTPUT, mOutput);
        activity.startActivityForResult(intent2, codeRequest);
        return mOutput;

    }

    public static String takePhotoStr(Activity activity, int codeTake) {
        String fileName = getPhotoPath(".png", "ToolImg");//参数分别为   格式，存放的文件夹名
        Log.e(TAG, "onClick:   获取的 文件地址   " + fileName);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        File out = new File(fileName);
        if (out.exists()) {
            out.delete();
        }
        Uri uri = Uri.fromFile(out);
        // 获取拍照后未压缩的原图片，并保存在uri路径中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        activity.startActivityForResult(intent, codeTake);
        return fileName;
    }

    /**
     * 选择 图片
     */
    public static void pickPhoto(Activity activity, int codeRequest) {
        PhotoSelectActivity.startPhotoSelect(activity, codeRequest);
    }

    /**
     * 照片 dialog 展示
     */
    /*          case CODE_REQUEST_PICK_BASE:
                    String pathImg = data.getStringExtra("imgPath");
                    if (pathImg != null && !"".equals(pathImg)) {
                        path = PhotoUtil.getSmallPath(pathImg);
                        setImgUI(path);
                    }
                    break;
                case CODE_REQUEST_TAKE_BASE:
                    if (uriImg == null)
                        return;
                    path = PhotoUtil.getSmallPath(uriImg);
                    if (path != null && !"".equals(path)) {
                        setImgUI(path);
                    }
                    break;*/
    public static void showPhotoDialog(final Activity activity, final int pickCode
            , final int takeCode, final OnTakePhotoBackUri backUri) {

        ActionSheet.showSheetDialog(activity, "取消", new String[]{"选择照片", "拍照"}
                , false, new ActionSheet.MenuItemClickListener() {
                    @Override
                    public void onItemClick(int itemPosition) {
                        switch (itemPosition) {
                            case 0:
                                pickPhoto(activity, pickCode);
                                break;
                            case 1:
                                Uri photo = takePhoto(activity, takeCode);
                                if (backUri != null) {
                                    backUri.onTake(photo);
                                }
                                break;
                        }
                    }
                });
    }

    public interface OnTakePhotoBackUri {
        void onTake(Uri uri);
    }

    @SuppressLint("SdCardPath")
    private static String getPhotoPath(String form, String Name) {
        String name = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + form;
        String externalPath = ToolCache.getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString();
        String pathname = externalPath + "/" + Name + "/";
        String fileName = pathname + name;
        File file = new File(pathname);
        if (!file.exists()) {
            Log.e("TAG", "第一次创建文件夹");
            file.mkdirs();// 如果文件夹不存在，则创建文件夹
        }
        return fileName;
    }

    /**
     * 获取图片压缩之后的地址
     * 实际就是将图片进行压缩
     */
    public static String getSmallPath(Uri uriImg) {
        if (uriImg == null)
            return "";
        return getSmallPath(uriImg.getPath());
    }


    public static String getSmallPath(String pathImg) {
        Bitmap smallBitmap = PhotoUtil.getSmallBitmap(pathImg);
        new StorePhotos(smallBitmap, pathImg);
        return pathImg;
    }

    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;// 设置为ture,只读取图片的大小，不把它加载到内存中去
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 960, 1600);// 此处，选取了480x800分辨率的照片

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;// 处理完后，同时需要记得设置为false

        return BitmapFactory.decodeFile(filePath, options);
    }

    // 计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

}
