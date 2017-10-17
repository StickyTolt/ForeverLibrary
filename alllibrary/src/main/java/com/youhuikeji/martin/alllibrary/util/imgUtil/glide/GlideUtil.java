package com.youhuikeji.martin.alllibrary.util.imgUtil.glide;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.youhuikeji.martin.alllibrary.R;

/**
 * Martin on 2017/5/4.
 */
public class GlideUtil {

    /**
     * 加载普通图片（http://或者file://）
     */
    public static void load(Context context, String url, ImageView imageView) {
        load(context, url, imageView, R.mipmap.register_person, R.mipmap.register_person);
    }

    /**
     * 高度自定义方法
     *
     * @param loadRes  加载中展示图片
     * @param errorRes 加载错误展示图片
     */
    public static void load(Context context, String url, ImageView imageView, int loadRes, int errorRes) {
        Glide.with(context).load(url)
                .placeholder(loadRes)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(errorRes)
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    /**
     * 加载为圆形图片（一般为头像加载）
     */
    public static void loadCircle(Context context, String url, ImageView imageView) {
        loadCircle(context, url, imageView, R.mipmap.register_person, R.mipmap.register_person);
    }

    public static void loadCircle(Context context, String url, ImageView imageView, int loadRes, int errorRes) {
        Glide.with(context).load(url)
                .placeholder(loadRes)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(errorRes)
                .dontAnimate()
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    /**
     * 加载为圆形图片（一般为头像加载）
     */
    public static void loadCircle(Context context, Uri uri, ImageView imageView) {
        loadCircle(context, uri, imageView, R.mipmap.register_person, R.mipmap.register_person);
    }

    public static void loadCircle(Context context, Uri uri, ImageView imageView, int loadRes, int errorRes) {
        Glide.with(context).load(uri)
                .placeholder(loadRes)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(errorRes)
                .dontAnimate()
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    /**
     * 加载为圆形图片（一般为头像加载）
     * 设置大小
     */
    public static void loadCircleLimit(Context context, String url, ImageView imageView, int width, int height) {
        loadCircleLimit(context, url, imageView, width, height, R.mipmap.register_person, R.mipmap.register_person);
    }

    public static void loadCircleLimit(Context context, String url, ImageView imageView, int width, int height, int loadRes, int errorRes) {
        Glide.with(context).load(url)
                .placeholder(loadRes)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(width, height)
                .error(errorRes)
                .dontAnimate()
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    /**
     * 加载本地图片（资源文件）
     */
    public static void loadLocalImage(Context context, int resId, ImageView imageView) {
        Glide.with(context).load(resId).dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    /**
     * 加载为圆形图片（一般为头像加载）
     */
    public static void loadCircleImage(Context context, int resId, ImageView imageView) {
        Glide.with(context).load(resId).dontAnimate().transform(new GlideCircleTransform(context)).into(imageView);
    }
}
