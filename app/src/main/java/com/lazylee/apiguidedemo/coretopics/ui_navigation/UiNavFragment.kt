package com.lazylee.apiguidedemo.coretopics.ui_navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.lazylee.apiguidedemo.R

/**
 * A simple [Fragment] subclass.
 * Use the [UiNavFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UiNavFragment : Fragment() {
    @BindView(R.id.ui_nav_custom_view)
    var customBtn: Button? = null

    @BindView(R.id.canvas_button)
    var canvasButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_ui_nav, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    @OnClick(R.id.ui_nav_custom_view)
    fun jumpToCustomViewActivity(view: View) {
        startActivity(Intent(view.context, CustomViewActivity::class.java))
    }

    @OnClick(R.id.canvas_button)
    fun jumpToCanvasActivity(view: View) {
        startActivity(Intent(view.context, CanvasActivity::class.java))
    }

    companion object {
        const val TAG = "UiNavFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment UiNavFragment.
         */
        fun newInstance(): UiNavFragment {
            return UiNavFragment()
        }
    }
}