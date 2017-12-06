package com.martin.alllibrary.util.viewUtils.vpUtils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Martin on 2017/7/8 14:47
 * 邮箱：martin0207mfh@163.com
 */
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    public   List<Fragment> data;

    public ViewPagerFragmentAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
    }


    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
