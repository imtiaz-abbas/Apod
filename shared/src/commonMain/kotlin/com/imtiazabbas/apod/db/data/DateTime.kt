package com.imtiazabbas.apod.db.data

import com.squareup.sqldelight.ColumnAdapter

expect fun dateTimeNow(): DateTime

expect class DateTime(isoString: String) {
  fun getTimeInMillis(): Long
}

expect class DateTimeAdapter() : ColumnAdapter<DateTime, Long>
