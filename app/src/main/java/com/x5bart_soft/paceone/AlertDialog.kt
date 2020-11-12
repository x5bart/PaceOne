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

    lateinit var function: PaceFunction

    private var btn1 = ""
    private var btn2 = ""
    private var btn1Flag = ""
    private var btn2Flag = ""
    var REQUEST_CODE = 0
    var FLAG = ""
    lateinit var binding: DialogAlertBinding
    lateinit var paceObject: Pace


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
        function = PaceFunction()
        dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        paceObject = Pace

        val etID = paceObject.etID


        if (etID == "paceM" || etID == "paceS" || etID == "speed") {
            btn1 = getString(R.string.timeBtn)
            btn1Flag = "time"
            btn2 = getString(R.string.distanceBtn)
            btn2Flag = "dist"
        }
        if (etID == "timeH" || etID == "timeM" || etID == "timeS") {
            btn1 = getString(R.string.speedBtn)
            btn1Flag = "speed"
            btn2 = getString(R.string.distanceBtn)
            btn2Flag = "dist"
        }
        if (etID == "dist") {
            btn1 = getString(R.string.timeBtn)
            btn1Flag = "time"
            btn2 = getString(R.string.speedBtn)
            btn2Flag = "speed"
        }
        binding.btnDialog1.text = "$btn1"
        binding.btnDialog2.text = "$btn2"

        binding.btnDialog1.setOnClickListener {
            when (btn1Flag) {
                "speed" -> {
                    paceObject.activeFunction = btn1Flag
                    function.speed()
                    result()
                    dismiss()
                    showKey()
                }
                "time" -> {
                    paceObject.activeFunction = btn1Flag
                    function.time()
                    result()
                    dismiss()
                    showKey()
                }
                         }

        }
        binding.btnDialog2.setOnClickListener {
            when (btn2Flag) {
                "speed" -> {
                    paceObject.activeFunction = btn2Flag
                    function.speed()
                    result()
                    dismiss()
                    showKey()
                }

                "dist" -> {
                    paceObject.activeFunction = btn2Flag
                    function.distance()
                    result()
                    dismiss()
                    showKey()
                }
            }
        }
    }

    private fun result(){
        val intent = Intent()
        targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
    }
    private fun showKey() {
        val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
}


