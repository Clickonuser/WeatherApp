package com.example.weather.repository

import com.example.weather.model.Coord
import com.example.weather.model.currentweather.CurrentWeatherResult
import com.example.weather.model.forecast.City
import com.example.weather.model.forecast.Forecast
import com.example.weather.network.WeatherApi
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getCoordinates(city: String): Coord {
        val response = weatherApi.getGeocoding(city, LIMIT, APP_ID)
        if(response.isSuccessful && !(response.body().isNullOrEmpty())) {
            val locationResult = response.body()
            val lat = locationResult?.first()?.lat
            val lon = locationResult?.first()?.lon
            if(lat != null && lon != null) {
                return Coord(lat, lon)
            }
        }
        return Coord(0.0, 0.0)
    }

    override suspend fun getCurrentWeather(lat: Double, lon: Double): CurrentWeatherResult {
        val response = weatherApi.getCurrentWeather(lat, lon, APP_ID, METRIC)
        if(response.isSuccessful) {
            val weatherResult = response.body()
            return CurrentWeatherResult(
                main = weatherResult?.weather?.first()?.main ?: "",
                description = weatherResult?.weather?.first()?.description ?: "",
                temp = weatherResult?.main?.temp ?: 0.0,
                pressure = weatherResult?.main?.pressure ?: 0,
                humidity = weatherResult?.main?.humidity ?: 0,
                windSpeed = weatherResult?.wind?.speed ?: 0.0
            )
        }
        return CurrentWeatherResult(
            main = "",
            description = "",
            temp = 0.0,
            pressure = 0,
            humidity = 0,
            windSpeed = 0.0
        )
    }

    override suspend fun getForecast(lat: Double, lon: Double): Forecast {
        val response = weatherApi.getForecast(lat, lon, APP_ID, METRIC)
        if(response.isSuccessful) {
            val forecastResult = response.body()
            return Forecast(
                city = forecastResult?.city ?: City(Coord(0.0,0.0), "", 0, "", 0, 0, 0, 0),
                cnt = forecastResult?.cnt ?: 0,
                list = forecastResult?.list ?: listOf(),
                cod = forecastResult?.cod ?: "",
                message = forecastResult?.message ?: 0
            )
        }
        return Forecast(
            city = City(Coord(0.0, 0.0), "", 0, "", 0, 0, 0, 0),
            cnt = 0,
            list = listOf(),
            cod = "",
            message = 0
        )
    }

    companion object {
        const val LIMIT = "1"
        const val APP_ID = "0e11c960184bb342907b1b942930e9c8"
        const val METRIC = "metric"
        const val WEATHER_TYPE_CLEAR = "Clear"
        const val WEATHER_TYPE_RAIN = "Rain"
        const val WEATHER_TYPE_SNOW = "Snow"
        const val WEATHER_TYPE_THUNDERSTORM = "Thunderstorm"
        const val WEATHER_TYPE_CLOUDS = "Clouds"
    }
}