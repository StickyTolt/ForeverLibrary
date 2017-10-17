package com.mfh.mjl.mmlibrary.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.Toast;

import com.mfh.mjl.mmlibrary.R;
import com.mfh.mjl.mmlibrary.viewpager.adapter.TopResAdapter;
import com.youhuikeji.martin.alllibrary.util.useView.vpUtils.VpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Martin on 2017/5/4.
 */
public class VPDemoUtil {

    private static final String TAG = VPDemoUtil.class.getSimpleName();

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
        setVpLoop(vp, CODE_VP_TOP, 3000);
        setVpClick(vp);
        vp.setCurrentItem(1);
    }

    public void setVpClick(ViewPager vp) {
        VpUtil.getInstance().setVpClick(vp, new VpUtil.IVpClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(context, "onClick: 你点击的条目是    ===    " + position, Toast.LENGTH_SHORT).show();
            }
        });
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
