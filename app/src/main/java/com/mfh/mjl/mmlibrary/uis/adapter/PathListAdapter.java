package com.mfh.mjl.mmlibrary.uis.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.martin.alllibrary.base.adapter.BaseRecycleViewAdapter;
import com.mfh.mjl.mmlibrary.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Martin on 2017/12/6 17:15
 * 邮箱：martin0207mfh@163.com
 */
public class PathListAdapter extends BaseRecycleViewAdapter<String,PathListAdapter.ViewHolder> {

    private static final String TAG = PathListAdapter.class.getSimpleName();

    public PathListAdapter(List<String> data, Context context) {
        super(data, context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.item_path, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtContent.setText(data.get(position));
    }


    class ViewHolder extends BaseRecycleViewAdapter.ViewHolder {

        @BindView(R.id.txt_content)
        TextView txtContent;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}