package com.x5bart_soft.paceone.segment


import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.PopupMenu
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.x5bart_soft.paceone.Fragment1
import com.x5bart_soft.paceone.MainActivity
import com.x5bart_soft.paceone.R
import com.yandex.mobile.ads.AdRequest
import com.yandex.mobile.ads.AdSize
import kotlinx.android.synthetic.main.fragment2.*
import java.math.RoundingMode
import java.util.*

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
    var tmp = 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        tmp = MainActivity.FLAG_MILE_TO_KM
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        MainActivity.FLAG_MILE_TO_KM = tmp
        when (Fragment1.resume) {
            1 -> {
                clearRv()
                when (MainActivity.FLAG_MILE_TO_KM) {
                    1 -> mileToKm()
                    2 -> kmToMile()
                }
                Fragment1.resume = 2
            }
            3 -> {
                clearRv()
                rv()
            }
        }
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val locale = Locale.getDefault()
        val configuration = Configuration()
        configuration.locale = locale
        activity!!.baseContext.resources.updateConfiguration(configuration, null)
        return inflater.inflate(R.layout.fragment2, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        sbNegSeg.setOnSeekBarChangeListener(this)
        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN

        val sPref = activity!!.getPreferences(Context.MODE_PRIVATE)
        MainActivity.FLAG_MILE_TO_KM = sPref.getInt(MainActivity.APP_MILE_TO_KM, 0)
        when (MainActivity.FLAG_MILE_TO_KM) {
            1 -> {
                textView77.text = resources.getString(R.string.km)
                textView555.text = resources.getString(R.string.km)
                textView11.text = resources.getString(R.string.km_2)
            }
            2 -> {
                textView77.text = resources.getString(R.string.mile)
                textView555.text = resources.getString(R.string.mile)
                textView11.text = resources.getString(R.string.mile_3)
            }
        }


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
        ivInfoKm.setOnClickListener { showPopupKm(ivInfoKm) }

    }


    fun showPopupKm(v: View) {
        val popupMenu = PopupMenu(activity, v, Gravity.NO_GRAVITY)
        popupMenu.inflate(R.menu.popup_menu_km)
        if (MainActivity.FLAG_MILE_TO_KM == 2) {
            popupMenu.menu.findItem(R.id.km_3).setTitle(R.string._3_km_mile)
            popupMenu.menu.findItem(R.id.km_5).setTitle(R.string._5_km_mile)
            popupMenu.menu.findItem(R.id.km_10).setTitle(R.string._10_km_mile)
        }

        popupMenu.setOnMenuItemClickListener { item ->
            etKm.requestFocus()
            var tmp = 0.0
            when (item.itemId) {
                R.id.km_3 -> tmp = 3.0
                R.id.km_5 -> tmp = 5.0
                R.id.km_10 -> tmp = 10.0
                R.id.km_21 -> tmp = 21.097
                R.id.km_42 -> tmp = 42.195
            }
            if (MainActivity.FLAG_MILE_TO_KM == 2) tmp /= (MainActivity.MILEKM / MainActivity.KM)
            etKm.setText("$tmp")
            true
        }
        popupMenu.show()
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

        time = (((h * MainActivity.MINUTE) + m) * MainActivity.MINUTE) + s

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
                val timeSeg = ((pace * seg) * (1 - (negativ + coefAvg)))

                val timeSum = (timeSeg + timeNext)
                timeNext = timeSum
                val timeSumPrint =
                    timeSum.toBigDecimal().setScale(0, RoundingMode.HALF_UP).toInt()
                val timeSegPrint = timeSeg.toInt()

                val h =
                    (timeSumPrint / MainActivity.HOUR.toInt()).toBigDecimal()
                        .setScale(0, RoundingMode.HALF_UP).toInt()
                val m = ((timeSumPrint - (h * MainActivity.HOUR)) / MainActivity.MINUTE).toInt()
                val s =
                    (((timeSumPrint - (h * MainActivity.HOUR) - (m * MainActivity.MINUTE)))).toInt()
                var mPrint = m.toString()
                var sPrint = s.toString()
                if (m < 10) mPrint = "0$m"
                if (s < 10) sPrint = "0$s"

                val pM = (timeSegPrint / MainActivity.MINUTE)
                val pS = (timeSegPrint - (pM * MainActivity.MINUTE))
                var pSPrint = pS.toString()
                if (pS < 10) pSPrint = "0$pS"

                val avgPace =
                    (timeSum / sg).toBigDecimal().setScale(0, RoundingMode.HALF_UP).toInt()
                val aM = (avgPace / MainActivity.MINUTE)
                val aS = (avgPace - (aM * MainActivity.MINUTE))
                var aSPrint = aS.toString()
                if (aS < 10) aSPrint = "0$aS"


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

    fun kmToMile() {
        val res = (dist / (MainActivity.MILEKM / MainActivity.KM))
            .toBigDecimal()
            .setScale(3, RoundingMode.HALF_UP)
        etSegId = 4
        etKm.setText("$res")
    }

    fun mileToKm() {
        val res = (dist * (MainActivity.MILEKM / MainActivity.KM))
            .toBigDecimal()
            .setScale(3, RoundingMode.HALF_UP)
        etSegId = 4
        etKm.setText("$res")
    }
}
