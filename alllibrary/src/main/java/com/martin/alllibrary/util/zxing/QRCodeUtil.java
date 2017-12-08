package com.martin.alllibrary.util.zxing;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.zxing.WriterException;
import com.martin.alllibrary.util.zxing.encode.EncodingHandler;

import java.io.UnsupportedEncodingException;

/**
 * 作者：Martin on 2017/7/12 09:02
 * 邮箱：martin0207mfh@163.com
 */
public class QRCodeUtil {

    public static Bitmap getQRCode(String key, Context context) {
        Bitmap qrCode = null;
        try {
            qrCode = EncodingHandler.create2Code(key, dpTOpx(350, context));
        } catch (WriterException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return qrCode;
    }


    //         * dp 转换为 px
    public static int dpTOpx(float dpValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
