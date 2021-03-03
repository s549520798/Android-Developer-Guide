package com.lazylee.apiguidedemo.coretopics.intents

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.lazylee.apiguidedemo.R

class IntentsFragment : Fragment(), View.OnClickListener {
    private var mBtn: Button? = null
    private var mBtn2: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.intents_fragment, container, false)
        mBtn = rootView.findViewById(R.id.button)
        mBtn2 = rootView.findViewById(R.id.common_intent)
        mBtn?.setOnClickListener(this)
        mBtn2?.setOnClickListener(this)
        return rootView
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button -> {
            }
            R.id.common_intent -> {
                val intent = Intent(context, CommonIntentActivity::class.java)
                startActivity(intent)
            }
            else -> {
            }
        }
    }

    companion object {
        const val TAG = "IntentsFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment IntentsFragment.
         */
        fun newInstance(): IntentsFragment {
            return IntentsFragment()
        }
    }
}