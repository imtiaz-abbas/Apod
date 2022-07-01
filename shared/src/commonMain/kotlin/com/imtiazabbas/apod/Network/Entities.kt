package com.imtiazabbas.apod.Network

import kotlinx.serialization.Serializable

@Serializable
data class AstronomyPicture(
  var copyright: String? = null,
  var date: String,
  var explanation: String,
  var hdurl: String,
  var media_url: String? = null,
  var service_version: String? = null,
  var media_type: String? = null,
  var title: String,
  var url: String
)
