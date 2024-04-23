package com.example.weather.model.currentweather

data class CurrentWeatherResult(
    val main: String,
    val description: String,
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double
)
