package com.mfh.mjl.mmlibrary.uis.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.martin.alllibrary.util.misc.interfacies.OnItemClickListener;
import com.mfh.mjl.mmlibrary.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Martin on 2017/12/6 17:15
 * 邮箱：martin0207mfh@163.com
 */
public class PathListAdapter extends RecyclerView.Adapter<PathListAdapter.ViewHolder> {

    private static final String TAG = PathListAdapter.class.getSimpleName();

    private List<String> data;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener listener;

    public PathListAdapter(List<String> data, Context context) {
        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * 设置点击监听
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void refresh(List<String> list) {
        if (list != null) {
            data.clear();
            data.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addRes(String item) {
        if (item != null) {
            data.add(item);
            notifyItemChanged(getItemCount());
        }
    }

    public List<String> getData() {
        return data;
    }

    public String getItem(int position) {
        if (data.size() > position) {
            return data.get(position);
        } else {
            return null;
        }
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

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_content)
        TextView txtContent;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "ViewHolder: adapterPosition : " + getAdapterPosition());
                    Log.e(TAG, "ViewHolder: Position : " + getPosition());
                    Log.e(TAG, "ViewHolder: LayoutPosition : " + getLayoutPosition());
                    Log.e(TAG, "ViewHolder: OldPosition : " + getOldPosition());
                }
            });
        }
    }

}
