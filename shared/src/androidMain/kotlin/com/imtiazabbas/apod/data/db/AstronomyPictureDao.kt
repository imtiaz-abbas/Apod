package com.imtiazabbas.apod.data.db

import com.imtiazabbas.apod.data.DateTime
import com.imtiazabbas.apod.data.dateTimeNow
import comimtiazabbasapoddatadb.AstronomyPicture
import comimtiazabbasapoddatadb.AstronomyPictureQueries

actual class AstronomyPictureDao : BaseAstronomyPictureDao(), IAstronomyPictureDao {
  private val pictureQueries by lazy {
    Db.instance.astronomyPictureQueries
  }

  override fun fetchPictureQueries(): AstronomyPictureQueries {
    return pictureQueries
  }

  @Throws(Exception::class)
  fun create(hdUrl: String, title: String, explanation: String, mediaType: String) {
    pictureQueries.insertOrReplacePicture(DateTime.now(), hdUrl, title, explanation, mediaType)
  }

  @Throws(Exception::class)
  override fun listPictures(): List<AstronomyPicture> {
    return pictureQueries.listPictures().executeAsList()
  }

  override fun mostRecentPicture(): AstronomyPicture? {
    return pictureQueries.mostRecentPicture().executeAsOneOrNull()
  }

  override fun insertPictures(pictures: List<com.imtiazabbas.apod.Network.AstronomyPicture>) {
    pictureQueries.transaction {
      pictures.forEach {
        pictureQueries.insertOrReplacePicture(DateTime(it.date, false), it.url, it.title, it.explanation, it.media_type)
      }
    }
  }
}