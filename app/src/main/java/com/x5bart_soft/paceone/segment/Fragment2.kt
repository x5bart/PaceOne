package com.x5bart_soft.paceone.segment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.x5bart_soft.paceone.Fragment1
import com.x5bart_soft.paceone.R
import kotlinx.android.synthetic.main.fragment2.*
import java.math.RoundingMode

/**
 * A simple [Fragment] subclass.
 */
class Fragment2 : Fragment() {
    val segments = arrayListOf<Segment>()
    var seg = 1.0
    //    val seg = etSegment.text.toString().toDouble()
    var dist = 0.0
    var time = 0
    var h = 0
    var m = 0
    var s = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment2, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)


        val bundle = this.arguments
        dist = bundle!!.getDouble("dist")
        etKm.setText("$dist")
        h = bundle.getInt("h")
        m = bundle.getInt("m")
        s = bundle.getInt("t")
        etH.setText("$h")
        etM.setText("$m")
        etS.setText("$s")
        timeSec()
        rv()

        btrecalculate.setOnClickListener {
            (recyclerView.adapter as SegmentAdapter).segmentsList.clear()
            read()
            rv()
        }
etSegment.setOnEditorActionListener{ _,actionId ,_ ->
    if (actionId == EditorInfo.IME_ACTION_GO){
        (recyclerView.adapter as SegmentAdapter).segmentsList.clear()
        read()
        rv()
        true
    } else{
        false
    }
}

    }

    fun timeSec() {
        time = (((h * Fragment1.hour) + m) * Fragment1.hour) + s
    }

    fun rv() {
        val count = (dist / seg).toBigDecimal().setScale(0, RoundingMode.UP).toInt()
        var i = 0
        while (i != count) {
            i++
            var sg = (seg * i).toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toDouble()
            if (sg > dist) sg = dist
            val timeSeg = ((sg * 1000) / ((dist * 1000) / time)).toInt()
            val h = (timeSeg / 3600)
            val m = ((timeSeg - (h * 3600)) / 60)
            val s = (((timeSeg - (h * 3600) - (m * 60))))
            var mPrint = m.toString()
            var sPrint = s.toString()
            if (m < 10) mPrint = "0$m"
            if (s < 10) sPrint = "0$s"

            segments.add(Segment(i, sg, "$h:$mPrint:$sPrint"))

        }
        val adapter = SegmentAdapter(segments)
        recyclerView.adapter = adapter
    }

    fun read() {
        seg = etSegment.text.toString().toDouble()
        dist = etKm.text.toString().toDouble()
        h = etH.text.toString().toInt()
        m = etM.text.toString().toInt()
        s = etM.text.toString().toInt()
        timeSec()
    }

}
