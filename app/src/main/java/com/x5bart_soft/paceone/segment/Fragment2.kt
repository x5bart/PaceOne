package com.x5bart_soft.paceone.segment


import android.os.Bundle
import android.util.Log
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
        val time = bundle.getInt("time")
        var h = 0
        var m = 0
        var s = 0


        val count =
            (dist / seg)
                .toBigDecimal()
                .setScale(0, RoundingMode.HALF_UP)
                .toInt()

        val timeSeg = time / count
//        var e = 0
//        while (e != count) {
//            e++
//            segments.add(Segment(e, seg * e, "${timeSeg * e}"))
//            dist - (seg * e)
//        }


        for (i in 1..count) {
            Log.d("mylogs", "i = $i")
            segments.add(Segment(i , i  * seg, "${timeSeg * i }"))
        }

        val adapter = SegmentAdapter(segments)
        recyclerView.adapter = adapter

    }
}
