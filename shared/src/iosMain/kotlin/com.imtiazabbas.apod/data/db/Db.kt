package com.imtiazabbas.apod.data.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze

object Db {
  private val driverRef = AtomicReference<SqlDriver?>(null)
  private val dbRef = AtomicReference<ApodDatabase?>(null)

  internal fun dbSetup(driver: SqlDriver) {
    val db = createQueryWrapper(driver)
    driverRef.value = driver.freeze()
    dbRef.value = db.freeze()
  }

  internal fun dbClear() {
    driverRef.value!!.close()
    dbRef.value = null
    driverRef.value = null
  }

  // Called from Swift
  @Suppress("unused")
  fun defaultDriver() {
    dbSetup(NativeSqliteDriver(Schema, "apod.db"))
  }

  val instance: ApodDatabase
    get() = dbRef.value!!
}
