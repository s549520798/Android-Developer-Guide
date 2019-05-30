package com.lazylee.apiguidedemo.coretopics.ui_navigation;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lazylee.apiguidedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UiNavFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UiNavFragment extends Fragment {

    public static final String TAG = "UiNavFragment";
    @BindView(R.id.ui_nav_custom_view) Button customBtn;
    @BindView(R.id.canvas_button) Button canvasButton;

    public UiNavFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment UiNavFragment.
     */

    public static UiNavFragment newInstance() {
        return new UiNavFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ui_nav, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.ui_nav_custom_view)
    public void jumpToCustomViewActivity(View view) {
        startActivity(new Intent(view.getContext(), CustomViewActivity.class));
    }

    @OnClick(R.id.canvas_button)
    public void jumpToCanvasActivity(View view) {
        startActivity(new Intent(view.getContext(), CanvasActivity.class));
    }
}
