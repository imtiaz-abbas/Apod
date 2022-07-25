package com.imtiazabbas.apod.Network

import com.imtiazabbas.apod.BuildKonfig
import com.imtiazabbas.apod.data.DateTime
import com.imtiazabbas.apod.data.dateTimeNow
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.ListSerializer

import kotlinx.serialization.json.Json
import kotlin.native.concurrent.SharedImmutable


@SharedImmutable
internal expect val ApplicationDispatcher: CoroutineDispatcher

class NetworkService {
  private val client = HttpClient()

  private val json = Json { ignoreUnknownKeys = true }

  fun fetchPictures(callback: (List<AstronomyPicture>) -> Unit) {
    GlobalScope.launch {
      val nowMillis = dateTimeNow().getTimeInMillis()
      val startMillis = nowMillis - 604800000L
      val startDate = DateTime(millis = startMillis)
//      val endMillis = nowMillis - 86400000L
//      val endDate = DateTime(millis = endMillis)
      val url = "${baseUrl}?api_key=${apiKey}&start_date=${startDate.getReadableTime()}"
      try {
        val response = client.get(url)
        val responseText = response.bodyAsText()
        val pictures = json.decodeFromString(ListSerializer(AstronomyPicture.serializer()), responseText)
        callback(pictures)
      } catch (err: Exception) {
        println("Got Error -> ${err.message}")
        callback(listOf())
      }
    }
  }

  companion object {
    val baseUrl = BuildKonfig.BASE_URL
    val apiKey = BuildKonfig.API_KEY
  }
}