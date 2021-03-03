package com.lazylee.apiguidedemo.coretopics.backgroundtasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import com.lazylee.apiguidedemo.R

class BackgroundTasksFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_background_tasks, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    companion object {
        const val TAG = "BackgroundTasksFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        fun newInstance(): BackgroundTasksFragment {
            return BackgroundTasksFragment()
        }
    }
}