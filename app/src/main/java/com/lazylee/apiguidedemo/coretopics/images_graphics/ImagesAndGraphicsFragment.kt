package com.lazylee.apiguidedemo.coretopics.images_graphics

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
 * Use the [ImagesAndGraphicsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImagesAndGraphicsFragment : Fragment() {
    @BindView(R.id.drawables_overview)
    var mDrawablesOverview: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_images_and_graphics, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    @OnClick(R.id.drawables_overview)
    fun jumpToDrawablesOverviewActivity(view: View) {
        startActivity(Intent(view.context, DrawablesOverviewActivity::class.java))
    }

    companion object {
        const val TAG = "ImagesAndGraphicsFrag"
        fun newInstance(): ImagesAndGraphicsFragment {
            return ImagesAndGraphicsFragment()
        }
    }
}