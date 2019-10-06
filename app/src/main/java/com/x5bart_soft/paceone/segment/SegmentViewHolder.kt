package com.x5bart_soft.paceone.segment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.x5bart_soft.paceone.R

class SegmentViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    val number = itemView.findViewById<TextView>(R.id.number)
    val distance = itemView.findViewById<TextView>(R.id.distance)
    val time = itemView.findViewById<TextView>(R.id.time)



}