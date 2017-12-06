package com.martin.alllibrary.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.martin.alllibrary.R;
import com.martin.alllibrary.activity.model.AreaModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Martin on 2017/8/23 14:19
 * 邮箱：martin0207mfh@163.com
 */
public class SelectCountyAdapter extends BaseAdapter {

    private List<AreaModel.ProvinceBean.CityBean.DistrictBean> data;
    private LayoutInflater inflater;
    private Context context;

    public SelectCountyAdapter(List<AreaModel.ProvinceBean.CityBean.DistrictBean> data, Context context) {
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
    public void refreshRes(List<AreaModel.ProvinceBean.CityBean.DistrictBean> list) {
        if (list != null) {
            data.clear();
            data.addAll(list);
            notifyDataSetChanged();
        }
    }

    /**
     * 获取选中的item
     */
    public AreaModel.ProvinceBean.CityBean.DistrictBean getCheckItem() {
        for (int i = 0; i < data.size(); i++) {
            if (getItem(i).isIsChecked()) {
                return getItem(i);
            }
        }
        if (getCount() > 0) {
            setCheck(0);
            return getItem(0);
        }
        return null;
    }

    /**
     * 设置选中
     */
    public void setCheck(int position) {
        for (int i = 0; i < getCount(); i++) {
            AreaModel.ProvinceBean.CityBean.DistrictBean item = getItem(i);
            if (i != position) {
                if (item.isIsChecked()) {
                    item.setIsChecked(false);
                }
            } else {
                item.setIsChecked(true);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public AreaModel.ProvinceBean.CityBean.DistrictBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_select_area, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AreaModel.ProvinceBean.CityBean.DistrictBean item = getItem(position);
        if (item.isIsChecked()) {
            holder.txtContent.setBackgroundColor(context.getResources().getColor(R.color.orange_small));
        } else {
            holder.txtContent.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        holder.txtContent.setText(item.getName());

        return convertView;
    }

    static class ViewHolder {

        TextView txtContent;

        ViewHolder(View view) {
            txtContent = (TextView) view.findViewById(R.id.txt_content);
        }
    }
}
