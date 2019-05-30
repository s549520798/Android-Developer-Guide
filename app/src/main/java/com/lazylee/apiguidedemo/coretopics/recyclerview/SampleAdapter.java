package com.lazylee.apiguidedemo.coretopics.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lazylee.apiguidedemo.R;

import java.util.ArrayList;

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.SampleViewHolder> {

    private ArrayList<String> mlist;
    private OnItemClickListener itemClickListener = null;
    private OnItemLongClickListener itemLongClickListener = null;

    public SampleAdapter(ArrayList<String> mlist) {
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public SampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fragment_item, parent, false);
        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(v, position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return itemLongClickListener.onLongClick(v, position);
            }
        });
        holder.bindView(position, mlist.get(position));
    }

    @Override
    public int getItemCount() {
        return mlist == null ? 0 : mlist.size();
    }

    public void addFooter(String string) {
        if (mlist != null) {
            mlist.add(string);
            notifyDataSetChanged();
        }
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public class SampleViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvID;
        private TextView mTvStr;

        public SampleViewHolder(View itemView) {
            super(itemView);
            mTvID = itemView.findViewById(R.id.recycler_item_id);
            mTvStr = itemView.findViewById(R.id.recycler_item_string);
        }

        private void bindView(int position, String string) {
            mTvID.setText("" + position);
            mTvStr.setText(string);
        }
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onLongClick(View view, int position);
    }
}
