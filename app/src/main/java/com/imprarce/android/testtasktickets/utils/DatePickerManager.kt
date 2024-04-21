package com.imprarce.android.testtasktickets.utils

import android.app.DatePickerDialog
import android.content.Context
import android.widget.Toast
import java.util.*

class DatePickerManager(private val context: Context) {

    fun showDatePickerDialog(onDateSelected: (Date) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)
                onDateSelected(selectedDate.time)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}
