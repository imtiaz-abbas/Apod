package com.imtiazabbas.apod.db.data

import com.squareup.sqldelight.ColumnAdapter
import java.time.Instant
import java.time.format.DateTimeFormatter

actual class DateTime internal constructor(internal val instant: Instant) {
  actual constructor(isoString: String) : this(partsToDate(isoString))

  override fun equals(other: Any?): Boolean {
    return if (other is DateTime) {
      formatDatetime(other) == formatDatetime(this)
    } else {
      false
    }
  }

  companion object {
    fun now(): DateTime {
      return DateTime(Instant.now())
    }
  }

  actual fun getTimeInMillis(): Long {
    return this.instant.toEpochMilli()
  }
}

internal fun partsToDate(isoString: String): Instant {
  return Instant.from(DateTimeFormatter.ISO_INSTANT.parse(isoString))
}

actual class DateTimeAdapter actual constructor() : ColumnAdapter<DateTime, Long> {
  override fun encode(value: DateTime) = value.instant.toEpochMilli()
  override fun decode(databaseValue: Long) = DateTime(Instant.ofEpochMilli(databaseValue))
}

actual fun dateTimeNow(): DateTime {
  return DateTime.now()
}
