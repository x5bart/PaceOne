package com.x5bart_soft.paceone.dialogs


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.x5bart_soft.paceone.databinding.DialogHelpBinding

class HelpDialog(private var title: Int, private var text: Int, private var copyrate: Int) :
    DialogFragment() {

    lateinit var binding: DialogHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = DialogHelpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleAbout.text = getString(title)
        binding.tvAbout.text = getString(text)
        binding.tvCopyrate.text = getString(copyrate)
        binding.btnBack.setOnClickListener {
            dismiss()
        }
    }
}
