package com.example.weather.model.currentweather

import com.example.weather.model.Clouds
import com.example.weather.model.Coord
import com.example.weather.model.Rain
import com.example.weather.model.Weather

data class CurrentWeather(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: MainWeather,
    val name: String,
    val rain: Rain,
    val sys: SysWeather,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: WindWeather
)