package com.lazylee.apiguidedemo.images_graphics;


import android.content.Intent;
import android.os.Bundle;
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
 * Use the {@link ImagesAndGraphicsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImagesAndGraphicsFragment extends Fragment {

    public static final String TAG = "ImagesAndGraphicsFrag";

    @BindView(R.id.drawables_overview) Button mDrawablesOverview;

    public ImagesAndGraphicsFragment() {
        // Required empty public constructor
    }


    public static ImagesAndGraphicsFragment newInstance() {
        return new ImagesAndGraphicsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_images_and_graphics, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.drawables_overview)
    public void jumpToDrawablesOverviewActivity(View view) {
        startActivity(new Intent(view.getContext(), DrawablesOverviewActivity.class));
    }
}
