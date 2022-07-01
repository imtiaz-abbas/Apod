package com.imtiazabbas.apod.android

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imtiazabbas.apod.Network.AstronomyPicture
import com.imtiazabbas.apod.Network.NetworkService
import kotlinx.coroutines.launch

class APODViewModel : ViewModel() {
  private var _picture = mutableStateOf<AstronomyPicture>(AstronomyPicture("", "", "", "", "", "", "", "", ""))
  var errorMessage: String by mutableStateOf("")
  val picture: AstronomyPicture
    get() = _picture.value

  fun getAPOD() {
    viewModelScope.launch {
      NetworkService().fetchTodayAPOD {
        if (it != null) {
          _picture.value = it
        }
      }
    }
  }
}