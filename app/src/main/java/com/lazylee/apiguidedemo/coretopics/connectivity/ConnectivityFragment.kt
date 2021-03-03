package com.lazylee.apiguidedemo.coretopics.connectivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import com.lazylee.apiguidedemo.R

/**
 * A simple [Fragment] subclass.
 * Use the [ConnectivityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConnectivityFragment : Fragment() {
    private var expandableListView: ExpandableListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.connectivity_fragment, container, false)
        expandableListView = rootView.findViewById(R.id.expand_list)
        return inflater.inflate(R.layout.connectivity_fragment, container, false)
    }

    companion object {
        const val TAG = "ConnectivityFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ConnectivityFragment.
         */
        fun newInstance(): ConnectivityFragment {
            val fragment = ConnectivityFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}