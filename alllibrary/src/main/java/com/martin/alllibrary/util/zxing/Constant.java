package com.martin.alllibrary.util.zxing;

import android.os.Environment;

/**
 * Created by wangguodong on 2017/2/10.
 */
public class Constant {

    /**
     * 扫描类型
     * 条形码或者二维码：REQUEST_SCAN_MODE_ALL_MODE
     * 条形码： REQUEST_SCAN_MODE_BARCODE_MODE
     * 二维码：REQUEST_SCAN_MODE_QRCODE_MODE
     */
    public static final String REQUEST_SCAN_MODE = "ScanMode";
    /**
     * 条形码： REQUEST_SCAN_MODE_BARCODE_MODE
     */
    public static final int REQUEST_SCAN_MODE_BARCODE_MODE = 0X100;
    /**
     * 二维码：REQUEST_SCAN_MODE_ALL_MODE
     */
    public static final int REQUEST_SCAN_MODE_QRCODE_MODE = 0X200;
    /**
     * 条形码或者二维码：REQUEST_SCAN_MODE_ALL_MODE
     */
    public static final int REQUEST_SCAN_MODE_ALL_MODE = 0X300;
    public static final int NOT_NET_STATE = 0;
    public static final int YES_NET_STATE = 1;

    /**
     * 更新
     */
    public final static String APK_URL = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/download";//系统默认不通知

    /**
     * 智能插座
     */
    public final static String SOCKET_CLIENT_NAME = "www.hui2013.com";
    public final static String SOCKET_CLIENT_ID = "qf291d2iB36970Dm";
    public final static String SOCKET_CLIENT_SECRET = "x221D2224K5iX1iv";
    public final static String SOCKET_REDIRECT_URI = "http://www.KonKeAuthCode.com";

    public final static String SOCKET_ACCESS_TOKEN = "plug_access_token";
    public final static String SOCKET_USER_ID= "plug_user_id";
    public final static String SOCKET_HISTORY= "plug_history";

}
