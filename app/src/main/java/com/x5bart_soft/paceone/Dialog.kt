package com.x5bart_soft.paceone


import android.content.Context
import android.view.LayoutInflater
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog.*
import kotlinx.android.synthetic.main.dialog.btnDialog1

class Dialog : DialogFragment() {

    var btn1 = ""
    var btn2 = ""
    var btn1Flag = 0
    var btn2Flag = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog, null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val etID = Fragment1.etID
        val ctx:Context

        if (etID in 1..3) {
            btn1 = getString(R.string.time)
            btn1Flag = 2
            btn2 = getString(R.string.distance2)
            btn2Flag = 3
        }
        if (etID in 4..6) {
            btn1 = getString(R.string.speed)
            btn1Flag = 1
            btn2 = getString(R.string.distance2)
            btn2Flag = 3
        }
        if (etID == 7) {
            btn1 = getString(R.string.time)
            btn1Flag = 2
            btn2 = getString(R.string.speed)
            btn2Flag = 1
        }
        btnDialog1.text = "$btn1"
        btnDialog2.text = "$btn2"

        btnDialog1.setOnClickListener {
            Fragment1.flag = btn1Flag
            dismiss()
        }
        btnDialog2.setOnClickListener {
            Fragment1.flag = btn2Flag
            dismiss()
        }
    }
}


