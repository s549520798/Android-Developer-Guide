package com.lazylee.apiguidedemo.coretopics.activities.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.ListFragment
import com.lazylee.apiguidedemo.R

class TitlesFragment : ListFragment() {
    private var mDualPane = false
    private var mCurCheckPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Populate list with our static array of titles.
        listAdapter = ArrayAdapter(activity!!,
                android.R.layout.simple_list_item_activated_1, Shakespeare.TITLES)
        // Check to see if we have a frame in which to embed the details
        // fragment directly in the containing UI.
        val detailsFrame = activity!!.findViewById<View>(R.id.details)
        mDualPane = detailsFrame != null && detailsFrame.visibility == View.VISIBLE
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0)
        }
        if (mDualPane) {
            // In dual-pane mode, the list view highlights the selected item.
            listView.choiceMode = ListView.CHOICE_MODE_SINGLE
            // Make sure our UI is in the correct state.
            showDetails(mCurCheckPosition)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("curChoice", mCurCheckPosition)
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        showDetails(position)
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

    /**
     * Helper function to show the details of a selected item, either by
     * displaying a fragment in-place in the current UI, or starting a
     * whole new activity in which it is displayed.
     */
    fun showDetails(index: Int) {
        mCurCheckPosition = index
        if (mDualPane) {
            // We can display everything in-place with fragments, so update
            // the list to highlight the selected item and show the data.
            listView.setItemChecked(index, true)

            // Check what fragment is currently shown, replace if needed.
            var details = fragmentManager!!.findFragmentByTag(index.toString() + "") as DetailsFragment?
            if (details == null || details.shownIndex != index) {
                // Make new fragment to show this selection.
                details = DetailsFragment.Companion.newInstance(index)

                // Execute a transaction, replacing any existing fragment
                // with this one inside the frame.
                val ft = fragmentManager!!.beginTransaction()
                if (index == 0) {
                    ft.add(R.id.details, details, 0.toString() + "")
                } else {
                    ft.replace(R.id.details, details, details.shownIndex.toString() + "")
                }
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                ft.commit()
            }
        } else {
            // Otherwise we need to launch a new activity to display
            // the dialog fragment with selected text.
            val intent = Intent()
            intent.setClass(activity!!, DetailsActivity::class.java)
            intent.putExtra("index", index)
            startActivity(intent)
        }
    }

    companion object {
        private const val TAG = "TitlesFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment TitlesFragment.
         */
        fun newInstance(): TitlesFragment {
            //        Bundle args = new Bundle();
//        fragment.setArguments(args);
            return TitlesFragment()
        }
    }
}