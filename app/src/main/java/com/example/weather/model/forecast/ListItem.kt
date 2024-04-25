package com.example.weather.model.forecast

import com.example.weather.model.Clouds
import com.example.weather.model.Rain
import com.example.weather.model.Weather

data class ListItem(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: MainForecast,
    val pop: Double,
    val rain: Rain,
    val sys: SysForecast,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: WindForecast
)