package com.imtiazabbas.apod.data.db

import comimtiazabbasapoddatadb.AstronomyPicture

interface IAstronomyPictureDao {
  fun listPictures(): List<AstronomyPicture>
  fun mostRecentPicture(): AstronomyPicture?
  fun insertPictures(pictures: List<com.imtiazabbas.apod.Network.AstronomyPicture>)
}

expect class AstronomyPictureDao() : IAstronomyPictureDao