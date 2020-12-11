package com.x5bart_soft.paceone

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import com.x5bart_soft.paceone.data.Person
import com.x5bart_soft.paceone.databinding.FragmentPersonBinding
import com.x5bart_soft.paceone.dialogs.HelpDialog
import com.x5bart_soft.paceone.utils.AdsUtils
import java.text.SimpleDateFormat
import java.util.*


class PersonFragment : Fragment() {

    private lateinit var binding: FragmentPersonBinding
    private lateinit var person: Person
    private lateinit var calendar: Calendar
    private lateinit var adsUtils: AdsUtils


    private var height = 0
    private var strideLength = 0
    private var mYear = 0
    private var mMonth = 0
    private var mDay = 0
    private val zone5 = 0.87
    private val zone4 = 0.82
    private val zone3 = 0.77
    private val zone2 = 0.72
    private val zone0 = 0.0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        person = Person
        adsUtils = AdsUtils()

        adsUtils.showAds(binding.personBannerView, adsUtils.blockIdPerson)
        readObject()
        viewBehavior()


    }

    private fun viewBehavior() {
        binding.personEtBirthday.setOnClickListener { datePicker() }
        binding.personEtBirthday.setOnFocusChangeListener { _, _ -> if (binding.personEtBirthday.isFocused) datePicker() }
        binding.personEtHeight.setOnClickListener { numberPicker() }
        binding.personEtHeight.setOnFocusChangeListener { _, _ -> if (binding.personEtHeight.isFocused) numberPicker() }

        binding.personBtnZone1.setOnClickListener {
            val dialog = HelpDialog(
                R.string.zone_title_1,
                R.string.zone_text_1,
                R.string.copyrate_suunto_com
            )
            dialog.show(fragmentManager!!, "zone1")
        }
        binding.personBtnZone2.setOnClickListener {
            val dialog = HelpDialog(
                R.string.zone_title_2,
                R.string.zone_text_2,
                R.string.copyrate_suunto_com
            )
            dialog.show(fragmentManager!!, "zone2")
        }
        binding.personBtnZone3.setOnClickListener {
            val dialog = HelpDialog(
                R.string.zone_title_3,
                R.string.zone_text_3,
                R.string.copyrate_suunto_com
            )
            dialog.show(fragmentManager!!, "zone1")
        }
        binding.personBtnZone4.setOnClickListener {
            val dialog = HelpDialog(
                R.string.zone_title_4,
                R.string.zone_text_4,
                R.string.copyrate_suunto_com
            )
            dialog.show(fragmentManager!!, "zone1")
        }
        binding.personBtnZone5.setOnClickListener {
            val dialog = HelpDialog(
                R.string.zone_title_5,
                R.string.zone_text_5,
                R.string.copyrate_suunto_com
            )
            dialog.show(fragmentManager!!, "zone1")
        }
    }

    private fun datePicker() {
        calendar = Calendar.getInstance()
        getDate()

        val dialog = Dialog(this.activity!!)
        dialog.setContentView(R.layout.picker_date)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)


        val datePicker = dialog.findViewById<DatePicker>(R.id.date_picker)
        datePicker.init(
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        ) { _, year, month, dayOfMonth ->
            mYear = year
            mMonth = month
            mDay = dayOfMonth
        }
//        datePicker.init(mYear, mMonth, mDay)
//            .setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
//            mYear = year
//            mMonth = monthOfYear
//            mDay = dayOfMonth
//        }
//        DatePickerDialog(
//            this.activity!!, { _: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->

//            },
//            mYear,
//            mMonth,
//            mDay
//        )


        val button = dialog.findViewById<Button>(R.id.btn_dpOk)
        button.setOnClickListener {
            birthday()
            dialog.dismiss()
        }

        calendar.add(Calendar.YEAR, -100)
        datePicker.minDate = calendar.timeInMillis
        datePicker.maxDate = Date().time
        datePicker.updateDate(mYear, mMonth, mDay)
        person.isBirthday = true
        dialog.show()
    }

//    private fun datePicker() {
//        calendar = Calendar.getInstance()
//        getDate()
//
//        val datePickerDialog = DatePickerDialog(
//            this.activity!!, { _: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
//                mYear = year
//                mMonth = month
//                mDay = dayOfMonth
//                birthday()
//            },
//            mYear,
//            mMonth,
//            mDay
//        )
//        calendar.add(Calendar.YEAR, -100)
//        datePickerDialog.datePicker.minDate = calendar.timeInMillis
//        datePickerDialog.datePicker.maxDate = Date().time
//        datePickerDialog.setContentView(R.layout.date_picker)
//        person.isBirthday = true
//        datePickerDialog.show()
//    }


    private fun getDate() {
        mDay = person.getDay()
        mMonth = person.getMonth()
        mYear = person.getYear()

    }

    private fun setDate() {
        person.setDay(mDay)
        person.setMonth(mMonth)
        person.setYear(mYear)
    }

    private fun setMaxHr() {
        val maxHr = person.getMaxHr()
        binding.personEtMaxHR.text = maxHr.toString()
    }

    private fun hrZone() {
        binding.personBtnZone5.text = person.getZone(zone5, zone0)
        binding.personBtnZone4.text = person.getZone(zone4, zone5)
        binding.personBtnZone3.text = person.getZone(zone3, zone4)
        binding.personBtnZone2.text = person.getZone(zone2, zone3)
        binding.personBtnZone1.text = person.getZone(zone0, zone2)
    }

    private fun numberPicker() {

        val dialog = Dialog(this.activity!!)
        dialog.setContentView(R.layout.picker_height)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)

        val numberPicker = dialog.findViewById<NumberPicker>(R.id.numberPicker)
        numberPicker.maxValue = 250
        numberPicker.minValue = 120
        if (height == 0) height = 160
        numberPicker.value = height
        numberPicker.setOnValueChangedListener { _, _, newVal ->
//            binding.etHeight.text = newVal.toString()
            height = newVal
        }

        val button = dialog.findViewById<Button>(R.id.btn_npOk)
        button.setOnClickListener {
            binding.personEtHeight.text = height.toString()
            person.setHeight(height)
            writeStrideLenght()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun writeStrideLenght() {
        strideLength = person.getStrideLength()
        binding.personEtStrideLength.text = strideLength.toString()
    }

    private fun readObject() {
        height = person.getHeight()
        strideLength = person.getStrideLength()
        mYear = person.getYear()
        mMonth = person.getMonth()
        mDay = person.getDay()
        writeView()
    }

    private fun writeView() {
        if (height != 0) {
            binding.personEtHeight.text = height.toString()
            writeStrideLenght()
        }
        if (person.isBirthday) birthday()

    }

    @SuppressLint("SimpleDateFormat", "WeekBasedYear")
    private fun birthday() {
        val selectedDate = Calendar.getInstance()
        val format = SimpleDateFormat("dd.MM.YYYY")
        selectedDate.set(Calendar.YEAR, mYear)
        selectedDate.set(Calendar.MONTH, mMonth)
        selectedDate.set(Calendar.DAY_OF_MONTH, mDay)
        setDate()
        val date = format.format(selectedDate.time)
        binding.personEtBirthday.text = date
        setMaxHr()
        hrZone()
    }

}

