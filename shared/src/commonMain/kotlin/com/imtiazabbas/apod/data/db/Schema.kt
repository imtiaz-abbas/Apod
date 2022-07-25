package com.imtiazabbas.apod.data.db

import com.imtiazabbas.apod.data.DateTimeAdapter
import com.squareup.sqldelight.db.SqlDriver
import comimtiazabbasapoddatadb.AstronomyPicture

fun createQueryWrapper(driver: SqlDriver): ApodDatabase {
  return ApodDatabase(
    driver = driver,
    astronomyPictureAdapter = AstronomyPicture.Adapter(
      dateAdapter = DateTimeAdapter()
    )
  )
}

object  Schema: SqlDriver.Schema by ApodDatabase.Schema {
  override fun create(driver: SqlDriver) {
    ApodDatabase.Schema.create(driver)
    createQueryWrapper(driver).apply {

    }
  }
}

