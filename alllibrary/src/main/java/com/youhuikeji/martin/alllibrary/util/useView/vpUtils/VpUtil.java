package com.youhuikeji.martin.alllibrary.util.useView.vpUtils;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Martin on 2017/5/4.
 */
public class VpUtil<T> {
    private static final String TAG = VpUtil.class.getSimpleName();

    /**
     * 这个Map的作用在于
     * Loop的ViewPager可能会有很多个，那么需要有个容器来存储ViewPager
     * Map就是那个容器
     */
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
     * 设置ViewPager的轮旬
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
                /**
                 * state是ViewPager的滚动状态
                 * 0是停止状态，1是滚动状态
                 * 当ViewPager停止滚动的时候，我们进行无动画切换，更加流畅
                 */
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

    /**
     * 设置ViewPager的点击事件
     *
     * @param vp
     * @param listener
     */
    public void setVpClick(final ViewPager vp, final IVpClickListener listener) {
        if (vp == null) {
            return;
        }
        vp.setOnTouchListener(new View.OnTouchListener() {
            int flagTouch = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        flagTouch = 0;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        flagTouch = 1;
                        break;
                    case MotionEvent.ACTION_UP:
                        if (flagTouch == 0 && listener != null) {
                            listener.onClick(vp.getCurrentItem());
                        }
                        break;
                }
                return false;
            }
        });
    }

    public interface IVpClickListener {
        void onClick(int position);
    }

}
