package com.example.weather.model.currentweather

data class SysWeather(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)