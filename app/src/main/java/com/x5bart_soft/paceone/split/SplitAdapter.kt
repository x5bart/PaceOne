package com.x5bart_soft.paceone.split

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.x5bart_soft.paceone.R

open class SplitAdapter(val segmentsList: ArrayList<Split>) :
    RecyclerView.Adapter<SplitViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SplitViewHolder {
        val v = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return SplitViewHolder(v)
    }

    override fun getItemCount(): Int {
        return segmentsList.size
    }

    override fun onBindViewHolder(holder: SplitViewHolder, position: Int) {
        val split = segmentsList[position]
        holder.apply {
            number.text = split.number.toString()
            distance.text = split.distance.toString()
            time.text = split.time
            temp.text = split.temp
            average.text =split.average

        }
    }
}