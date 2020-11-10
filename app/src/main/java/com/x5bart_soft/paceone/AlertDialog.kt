package com.x5bart_soft.paceone


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import com.x5bart_soft.paceone.databinding.DialogAlertBinding


class AlertDialog : DialogFragment() {

    private var btn1 = ""
    private var btn2 = ""
    private var btn1Flag = 0
    private var btn2Flag = 0
    var REQUEST_CODE = 0
    var FLAG = 0
    lateinit var binding: DialogAlertBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setCanceledOnTouchOutside(false)
        binding = DialogAlertBinding.inflate(layoutInflater)
        return binding.root
//        inflater.inflate(R.layout.dialog_alert, null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        val etID = Fragment1.etID

        REQUEST_CODE = etID

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
        binding.btnDialog1.text = "$btn1"
        binding.btnDialog2.text = "$btn2"

        binding.btnDialog1.setOnClickListener {
            FLAG = btn1Flag
            val intent = Intent()
            intent.putExtra("1", REQUEST_CODE)
            intent.putExtra("2", FLAG)
            targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
            dismiss()
            showKey()
        }
        binding.btnDialog2.setOnClickListener {
            FLAG = btn2Flag
            val intent = Intent()
            intent.putExtra("1", REQUEST_CODE)
            intent.putExtra("2", FLAG)
            targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
            dismiss()
            showKey()
        }
    }

    fun showKey() {
        val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
}


