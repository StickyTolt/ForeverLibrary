package com.martin.alllibrary.util.mediaUtil.videoUtil;

import android.graphics.Bitmap;

/**
 * 作者：Martin on 2017/7/11 14:17
 * 邮箱：martin0207mfh@163.com
 */
public class VideoInfo {

    private String filePath;
    private String mimeType;
    private Bitmap b;
    private String title;
    private String time;
    private String size;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Bitmap getB() {
        return b;
    }

    public void setB(Bitmap b) {
        this.b = b;
    }
}
