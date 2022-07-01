package com.imtiazabbas.apod.Network

import com.imtiazabbas.apod.BuildKonfig
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import kotlinx.serialization.json.Json
import kotlin.native.concurrent.SharedImmutable


@SharedImmutable
internal expect val ApplicationDispatcher: CoroutineDispatcher

class NetworkService {
  private val client = HttpClient()

  private val json = Json { ignoreUnknownKeys = true }

  fun fetchTodayAPOD(callback: (AstronomyPicture?) -> Unit) {
    GlobalScope.launch(ApplicationDispatcher) {
      val response = client.get("${baseUrl}?api_key=${apiKey}")
      val responseText = response.bodyAsText()
      val picture = json.decodeFromString(AstronomyPicture.serializer(), responseText)
      callback(picture)
    }
  }

  companion object {
    val baseUrl = BuildKonfig.BASE_URL
    val apiKey = BuildKonfig.API_KEY
  }
}