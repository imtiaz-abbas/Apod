package com.imtiazabbas.apod.db.data

import java.time.format.DateTimeFormatter

actual fun formatDatetime(d: DateTime): String {
  return DateTimeFormatter.ISO_INSTANT.format(d.instant)
}