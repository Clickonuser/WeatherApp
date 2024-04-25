package com.example.weather.repository

import com.example.weather.model.Coord
import com.example.weather.model.currentweather.CurrentWeatherResult
import com.example.weather.model.forecast.Forecast

/**
 * Provides API connection with https://openweathermap.org/
 */
interface WeatherRepository {

    /**
     * Getting location info like lat and lon
     */
    suspend fun getCoordinates(city: String): Coord

    /**
     * Getting current weather for specific place by provide lat and lon
     */
    suspend fun getCurrentWeather(lat: Double, lon: Double): CurrentWeatherResult

    /**
     * Getting forecast for specific place by provide lat and lon
     */
    suspend fun getForecast(lat: Double, lon: Double): Forecast

}