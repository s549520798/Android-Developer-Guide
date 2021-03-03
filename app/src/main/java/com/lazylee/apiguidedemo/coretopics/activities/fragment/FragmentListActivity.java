package com.lazylee.apiguidedemo.coretopics.activities.fragment;



import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import com.lazylee.apiguidedemo.R;

public class FragmentListActivity extends AppCompatActivity {

    private static final String TAG = "FragmentListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_list);
        Log.d(TAG, "onCreate: is called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: is called !");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: is called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: is called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: is called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: is called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: is called");
    }
}
