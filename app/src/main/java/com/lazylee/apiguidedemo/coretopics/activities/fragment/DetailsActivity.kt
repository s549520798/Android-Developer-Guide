package com.lazylee.apiguidedemo.coretopics.activities.fragment

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lazylee.apiguidedemo.R

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        if (resources.configuration.orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line with the list so we don't need this activity.
            finish()
            return
        }
        if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            val details = DetailsFragment()
            details.arguments = intent.extras
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, details)
                    .commit()
        }
    }
}