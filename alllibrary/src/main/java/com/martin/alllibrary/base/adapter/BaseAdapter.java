package com.martin.alllibrary.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

    public List<T> data;
    public LayoutInflater inflater;
    public Context context;

    public BaseAdapter(List<T> data, Context context) {
        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    /**
     * 刷新数据
     */
    public void refreshRes(List<T> list) {
        if (list != null) {
            data.clear();
            data.addAll(list);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加数据
     */
    public void addRes(List<T> list) {
        if (list != null) {
            data.addAll(list);
            notifyDataSetChanged();
        }
    }

    protected String noNull(String str) {
        return str == null ? "" : str;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
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
    public abstract View getView(int position, View convertView, ViewGroup parent);

}
