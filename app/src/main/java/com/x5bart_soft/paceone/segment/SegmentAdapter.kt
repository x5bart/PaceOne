package com.x5bart_soft.paceone.segment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.x5bart_soft.paceone.R

open class SegmentAdapter(val segmentsList: ArrayList<Segment>) :
    RecyclerView.Adapter<SegmentViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SegmentViewHolder {
        val v = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return SegmentViewHolder(v)
    }

    override fun getItemCount(): Int {
        return segmentsList.size
    }


    override fun onBindViewHolder(holder: SegmentViewHolder, position: Int) {
        val segment = segmentsList[position]
        holder.apply {
            number.text = segment.number.toString()
            distance.text = ("${segment.distance} km")
            time.text = segment.time

        }
    }

    fun clear() {
        segmentsList.clear()
    }

}