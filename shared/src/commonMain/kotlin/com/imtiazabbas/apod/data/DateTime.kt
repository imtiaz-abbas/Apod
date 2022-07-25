package com.imtiazabbas.apod.data

import com.squareup.sqldelight.ColumnAdapter

expect fun dateTimeNow(): DateTime

expect class DateTime {
  constructor(millis: Long)
  constructor(string: String, isIso: Boolean = true)
  fun getTimeInMillis(): Long
  fun getReadableTime(): String
}

expect class DateTimeAdapter() : ColumnAdapter<DateTime, Long>
