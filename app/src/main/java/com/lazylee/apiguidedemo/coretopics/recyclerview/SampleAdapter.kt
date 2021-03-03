package com.lazylee.apiguidedemo.coretopics.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lazylee.apiguidedemo.R
import com.lazylee.apiguidedemo.coretopics.recyclerview.SampleAdapter.SampleViewHolder
import java.util.*

class SampleAdapter(private val mlist: ArrayList<String>?) : RecyclerView.Adapter<SampleViewHolder>() {
    private var itemClickListener: OnItemClickListener? = null
    private var itemLongClickListener: OnItemLongClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_fragment_item, parent, false)
        return SampleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.itemView.setOnClickListener { v -> itemClickListener!!.onClick(v, position) }
        holder.itemView.setOnLongClickListener { v -> itemLongClickListener!!.onLongClick(v, position) }
        holder.bindView(position, mlist!![position])
    }

    override fun getItemCount(): Int {
        return mlist?.size ?: 0
    }

    fun addFooter(string: String) {
        if (mlist != null) {
            mlist.add(string)
            notifyDataSetChanged()
        }
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    fun setItemLongClickListener(itemLongClickListener: OnItemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener
    }

    inner class SampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mTvID: TextView
        private val mTvStr: TextView
        fun bindView(position: Int, string: String) {
            mTvID.text = "" + position
            mTvStr.text = string
        }

        init {
            mTvID = itemView.findViewById(R.id.recycler_item_id)
            mTvStr = itemView.findViewById(R.id.recycler_item_string)
        }
    }

    public interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }

    public interface OnItemLongClickListener {
        fun onLongClick(view: View, position: Int): Boolean
    }
}