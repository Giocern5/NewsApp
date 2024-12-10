package com.example.reachmobi.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun formatDate(dateString: String): String {
    val dateTime = ZonedDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME)

    return dateTime.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"))
}