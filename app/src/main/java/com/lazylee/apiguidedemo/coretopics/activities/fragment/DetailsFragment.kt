package com.lazylee.apiguidedemo.coretopics.activities.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailsFragment : Fragment() {
    var shownIndex = 0
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            shownIndex = arguments!!.getInt(ARG_CUR_CHOICE, 0)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist.  The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed.  Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
            return null
        }
        val scroller = ScrollView(activity)
        val text = TextView(activity)
        val padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, activity!!.resources.displayMetrics).toInt()
        text.setPadding(padding, padding, padding, padding)
        scroller.addView(text)
        text.text = Shakespeare.DIALOGUE[shownIndex]
        return scroller
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: is called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: is called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: is called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: is called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: is called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: is called")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: is called")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach: is called")
    }

    companion object {
        private const val TAG = "DetailsFragment"
        private const val ARG_CUR_CHOICE = "cur_choice"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment DetailsFragment.
         */
        fun newInstance(index: Int): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putInt(ARG_CUR_CHOICE, index)
            fragment.arguments = args
            return fragment
        }
    }
}