package com.imtiazabbas.apod.data.db

import com.imtiazabbas.apod.data.DateTime
import com.imtiazabbas.apod.data.dateTimeNow
import comimtiazabbasapoddatadb.AstronomyPicture
import comimtiazabbasapoddatadb.AstronomyPictureQueries
import kotlin.native.concurrent.freeze

actual class AstronomyPictureDao : BaseAstronomyPictureDao(), IAstronomyPictureDao {
  private val pictureQueries by lazy {
    Db.instance.astronomyPictureQueries
  }

  @Throws(Exception::class)
  fun create(hdUrl: String, title: String, explanation: String, mediaType: String) {
    pictureQueries.insertOrReplacePicture(dateTimeNow(), hdUrl, title, explanation, mediaType)
  }

  override fun fetchPictureQueries(): AstronomyPictureQueries {
    return pictureQueries
  }

  override fun listPictures(): List<AstronomyPicture> {
    return pictureQueries.listPictures().executeAsList().freeze()
  }

  override fun mostRecentPicture(): AstronomyPicture? {
    return pictureQueries.mostRecentPicture().executeAsOneOrNull().freeze()
  }

  override fun insertPictures(pictures: List<com.imtiazabbas.apod.Network.AstronomyPicture>) {
    pictureQueries.transaction {
      pictures.forEach {
        pictureQueries.insertOrReplacePicture(DateTime(it.date, false), it.url, it.title, it.explanation, it.media_type)
      }
    }
  }
}