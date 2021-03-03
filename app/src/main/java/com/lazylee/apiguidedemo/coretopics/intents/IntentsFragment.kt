package com.lazylee.apiguidedemo.coretopics.intents;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lazylee.apiguidedemo.R;

public class IntentsFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "IntentsFragment";

    private Button mBtn;
    private Button mBtn2;

    public IntentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment IntentsFragment.
     */
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
        View rootView = inflater.inflate(R.layout.intents_fragment, container, false);
        mBtn = rootView.findViewById(R.id.button);
        mBtn2 = rootView.findViewById(R.id.common_intent);
        mBtn.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                break;
            case R.id.common_intent:
                Intent intent = new Intent(getContext(), CommonIntentActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
