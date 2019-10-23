package com.x5bart_soft.paceone.segment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.x5bart_soft.paceone.Fragment1
import com.x5bart_soft.paceone.R
import com.yandex.mobile.ads.AdRequest
import com.yandex.mobile.ads.AdSize
import kotlinx.android.synthetic.main.fragment2.*
import java.math.RoundingMode

class Fragment2 : Fragment(), SeekBar.OnSeekBarChangeListener {
    private val segments = arrayListOf<Segment>()
    var seg = 1.0
    var dist = 0.0
    var time = 0
    var h = 0
    var m = 0
    var s = 0
    var etSegId = 0
    var negSeg = 0.0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment2, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        sbNegSeg.setOnSeekBarChangeListener(this)



        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN

//        val BLOCK_ID = "adf-326819/1043357"
        val BLOCK_ID = "adf-326819/1042468"
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
        rv()

        etH.setOnFocusChangeListener { _, _ ->
            etSegId = 1
        }
        etM.setOnFocusChangeListener { _, _ ->
            etSegId = 2
        }
        etS.setOnFocusChangeListener { _, _ ->
            etSegId = 3
        }
        etKm.setOnFocusChangeListener { _, _ ->
            etSegId = 4
        }
        etSegment.setOnFocusChangeListener { _, _ ->
            etSegId = 5
        }

        etH.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etSegId == 1) autoRv()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etSegId == 2) autoRv()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etSegId == 3) autoRv()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etKm.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etSegId == 4) autoRv()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etSegment.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etSegId == 5) autoRv()
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
        ivNegativeSplit.setOnClickListener {
            val dialog = NegativSplitDialog()
            dialog.show(fragmentManager!!, "info")
        }
    }

    override fun onProgressChanged(
        seekBar: SeekBar,
        progress: Int,
        fromUser: Boolean
    ) {
        val pro = progress / 2.toDouble()
        tvNegSeg!!.text = "$pro%"
        negSeg = pro
        autoRv()

    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
    }

    fun notNull() {

        h = if (etH.text.toString().isEmpty()) 0 else etH.text.toString().toInt()
        if (h == 0 && !etH.isFocused) etH.setText("0")

        m = if (etM.text.toString().isEmpty()) 0 else etM.text.toString().toInt()
        if (m == 0 && !etM.isFocused) etM.setText("00")

        s = if (etS.text.toString().isEmpty()) 0 else etS.text.toString().toInt()
        if (s == 0 && !etS.isFocused) etS.setText("00")

        dist =
            if (etKm.text.toString().isEmpty() || etKm.text.toString() == ".") 0.0 else etKm.text.toString().toDouble()
        if (dist == 0.0 && !etKm.isFocused) etKm.setText("0.0")

        seg =
            if (etSegment.text.toString().isEmpty() || etSegment.text.toString() == ".") 0.0 else etSegment.text.toString().toDouble()
        if (seg == 0.0 && !etSegment.isFocused) etSegment.setText("0.0")

        time = (((h * Fragment1.hour) + m) * Fragment1.hour) + s

        if (etSegId != 2 && m < 10) etM.setText("0$m")
        if (etSegId != 3 && s < 10) etS.setText("0$s")

    }

    private fun rv() {
        notNull()
        if (time != 0 && dist != 0.0 && seg != 0.0) {
            val count = (dist / seg).toBigDecimal().setScale(0, RoundingMode.UP).toInt()
            val pace = time / dist // секунд на км средняя
            val negativ = (negSeg / 100)
            val negDelta = ((1 - negativ) - (1 + negativ))
            var timeNext = 0.0

            var i = 0
            while (i != count) {
                i++
                var sg = (seg * i).toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toDouble()
                if (sg > dist) {
                    seg = dist - (sg - seg)
                    sg = dist
                }

                val percentNext = sg / dist
                val percentPrev = (seg * (i - 1)) / dist

                val coefNext = negDelta * percentNext
                val coefPrev = negDelta * percentPrev
                val coefAvg = ((coefPrev) + (coefNext)) / 2
                val timeSegPrev = ((pace * seg) * (1 - (negativ + coefAvg)))

                val timeSum = (timeSegPrev + timeNext)
                timeNext = timeSum
                val timeSumPrint = timeSum.toBigDecimal().setScale(0, RoundingMode.HALF_UP).toInt()
                val timeSegPrint = timeSegPrev.toInt()

                val h = (timeSumPrint / 3600).toBigDecimal().setScale(0, RoundingMode.HALF_UP).toInt()
                val m = ((timeSumPrint - (h * 3600)) / 60)
                val s = (((timeSumPrint - (h * 3600) - (m * 60))))
                var mPrint = m.toString()
                var sPrint = s.toString()
                if (m < 10) mPrint = "0$m"
                if (s < 10) sPrint = "0$s"

                val pM = (timeSegPrint / 60)
                val pS = (timeSegPrint - (pM * 60))
                var pSPrint = pS.toString()
                if (pS < 10) pSPrint = "0$pS"

                val avgPace = (timeSum / sg).toBigDecimal().setScale(0, RoundingMode.HALF_UP).toInt()
                val aM = (avgPace / 60)
                val aS = (avgPace - (aM * 60))
                var aSPrint = aS.toString()
                if (aS<10)aSPrint = "0$aS"


                segments.add(
                    Segment(
                        i,
                        sg,
                        "$h:$mPrint:$sPrint",
                        "$pM:$pSPrint",
                        "$aM:$aSPrint"
                    )
                )
            }
            val adapter = SegmentAdapter(segments)
            recyclerView.adapter = adapter
        }
    }

    fun clearRv() {
        (recyclerView.adapter as SegmentAdapter).segmentsList.clear()
    }

    fun autoRv() {
        clearRv()
        rv()
    }
}
