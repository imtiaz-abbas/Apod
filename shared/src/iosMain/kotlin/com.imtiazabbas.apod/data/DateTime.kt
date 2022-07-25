package com.imtiazabbas.apod.data

import com.squareup.sqldelight.ColumnAdapter
import platform.Foundation.NSDate
import platform.Foundation.NSISO8601DateFormatWithFractionalSeconds
import platform.Foundation.NSISO8601DateFormatWithInternetDateTime
import platform.Foundation.NSISO8601DateFormatter
import platform.Foundation.date
import platform.Foundation.dateWithTimeIntervalSince1970
import platform.Foundation.timeIntervalSince1970
import kotlin.math.roundToLong

actual class DateTime constructor(val nsDate: NSDate) {
  actual constructor(string: String, isIso: Boolean) : this(partsToDate(string, isIso))
  actual constructor(millis: Long) : this(partsToDate(millis))

  override fun equals(other: Any?): Boolean {
    return if (other is DateTime) {
      formatDatetime(other) == formatDatetime(this)
    } else {
      false
    }
  }

  actual fun getTimeInMillis(): Long {
    return (this.nsDate.timeIntervalSince1970 * 1000).roundToLong()
  }

  actual fun getReadableTime(): String {
    return formatToReadableDatetime(this)
  }
}

internal fun partsToDate(string: String, isIso: Boolean): NSDate {
  return if (isIso) {
    val formatter = NSISO8601DateFormatter()
    formatter.formatOptions = NSISO8601DateFormatWithFractionalSeconds or NSISO8601DateFormatWithInternetDateTime
    formatter.dateFromString(string)!!
  } else {
    readableDateStringToNSDate(string)!!
  }
}

internal fun partsToDate(millis: Long): NSDate {
  return NSDate.dateWithTimeIntervalSince1970((millis / 1000).toDouble())
}

actual class DateTimeAdapter actual constructor() : ColumnAdapter<DateTime, Long> {
  override fun decode(databaseValue: Long): DateTime =
    DateTime(NSDate.dateWithTimeIntervalSince1970(databaseValue.toDouble() / 1000))

  override fun encode(value: DateTime): Long =
    (value.nsDate.timeIntervalSince1970 * 1000).roundToLong()
}

actual fun dateTimeNow(): DateTime {
  return DateTime(NSDate.date())
}
