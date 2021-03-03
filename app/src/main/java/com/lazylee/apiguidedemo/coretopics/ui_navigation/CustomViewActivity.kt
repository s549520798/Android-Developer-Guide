package com.lazylee.apiguidedemo.coretopics.ui_navigation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.lazylee.apiguidedemo.R
import com.lazylee.apiguidedemo.coretopics.ui_navigation.custom.DialView

class CustomViewActivity : AppCompatActivity() {
    @BindView(R.id.toolbar)
    var mToolbar: Toolbar? = null

    @BindView(R.id.dial_view)
    var mDialView: DialView? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)
        ButterKnife.bind(this)
        setSupportActionBar(mToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.custom_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val n = item.order
        mDialView!!.setSelectCount(n)
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val TAG = "CustomViewActivity"
    }
}