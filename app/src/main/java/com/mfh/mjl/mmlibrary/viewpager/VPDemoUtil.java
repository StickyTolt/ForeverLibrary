package com.mfh.mjl.mmlibrary.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.ListView;

import com.mfh.mjl.mmlibrary.R;
import com.mfh.mjl.mmlibrary.viewpager.adapter.TopResAdapter;
import com.youhuikeji.martin.alllibrary.useView.vpUtils.VpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/5/4.
 */

public class VPDemoUtil {

    public static final int CODE_VP_TOP = 1000;

    private static VPDemoUtil assist;

    private static Context context;

    private VPDemoUtil() {
    }

    public static VPDemoUtil getInstance(Context context) {
        if (assist == null) {
            assist = new VPDemoUtil();
        }
        VPDemoUtil.context = context;
        return assist;
    }

    public void setVpLoop(ViewPager vp, int code, int dTime) {
        VpUtil.getInstance().setVpLoop(vp, code, dTime);
    }

    public void startVpLoop(int code) {
        VpUtil.getInstance().startLoop(code);
    }

    public void stopVpLoop(int code) {
        VpUtil.getInstance().stopLoop(code);
    }

    public void setTopVp(ViewPager vp) {
        vp.setAdapter(new TopResAdapter(getTopData()));
        assist.setVpLoop(vp, CODE_VP_TOP, 3000);
    }

    public List<ImageView> getTopData() {
        List<ImageView> data = new ArrayList<>();

        ImageView imageView1 = new ImageView(context);
        ImageView imageView2 = new ImageView(context);
        ImageView imageView3 = new ImageView(context);
        ImageView imageView4 = new ImageView(context);
        ImageView imageView5 = new ImageView(context);

        imageView1.setImageResource(R.color.blue);
        imageView2.setImageResource(R.color.colorAccent);
        imageView3.setImageResource(R.color.green);
        imageView4.setImageResource(R.color.blue);
        imageView5.setImageResource(R.color.colorAccent);

        data.add(imageView1);
        data.add(imageView2);
        data.add(imageView3);
        data.add(imageView4);
        data.add(imageView5);

        return data;
    }

}