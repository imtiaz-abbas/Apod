package com.imtiazabbas.apod.data

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

actual fun formatDatetime(d: DateTime): String {
  return DateTimeFormatter.ISO_INSTANT.format(d.instant)
}

actual fun formatToReadableDatetime(d: DateTime): String {
  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
  return formatter.format(d.instant.atZone(ZoneId.systemDefault()))
}

fun readableDateStringToInstant(dateString: String): Instant {
  val formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd")
  val date = LocalDate.parse(dateString, formatter).atTime(0,0,0,0)
  return date.toInstant(ZoneOffset.UTC)
}