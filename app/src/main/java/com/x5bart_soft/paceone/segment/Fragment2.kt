package com.x5bart_soft.paceone.segment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.x5bart_soft.paceone.R
import kotlinx.android.synthetic.main.fragment2.*
import java.math.RoundingMode

/**
 * A simple [Fragment] subclass.
 */
class Fragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment2, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val segments = arrayListOf<Segment>()
        val seg = etSegment.text.toString().toDouble()
        val bundle = this.arguments
        val dist = bundle!!.getDouble("dist")
        val time =  bundle.getDouble("time")

        val count =
            (dist / seg).toInt()
//                .toBigDecimal().setScale(0, RoundingMode.HALF_UP).toInt()
        val timeSeg = (time /count).toInt()

                for (i in 0..count)
            segments.add(Segment(i+1 , (i+1) * seg, (timeSeg + (timeSeg*count)).toString()))

        val adapter = SegmentAdapter(segments)
        recyclerView.adapter = adapter

    }
}
