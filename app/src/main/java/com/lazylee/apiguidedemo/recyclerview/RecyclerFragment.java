package com.lazylee.apiguidedemo.recyclerview;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazylee.apiguidedemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * demo for recycler view
 */
public class RecyclerFragment extends Fragment {

    public static final String TAG = "RecyclerFragment";

    private RecyclerView mRecyclerView;
    private SampleAdapter mAdapter;

    ArrayList<String> mList = new ArrayList<>();

    public RecyclerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecyclerFragment.
     */
    public static RecyclerFragment newInstance() {
        return new RecyclerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.recycler_fragment,container,false);
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        mAdapter = new SampleAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        for (int i = 0; i < 20; i++) {
            mList.add("测试数据  : " + i+1);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
