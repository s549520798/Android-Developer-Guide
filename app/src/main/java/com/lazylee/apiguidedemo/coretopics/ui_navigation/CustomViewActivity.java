package com.lazylee.apiguidedemo.coretopics.ui_navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lazylee.apiguidedemo.R;
import com.lazylee.apiguidedemo.coretopics.ui_navigation.custom.DialView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomViewActivity extends AppCompatActivity {

    private static final String TAG = "CustomViewActivity";

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.dial_view) DialView mDialView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int n = item.getOrder();
        mDialView.setSelectCount(n);
        return super.onOptionsItemSelected(item);
    }
}
