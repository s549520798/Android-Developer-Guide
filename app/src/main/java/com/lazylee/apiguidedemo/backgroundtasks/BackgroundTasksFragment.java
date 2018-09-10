package com.lazylee.apiguidedemo.backgroundtasks;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazylee.apiguidedemo.R;

import butterknife.ButterKnife;


public class BackgroundTasksFragment extends Fragment {

    public static final String TAG = "BackgroundTasksFragment";

    public BackgroundTasksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static BackgroundTasksFragment newInstance() {
        return new BackgroundTasksFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_background_tasks, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
