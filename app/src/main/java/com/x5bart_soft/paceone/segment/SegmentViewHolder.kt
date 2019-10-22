package com.x5bart_soft.paceone.segment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.x5bart_soft.paceone.R

class SegmentViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    val number = itemView.findViewById<TextView>(R.id.etNumber)!!
    val distance = itemView.findViewById<TextView>(R.id.etDistance)!!
    val time = itemView.findViewById<TextView>(R.id.etTime)!!
    val pace = itemView.findViewById<TextView>(R.id.etPace)!!
    val average = itemView.findViewById<TextView>(R.id.etAverage)!!
}