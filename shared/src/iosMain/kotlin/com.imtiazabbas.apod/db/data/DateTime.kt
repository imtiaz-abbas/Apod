package com.imtiazabbas.apod.db.data

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
  actual constructor(isoString: String) : this(partsToDate(isoString))

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
}

internal fun partsToDate(isoString: String): NSDate {
  val formatter = NSISO8601DateFormatter()
  formatter.formatOptions = NSISO8601DateFormatWithFractionalSeconds or NSISO8601DateFormatWithInternetDateTime
  return formatter.dateFromString(isoString)!!
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
