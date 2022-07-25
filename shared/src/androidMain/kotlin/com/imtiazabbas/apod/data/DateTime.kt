package com.imtiazabbas.apod.data

import com.squareup.sqldelight.ColumnAdapter
import java.time.Instant
import java.time.format.DateTimeFormatter

actual class DateTime internal constructor(internal val instant: Instant) {
  actual constructor(string: String, isIso: Boolean) : this(partsToDate(string, isIso))
  actual constructor(millis: Long) : this(partsToDate(millis))

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

  actual fun getReadableTime(): String {
    return formatToReadableDatetime(this)
  }
}

internal fun partsToDate(string: String, isIso: Boolean): Instant {
  return if (isIso) {
    Instant.from(DateTimeFormatter.ISO_INSTANT.parse(string))
  } else {
    readableDateStringToInstant(string)
  }
}
internal fun partsToDate(millis: Long): Instant {
  return Instant.ofEpochMilli(millis)
}

actual class DateTimeAdapter actual constructor() : ColumnAdapter<DateTime, Long> {
  override fun encode(value: DateTime) = value.instant.toEpochMilli()
  override fun decode(databaseValue: Long) =
    DateTime(Instant.ofEpochMilli(databaseValue))
}

actual fun dateTimeNow(): DateTime {
  return DateTime.now()
}
