package com.martin.alllibrary.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.martin.alllibrary.util.misc.interfacies.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Martin on 2017/12/6 17:13
 * 邮箱：martin0207mfh@163.com
 */
public abstract class BaseRecycleViewAdapter<T, H extends BaseRecycleViewAdapter.ViewHolder> extends RecyclerView.Adapter<H> {

    protected List<T> data;
    protected Context context;
    protected LayoutInflater inflater;
    protected OnItemClickListener listener;

    public BaseRecycleViewAdapter(List<T> data, Context context) {
        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
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

    /**
     * 添加单个数据
     */
    public void addRes(T item) {
        if (item != null) {
            data.add(item);
            notifyItemChanged(data.size());
        }
    }

    /**
     * 获取数据
     */
    public List<T> getData() {
        return data;
    }

    /**
     * 根据 position 获取数据
     */
    public T getItem(int position) {
        return data.size() > position ? data.get(position) : null;
    }

    /**
     * 设置条目点击监听
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public abstract H onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(H holder, int position);

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }


   public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

            if (listener != null) {
                listener.onItemClick(getAdapterPosition());
            }
        }
    }

}
