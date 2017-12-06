package com.martin.alllibrary.util.mediaUtil.videoUtil;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.martin.alllibrary.R;

import java.util.ArrayList;

/**
 * 作者：Martin on 2017/7/11 17:13
 * 邮箱：martin0207mfh@163.com
 */
class VideoListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<VideoInfo> vList;

    public VideoListAdapter(Context context, ArrayList<VideoInfo> videoList) {
        this.inflater = LayoutInflater.from(context);
        this.setVideoList(videoList);
    }

    public void setVideoList(ArrayList<VideoInfo> videoList) {
        this.vList = videoList;
    }

    @Override
    public int getCount() {
        return vList.size();
    }

    @Override
    public Object getItem(int p) {
        return vList.get(p);
    }

    @Override
    public long getItemId(int p) {
        return p;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_video_list, parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.vImage.setImageBitmap(vList.get(position).getB());
        holder.vTitle.setText(vList.get(position).getTitle());
        holder.vSize.setText(vList.get(position).getSize());
        holder.vTime.setText(vList.get(position).getTime());

        holder.vImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String bpath = "file://" + vList.get(position).getFilePath();
                intent.setDataAndType(Uri.parse(bpath), "video/*");
                v.getContext().startActivity(intent);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        ImageView vImage;
        TextView vTitle;
        TextView vSize;
        TextView vTime;

        public ViewHolder(View convertView) {
            vImage = (ImageView) convertView.findViewById(R.id.video_img);
            vTitle = (TextView) convertView.findViewById(R.id.video_title);
            vSize = (TextView) convertView.findViewById(R.id.video_size);
            vTime = (TextView) convertView.findViewById(R.id.video_time);
        }
    }
}