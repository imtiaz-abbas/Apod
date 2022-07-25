package com.imtiazabbas.apod.android.utils

import android.content.Context
import com.imtiazabbas.apod.data.db.ApodDatabase
import com.imtiazabbas.apod.data.db.Db


fun Db.getInstance(contex: Context): ApodDatabase {
  if (!Db.ready) {
    Db.dbSetup(contex)
  }
  return Db.instance
}