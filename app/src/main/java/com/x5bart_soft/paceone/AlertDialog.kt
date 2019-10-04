package com.x5bart_soft.paceone


import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_alert.*


class AlertDialog : DialogFragment() {

    var btn1 = ""
    var btn2 = ""
    var btn1Flag = 0
    var btn2Flag = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.dialog_alert, null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        val etID = Fragment1.etID

        if (etID in 1..3) {
            btn1 = getString(R.string.timeBtn)
            btn1Flag = 2
            btn2 = getString(R.string.distanceBtn)
            btn2Flag = 3
        }
        if (etID in 4..6) {
            btn1 = getString(R.string.speedBtn)
            btn1Flag = 1
            btn2 = getString(R.string.distanceBtn)
            btn2Flag = 3
        }
        if (etID == 7) {
            btn1 = getString(R.string.timeBtn)
            btn1Flag = 2
            btn2 = getString(R.string.speedBtn)
            btn2Flag = 1
        }
        btnDialog1.text = "$btn1"
        btnDialog2.text = "$btn2"

        btnDialog1.setOnClickListener {
            Fragment1.flag = btn1Flag
            dismiss()
            showKey()

        }
        btnDialog2.setOnClickListener {
            Fragment1.flag = btn2Flag
            dismiss()
            showKey()
        }
    }




    private fun showKey() {
        val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
}


