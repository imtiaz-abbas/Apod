package com.imtiazabbas.apod.android

import android.app.Application
import android.util.Log
import com.imtiazabbas.apod.Network.NetworkService
import com.imtiazabbas.apod.android.utils.getInstance
import com.imtiazabbas.apod.data.db.Db
import com.imtiazabbas.apod.sync.Syncer


class MainApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    Log.d("MainApplication", "Initializing Database")
    // initialize db
    Db.getInstance(this)

    // initialize Network Service
    val networkService = NetworkService()


    println("Syncer: Sync Starting")
    Syncer.syncNow(networkService = networkService) {
      println("Syncer: Sync Complete")
    }
  }
}
