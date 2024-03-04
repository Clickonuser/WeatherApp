package com.example.weather.model.forecast

data class FiveDayForecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Forecast>,
    val message: Int
)