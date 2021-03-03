package com.lazylee.apiguidedemo.coretopics.touch

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lazylee.apiguidedemo.R

class TouchInputFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_touch_input, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        const val TAG = "TouchInputFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment TouchInputFragment.
         */
        fun newInstance(): TouchInputFragment {
            //        Bundle args = new Bundle();
//        fragment.setArguments(args);
            return TouchInputFragment()
        }
    }
}