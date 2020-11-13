package com.x5bart_soft.paceone.split


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.x5bart_soft.paceone.databinding.DialogNegativSplitBinding

class NegativSplitDialog : DialogFragment() {

    lateinit var binding: DialogNegativSplitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding= DialogNegativSplitBinding.inflate(layoutInflater)
        return  binding.root
//        inflater.inflate(R.layout.dialog_negativ_split, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            dismiss()
        }
    }
}
