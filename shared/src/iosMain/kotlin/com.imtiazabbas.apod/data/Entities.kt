package com.imtiazabbas.apod.data

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSISO8601DateFormatWithFractionalSeconds
import platform.Foundation.NSISO8601DateFormatWithInternetDateTime
import platform.Foundation.NSISO8601DateFormatter


actual fun formatDatetime(d: DateTime): String {
  val dateTimeFormatter = NSISO8601DateFormatter()
  dateTimeFormatter.formatOptions =
    NSISO8601DateFormatWithFractionalSeconds or NSISO8601DateFormatWithInternetDateTime
  return dateTimeFormatter.stringFromDate(d.nsDate)
}

actual fun formatToReadableDatetime(d: DateTime): String {
  val dateTimeFormatter = NSDateFormatter()
  dateTimeFormatter.dateFormat = "yyyy-MM-dd"
  return dateTimeFormatter.stringFromDate(d.nsDate)
}

fun readableDateStringToNSDate(dateString: String): NSDate? {
  val dateTimeFormatter = NSDateFormatter()
  dateTimeFormatter.dateFormat = "yyyy-MM-dd"
  return dateTimeFormatter.dateFromString(dateString)
}