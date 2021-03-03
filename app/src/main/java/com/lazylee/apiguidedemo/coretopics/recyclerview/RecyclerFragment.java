package com.lazylee.apiguidedemo.coretopics.recyclerview;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lazylee.apiguidedemo.R;

import java.util.ArrayList;

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
        View rootView = inflater.inflate(R.layout.recycler_fragment, container, false);
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new SampleAdapter(mList);
        mAdapter.setItemClickListener(new SampleAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                int adapterPosition = mRecyclerView.getChildAdapterPosition(view);
                int layoutPosition = mRecyclerView.getChildLayoutPosition(view);
                Toast.makeText(getContext(), "position == " + position + " , layout position == " + layoutPosition
                        + "  , adapter position == " + adapterPosition, Toast.LENGTH_LONG).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(itemDecoration);
        for (int i = 0; i < 20; i++) {
            mList.add("测试数据  : " + i + 1);
        }
        mAdapter.notifyDataSetChanged();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d(TAG, "onScrollStateChanged:  state ==" + newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING &&
                        !recyclerView.canScrollVertically(1)) {
                    mAdapter.addFooter("footer");
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
