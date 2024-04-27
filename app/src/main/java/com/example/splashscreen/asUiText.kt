package com.example.splashscreen

fun TimeOfDay.asUiText(): UiText {
    return when(this) {
        TimeOfDay.MORNING -> UiText.StringResource(R.string.morning)
        TimeOfDay.DAY -> UiText.StringResource(R.string.day)
        TimeOfDay.EVENING -> UiText.StringResource(R.string.evening)
        TimeOfDay.NIGHT -> UiText.StringResource(R.string.night)
    }
}