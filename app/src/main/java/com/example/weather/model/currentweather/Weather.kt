package com.example.weather.model.currentweather

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)