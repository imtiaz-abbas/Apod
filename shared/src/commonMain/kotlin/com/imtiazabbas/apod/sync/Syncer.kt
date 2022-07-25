package com.imtiazabbas.apod.sync

import com.imtiazabbas.apod.Network.NetworkService
import com.imtiazabbas.apod.data.DateTime
import com.imtiazabbas.apod.data.db.AstronomyPictureDao
import com.imtiazabbas.apod.data.dateTimeNow

object Syncer {
  fun syncNow(networkService: NetworkService, callback: () -> Unit) {
    val pictureDao = AstronomyPictureDao()
    val mostRecentPictureInDb = pictureDao.mostRecentPicture()
    if (mostRecentPictureInDb?.date != null) {
      if (dateTimeNow().getReadableTime() == mostRecentPictureInDb.date.getReadableTime()) {
        // already up to date do nothing
        println("Syncer: Already Up to date - NO OP")
        callback()
      } else {
        println("Syncer: Sync In Progress")
        this.fetchPictures(networkService, pictureDao, callback)
      }
    } else {
      println("Syncer: INITIAL SYNC")
      this.fetchPictures(networkService,pictureDao, callback)
    }
  }

  private fun fetchPictures(networkService: NetworkService, pictureDao: AstronomyPictureDao, callback: () -> Unit) {
    networkService.fetchPictures {
      pictureDao.insertPictures(it)
      callback()
    }
  }
}