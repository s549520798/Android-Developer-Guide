package com.lazylee.apiguidedemo.coretopics.recyclerview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lazylee.apiguidedemo.R
import java.util.*

/**
 * demo for recycler view
 */
class RecyclerFragment : Fragment() {
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: SampleAdapter? = null
    var mList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.recycler_fragment, container, false)
        mRecyclerView = rootView.findViewById(R.id.recycler_view)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        mRecyclerView!!.layoutManager = layoutManager
        mAdapter = SampleAdapter(mList)
        val itemClickListener =object : SampleAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                val adapterPosition = mRecyclerView!!.getChildAdapterPosition(view)
                val layoutPosition = mRecyclerView!!.getChildLayoutPosition(view)
                Toast.makeText(context, "position == $position, layout position == $layoutPosition, adapter position == $adapterPosition",
                        Toast.LENGTH_LONG).show()
            }
        }
        mAdapter!!.setItemClickListener(itemClickListener)
        mRecyclerView!!.adapter = mAdapter
        val itemDecoration = DividerItemDecoration(mRecyclerView!!.context, layoutManager.orientation)
        mRecyclerView!!.addItemDecoration(itemDecoration)
        for (i in 0..19) {
            mList.add("测试数据  : " + i + 1)
        }
        mAdapter!!.notifyDataSetChanged()
        mRecyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                Log.d(TAG, "onScrollStateChanged:  state ==$newState")
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING &&
                        !recyclerView.canScrollVertically(1)) {
                    mAdapter!!.addFooter("footer")
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
    }

    companion object {
        const val TAG = "RecyclerFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RecyclerFragment.
         */
        fun newInstance(): RecyclerFragment {
            return RecyclerFragment()
        }
    }
}