package com.example.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.Coord
import com.example.weather.model.currentweather.CurrentWeatherResult
import com.example.weather.model.forecast.Forecast
import com.example.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val coordinates: MutableLiveData<Coord> = MutableLiveData()
    val coordinatesResult: LiveData<Coord> = coordinates

    private val currentWeather: MutableLiveData<CurrentWeatherResult> = MutableLiveData()
    val currentWeatherResult: LiveData<CurrentWeatherResult> = currentWeather

    private val forecast: MutableLiveData<Forecast> = MutableLiveData()
    val forecastResult: LiveData<Forecast> = forecast

    suspend fun getCoordinates(city: String) {
        withContext(Dispatchers.IO) {
            val coordinatesResult = weatherRepository.getCoordinates(city)
            coordinates.postValue(coordinatesResult)
        }
    }

    suspend fun getCurrentWeather(lat: Double, lon: Double) {
        withContext(Dispatchers.IO) {
            val currentWeatherResult = weatherRepository.getCurrentWeather(lat, lon)
            currentWeather.postValue(currentWeatherResult)
        }
    }

    suspend fun getForecast(lat: Double, lon: Double) {
        withContext(Dispatchers.IO) {
            val forecastResult = weatherRepository.getForecast(lat, lon)
            forecast.postValue(forecastResult)
        }
    }
}