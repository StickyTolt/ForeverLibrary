package com.youhuikeji.martin.alllibrary.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marti on 2017/10/11.
 */

public class BaseAdapter<T> extends android.widget.BaseAdapter {

    private List<T> data;
    private LayoutInflater inflater;
    private Context context;
    private int layoutRes;

    protected BaseAdapter(List<T> data, Context context, int layoutRes) {
        if (data != null) {
            data = new ArrayList<>();
        }
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.layoutRes = layoutRes;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return convertView;
    }
}
