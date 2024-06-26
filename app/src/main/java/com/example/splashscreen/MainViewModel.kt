package com.example.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class MainViewModel : ViewModel() {

    private val _text = MutableStateFlow(TimeOfDay.MORNING)
    val text get() = _text.asStateFlow()

    init {
        viewModelScope.launch {
            val currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            _text.value = when {
                (currentTime in 6 until 12) -> TimeOfDay.MORNING
                (currentTime in 12 until 18) -> TimeOfDay.DAY
                (currentTime in 18 until 23) -> TimeOfDay.EVENING
                else -> TimeOfDay.NIGHT
            }
        }
    }

}