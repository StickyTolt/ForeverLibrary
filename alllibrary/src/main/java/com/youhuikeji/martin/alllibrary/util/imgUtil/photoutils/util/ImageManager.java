package com.youhuikeji.martin.alllibrary.util.imgUtil.photoutils.util;

import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youhuikeji.martin.alllibrary.LibCache;
import com.youhuikeji.martin.alllibrary.R;

/**
 * Created by weiyu on 16/4/6.
 */
public class ImageManager {

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private ImageManager() {
    }

    private static class ImageManagerHolder {
        private static ImageManager instance = new ImageManager();
    }

    public static ImageManager getInstance() {
        return ImageManagerHolder.instance;
    }

    // 将资源ID转为Uri
    public Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }

    // 加载网络图片
    public void loadUrlImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.img_load_error)
                .error(R.drawable.img_load_error)   // 加载网络图片
                .crossFade()
                .into(imageView);
    }

    // 加载drawable图片
    public void loadResImage(Context context, int resId, ImageView imageView) {
        Glide.with(context)
                .load(resourceIdToUri(context, resId))
                .placeholder(R.drawable.img_load_error)
                .error(R.drawable.img_load_error)
                .crossFade()
                .into(imageView);
    }

    // 加载本地图片
    public void loadLocalImage(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .placeholder(R.drawable.img_load_error)
                .error(R.drawable.img_load_error)
                .crossFade()
                .into(imageView);
    }

    // 加载网络圆型图片
    public void loadCircleImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.img_load_error)
                .error(R.drawable.img_load_error)
                .crossFade()
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    // 加载drawable圆型图片
    public void loadCircleResImage(Context context, int resId, ImageView imageView) {
        Glide.with(context)
                .load(resourceIdToUri(context, resId))
                .placeholder(R.drawable.img_load_error)
                .error(R.drawable.img_load_error)
                .crossFade()
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    // 加载本地圆型图片
    public void loadCircleLocalImage(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load("file://" + path)
                .placeholder(R.drawable.img_load_error)
                .error(R.drawable.img_load_error)
                .crossFade()
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    /**
     * 清除图片内存缓存
     */
    public void clearImaegMemory() {
        if (Looper.myLooper() == Looper.getMainLooper()) {//只能在主线程运行
            Glide.get(LibCache.getContext()).clearMemory();
        } else
            Log.e("", "清除失败");
    }

    /**
     * 清除图片磁盘缓存
     */
    public void clearImageAllCache() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Glide.get(LibCache.getContext()).clearDiskCache();
                }
            }).start();
        } else {
            Glide.get(LibCache.getContext()).clearDiskCache();
        }
    }

}
