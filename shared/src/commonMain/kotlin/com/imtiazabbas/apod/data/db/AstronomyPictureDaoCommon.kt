package com.imtiazabbas.apod.data.db

import comimtiazabbasapoddatadb.AstronomyPictureQueries

abstract class BaseAstronomyPictureDao() : IAstronomyPictureDao {
  abstract fun fetchPictureQueries(): AstronomyPictureQueries
}
