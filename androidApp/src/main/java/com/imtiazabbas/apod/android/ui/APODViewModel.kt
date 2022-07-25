package com.imtiazabbas.apod.android.ui

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imtiazabbas.apod.data.db.AstronomyPictureDao
import comimtiazabbasapoddatadb.AstronomyPicture
import kotlinx.coroutines.launch

class APODViewModel : ViewModel() {
  private var _picture = mutableStateOf<List<AstronomyPicture>>(listOf())
  var errorMessage: String by mutableStateOf("")
  val picture: List<AstronomyPicture>
    get() = _picture.value

  fun getAPOD() {
    viewModelScope.launch {
      Handler(Looper.getMainLooper()).postDelayed({
        _picture.value = AstronomyPictureDao().listPictures()
      }, 2000)
    }
  }
}