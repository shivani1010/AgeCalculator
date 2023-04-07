package com.tutrials.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
   private var tv_selected_date : TextView? = null
    private var tv_age_in_minute : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_date_picker:Button=findViewById(R.id.btn_date_picker)
          tv_selected_date=findViewById(R.id.tv_selected_birth_date)
          tv_age_in_minute=findViewById(R.id.tv_age_in_minutes)


        btn_date_picker.setOnClickListener{
           // Toast.makeText(this,"Clicked Date",Toast.LENGTH_LONG).show()

            clickDatePicker();
        }
    }

    fun clickDatePicker()
    {
        val myCalendar=Calendar.getInstance();
        var year=myCalendar.get(Calendar.YEAR)
        var month=myCalendar.get(Calendar.MONTH)
        var day=myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd=DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
            _,selectedYear,selectedMonth,selectedDayOfMonth->

            val selected_date="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear";

            tv_selected_date?.text = selected_date

            val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate= sdf.parse(selected_date)


            val selectedDateInMinutes=theDate.time/60000;
            val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes=currentDate.time/60000;
            val differenceInTime=currentDateInMinutes - selectedDateInMinutes
            tv_age_in_minute?.text= differenceInTime.toString();


        },year,month,day)
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()
    }
}