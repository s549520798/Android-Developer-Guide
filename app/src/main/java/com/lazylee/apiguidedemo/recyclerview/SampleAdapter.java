package com.lazylee.apiguidedemo.recyclerview;

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
    public void onBindViewHolder(@NonNull SampleViewHolder holder, int position) {
        holder.bindView(position, mlist.get(position));
    }

    @Override
    public int getItemCount() {
        return mlist == null ? 0 : mlist.size();
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
}
