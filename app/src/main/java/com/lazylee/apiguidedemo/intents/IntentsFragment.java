package com.lazylee.apiguidedemo.intents;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazylee.apiguidedemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IntentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IntentsFragment extends Fragment {
    public static final String TAG = "IntentsFragment";




    public IntentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment IntentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IntentsFragment newInstance() {
        IntentsFragment fragment = new IntentsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.intents_fragment, container, false);
    }

}
