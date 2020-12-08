package com.x5bart_soft.paceone

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import com.x5bart_soft.paceone.data.Person
import com.x5bart_soft.paceone.databinding.FragmentPersonBinding
import java.text.SimpleDateFormat
import java.util.*


class PersonFragment : Fragment() {

    private lateinit var binding: FragmentPersonBinding
    private lateinit var person: Person
    private lateinit var calendar: Calendar

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

        readObject()
        viewBehavior()


    }

    private fun viewBehavior() {
        binding.etBirthday.setOnClickListener { datePicker() }
        binding.etBirthday.setOnFocusChangeListener { _, _ -> if (binding.etBirthday.isFocused) datePicker() }
        binding.etHeight.setOnClickListener { numberPicker() }
        binding.etHeight.setOnFocusChangeListener { _, _ -> if (binding.etHeight.isFocused) numberPicker() }
    }

    private fun datePicker() {
        calendar = Calendar.getInstance()
        getDate()

        val datePickerDialog = DatePickerDialog(
            this.activity!!, { _: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                mYear = year
                mMonth = month
                mDay = dayOfMonth
                birthday()
            },
            mYear,
            mMonth,
            mDay
        )
        calendar.add(Calendar.YEAR, -100)
        datePickerDialog.datePicker.minDate = calendar.timeInMillis
        datePickerDialog.datePicker.maxDate = Date().time
        person.isBirthday = true
        datePickerDialog.show()
    }

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
        binding.etMaxHeartRate.text = maxHr.toString()
    }

    private fun hrZone() {
        binding.tvZone5.text = person.getZone(zone5, zone0)
        binding.tvZone4.text = person.getZone(zone4, zone5)
        binding.tvZone3.text = person.getZone(zone3, zone4)
        binding.tvZone2.text = person.getZone(zone2, zone3)
        binding.tvZone1.text = person.getZone(zone0, zone2)
    }

    private fun numberPicker() {
        val myNumberPicker = NumberPicker(this.activity)
        myNumberPicker.maxValue = 250
        myNumberPicker.minValue = 120
        myNumberPicker.value = height
        myNumberPicker.setOnValueChangedListener { _, _, newVal ->
            binding.etHeight.text = newVal.toString()
            height = newVal
        }
        val builder = AlertDialog.Builder(this.activity).setView(myNumberPicker)
        builder.setPositiveButton("ok") { _, _ ->
            person.height = height
            writeStrideLenght()

        }
        builder.setCancelable(false)
        builder.show()
    }

    private fun writeStrideLenght() {
        strideLength = person.getStrideLength()
        binding.etStrideLength.text = strideLength.toString()
    }

    private fun readObject() {
        height = person.height
        strideLength = person.strideLength
        mYear = person.mYear
        mMonth = person.mMonth
        mDay = person.mDay
        writeView()
    }

    private fun writeView() {
        if (height != 0) {
            binding.etHeight.text = height.toString()
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
        binding.etBirthday.text = date
        setMaxHr()
        hrZone()
    }
    
}

