package com.example.fabcompose.utils

import java.text.SimpleDateFormat
import java.util.*

class Utilities {
    companion object {
        fun getCurrentDate(): String {
            val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+05:30");
            return simpleDateFormat.format(Date())
        }
    }
}