package com.imprarce.android.testtasktickets.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    private val dateFormat = SimpleDateFormat("dd MMM, EEE", Locale("ru"))
    private val dateFormatForBundle = SimpleDateFormat("dd MMMM", Locale("ru"))

    fun formatCurrentDate(): String {
        val currentDate = Calendar.getInstance().time
        return dateFormat.format(currentDate)
    }

    fun formatDate(date: Date): String {
        return dateFormat.format(date)
    }

    fun formatDateForBundle(date: String): String{
        val notFormattedDate = dateFormatForBundle.parse(date)
        return dateFormatForBundle.format(notFormattedDate)
    }
}
