package com.imtiazabbas.apod.db.data

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

object Db {
  private var driverRef: SqlDriver? = null
  private var dbRef: ApodDatabase? = null

  val ready: Boolean
    get() = driverRef != null

  fun dbSetup(context: Context) {
    val driver = AndroidSqliteDriver(Schema, context, "apod.db")
    val db = createQueryWrapper(driver)
    driverRef = driver
    dbRef = db
  }

  fun testSetup(driver: SqlDriver) {
    val db = createQueryWrapper(driver)
    driverRef = driver
    dbRef = db
  }

  internal fun dbClear() {
    driverRef!!.close()
    dbRef = null
    driverRef = null
  }

  val instance: ApodDatabase
    get() = dbRef!!
}
