package com.martin.alllibrary.util.viewUtils.vpUtils;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.martin.alllibrary.extras.ExtraCode;
import com.martin.alllibrary.util.showUtil.LogUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Martin on 2017/5/4.
 */
public class ViewPagerUtils {

    private static final String TAG = ViewPagerUtils.class.getSimpleName();

    private ViewPager viewPager;
    private boolean isLoop = false;

    /**
     * 专属用来loop的handler，不做其他使用
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ExtraCode.EXTRA_VIEWPAGER_LOOP:
                    if (viewPager != null) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                    break;
            }
        }
    };


    /**
     * 设置ViewPager的轮旬
     * 需要做数据头尾添加
     *
     * @param vp    ViewPager
     * @param dTime 延迟时间
     */
    public void setVpLoop(final ViewPager vp, final int dTime) {
        if (vp == null) {
            throw new NullPointerException("the viewpager must no null");
        }
        viewPager = vp;
        final int size = vp.getAdapter().getCount();

        if (3 > size) {
            //如果里面的数据不够三页，不参与轮询
            LogUtils.e("数据不够3条，不能开启轮询");
            return;
        }

        isLoop = true;
        vp.setCurrentItem(1);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            int position;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                this.position = position;
                if (isLoop) {
                    handler.removeMessages(ExtraCode.EXTRA_VIEWPAGER_LOOP);
                    handler.sendEmptyMessageDelayed(ExtraCode.EXTRA_VIEWPAGER_LOOP, dTime);
                }
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
    public void startLoop() {
        startLoop(1500);
    }

    public void startLoop(int dTime) {
        isLoop = true;
        handler.sendEmptyMessageDelayed(ExtraCode.EXTRA_VIEWPAGER_LOOP, dTime);
    }

    /**
     * 停止轮询
     */
    public void stopLoop() {
        isLoop = false;
        handler.removeMessages(ExtraCode.EXTRA_VIEWPAGER_LOOP);
    }

    /**
     * 设置ViewPager的点击事件
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
