package com.imtiazabbas.apod.db.data

import platform.Foundation.NSISO8601DateFormatWithFractionalSeconds
import platform.Foundation.NSISO8601DateFormatWithInternetDateTime
import platform.Foundation.NSISO8601DateFormatter


actual fun formatDatetime(d: DateTime): String {
  val dateTimeFormatter = NSISO8601DateFormatter()
  dateTimeFormatter.formatOptions =
    NSISO8601DateFormatWithFractionalSeconds or NSISO8601DateFormatWithInternetDateTime
  return dateTimeFormatter.stringFromDate(d.nsDate)
}