package com.lazylee.apiguidedemo.coretopics.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.OnClick
import com.lazylee.apiguidedemo.R
import com.lazylee.apiguidedemo.coretopics.activities.fragment.FragmentListActivity

/**
 * A simple [Fragment] subclass.
 * Use the [ActivitiesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ActivitiesFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.activities_fragment, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    @OnClick(R.id.fragment_btn)
    fun jumpToFragmentActivity() {
        startActivity(Intent(activity, FragmentListActivity::class.java))
    }

    companion object {
        const val TAG = "ActivitiesFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ActivitiesFragment.
         */
        fun newInstance(): ActivitiesFragment {
            return ActivitiesFragment()
        }
    }
}