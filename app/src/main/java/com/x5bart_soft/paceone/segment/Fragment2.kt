package com.x5bart_soft.paceone.segment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.x5bart_soft.paceone.Fragment1
import com.x5bart_soft.paceone.R
import com.yandex.mobile.ads.AdRequest
import com.yandex.mobile.ads.AdSize
import kotlinx.android.synthetic.main.fragment1.*
import kotlinx.android.synthetic.main.fragment2.*
import java.math.RoundingMode

class Fragment2 : Fragment() {
    private val segments = arrayListOf<Segment>()
    var seg = 1.0
    var dist = 0.0
    var time = 0
    var h = 0
    var m = 0
    var s = 0
    var etSegId=0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment2, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN

        val BLOCK_ID = "adf-326819/1043357"
//        val BLOCK_ID = "adf-326819/1042468"
        banner_view_split.blockId = BLOCK_ID
        banner_view_split.adSize = AdSize.BANNER_320x50
        val adRequest = AdRequest.builder().build()
        banner_view_split.loadAd(adRequest)

        val bundle = this.arguments
        dist = bundle!!.getDouble("dist")
        etKm.setText("$dist")
        h = bundle.getInt("h")
        m = bundle.getInt("m")
        s = bundle.getInt("s")
        etH.setText("$h")
        etM.setText("$m")
        etS.setText("$s")
        timeSec()
        rv()

        etH.setOnFocusChangeListener { _, _ ->
            etSegId = 1
        }
        etM.setOnFocusChangeListener { _, _ ->
            etSegId = 1
        }
        etS.setOnFocusChangeListener { _, _ ->
            etSegId = 1
        }
        etKm.setOnFocusChangeListener { _, _ ->
            etSegId = 2
        }
        etSegment.setOnFocusChangeListener { _, _ ->
            etSegId = 3
        }

        etH.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etH.text.toString().isNotEmpty() && etSegId == 1) autoRv()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etM.text.toString().isNotEmpty() && etSegId == 1) autoRv()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etS.text.toString().isNotEmpty() && etSegId == 1) autoRv()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etKm.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etKm.text.toString().isNotEmpty() && etSegId == 2) autoRv()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etSegment.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etSegment.text.toString().isNotEmpty() && etSegId == 3) autoRv()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etH.setOnClickListener { etH.selectAll() }
        etM.setOnClickListener { etM.selectAll() }
        etS.setOnClickListener { etS.selectAll() }
        etKm.setOnClickListener { etKm.selectAll() }
        etSegment.setOnClickListener { etSegment.selectAll() }






        btrecalculate.setOnClickListener {
            (recyclerView.adapter as SegmentAdapter).segmentsList.clear()
            read()
            rv()
        }
        etSegment.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                (recyclerView.adapter as SegmentAdapter).segmentsList.clear()
                read()
                rv()
                true
            } else {
                false
            }
        }
    }

    fun notNull(){
        if (etH.text.toString().isEmpty()) etH.setText("0")
        if (etM.text.toString().isEmpty()) etM.setText("00")
        if (etS.text.toString().isEmpty()) etS.setText("00")
        if (etKm.text.toString().isEmpty()) etH.setText("0.0")
        if (etSegment.text.toString().isEmpty()) etH.setText("0.0")
    }

    private fun timeSec() {
        time = (((h * Fragment1.hour) + m) * Fragment1.hour) + s
    }

    private fun rv() {
        notNull()
        read()
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

    private fun read() {
        seg = etSegment.text.toString().toDouble()
        dist = etKm.text.toString().toDouble()
        h = etH.text.toString().toInt()
        m = etM.text.toString().toInt()
        s = etS.text.toString().toInt()
        timeSec()
    }
    fun clearRv(){
        (recyclerView.adapter as SegmentAdapter).segmentsList.clear()
    }
    fun autoRv(){
        clearRv()
        rv()
    }
}
