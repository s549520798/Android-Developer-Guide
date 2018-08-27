package com.lazylee.apiguidedemo.images_graphics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lazylee.apiguidedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawablesOverviewActivity extends AppCompatActivity {

    @BindView(R.id.linear) LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawables_overview);
        ButterKnife.bind(this);
        // Create a LinearLayout in which to add the ImageView
        mLinearLayout = new LinearLayout(this);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.android);
        // set the ImageView bounds to match the Drawable's dimensions
        imageView.setAdjustViewBounds(true);
        imageView.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        mLinearLayout.addView(imageView);

    }
}
