package com.youhuikeji.martin.alllibrary.useView.vpUtils;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Martin on 2017/5/4.
 */

public class VpUtil {
    private static final String TAG = VpUtil.class.getSimpleName();

    private Map<Integer, ViewPager> vpMap = new HashMap<>();

    private static VpUtil assist;

    private VpUtil() {
    }

    public static VpUtil getInstance() {
        if (assist == null) {
            assist = new VpUtil();
        }
        return assist;
    }

    /**
     * 专属用来loop的handler，不做其他使用
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (vpMap != null && vpMap.size() > 0) {
                Set<Integer> keySet = vpMap.keySet();

                if (keySet.contains(msg.what)) {
                    ViewPager pager = vpMap.get(msg.what);
                    pager.setCurrentItem(pager.getCurrentItem() + 1);
                }
            }

        }
    };

    /**
     * 设置ViewPager的轮询
     * 需要做数据头尾添加
     *
     * @param vp    ViewPager
     * @param code  Handler发送的code，map存储的key
     * @param dTime 延迟时间
     */
    public void setVpLoop(final ViewPager vp, final int code, final int dTime) {
        if (vpMap == null) {
            vpMap = new HashMap<>();
        }

        vpMap.put(code, vp);
        final int size = vp.getAdapter().getCount();

        if (3 > size) {
            //如果里面的数据不够三页，不参与轮询
            return;
        }

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            int position;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                this.position = position;
                handler.removeMessages(code);
                handler.sendEmptyMessageDelayed(code, dTime);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 0) {
                    if (position == 0) {
                        vp.setCurrentItem(size - 2, false);
                    } else if (position == size - 1) {
                        vp.setCurrentItem(1, false);
                    }
                }
            }
        });
    }

    /**
     * 开始轮询
     */
    public void startLoop(int code) {
        handler.sendEmptyMessageDelayed(code, 3000);
    }

    /**
     * 停止轮询
     */
    public void stopLoop(int code) {
        handler.removeMessages(code);
    }

}
