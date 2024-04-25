package com.example.weather

import com.example.weather.model.Clouds
import com.example.weather.model.Coord
import com.example.weather.model.Rain
import com.example.weather.model.Weather
import com.example.weather.model.currentweather.*
import com.example.weather.model.forecast.*
import com.example.weather.model.geocoding.Geocoding
import com.example.weather.model.geocoding.GeocodingItem
import com.example.weather.network.WeatherApi
import com.example.weather.repository.WeatherRepositoryImpl
import com.example.weather.repository.WeatherRepositoryImpl.Companion.APP_ID
import com.example.weather.repository.WeatherRepositoryImpl.Companion.LIMIT
import com.example.weather.repository.WeatherRepositoryImpl.Companion.METRIC
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response

class WeatherRepositoryImplTest {

    private val weatherApiMock: WeatherApi = mock()
    private lateinit var subject: WeatherRepositoryImpl

    private val lat = 1.0
    private val lon = 1.0

    @Before
    fun setup() {
        subject = WeatherRepositoryImpl(weatherApiMock)
    }

    @Test
    fun `getCoordinates success response`(): Unit = runBlocking {
        //Setup
        val city = "Oslo"
        val expectedCoordinates = Coord(lat, lon)
        val location = Geocoding()
        location.add(GeocodingItem("test", lat = lat, null, lon = lon, "test", "test"))
        val mockResponse = Response.success(location)
        `when`(weatherApiMock.getGeocoding(city, LIMIT, APP_ID)).thenReturn(mockResponse)

        //Call
        val result = subject.getCoordinates(city)

        //Verification
        assertEquals(expectedCoordinates.lat, result.lat)
        assertEquals(expectedCoordinates.lon, result.lon)
    }

    @Test
    fun `getCurrentWeather success response`(): Unit = runBlocking {
        //Setup
        val currentWeather = CurrentWeather(
            base = "test",
            clouds = Clouds(1),
            cod = 1,
            coord = Coord(lat, lon),
            dt = 1,
            id = 1,
            main = MainWeather(1.0, 1, 1, 1.0, 1.0, 1.0),
            name = "test",
            rain = Rain(1.0),
            sys = SysWeather("test", 1, 1, 1, 1),
            timezone = 1,
            visibility = 1,
            weather = listOf(Weather("test", "test", 1, "test")),
            wind = WindWeather(1, 1.0)
        )
        val mockResponse = Response.success(currentWeather)
        `when`(weatherApiMock.getCurrentWeather(lat, lon, APP_ID, METRIC)).thenReturn(mockResponse)

        //Call
        val result = subject.getCurrentWeather(lat, lon)

        //Verification
        assertEquals(currentWeather.main.temp, result.temp)
        assertEquals(currentWeather.weather.first().main, result.main)
        assertEquals(currentWeather.weather.first().description, result.description)
        assertEquals(currentWeather.main.pressure, result.pressure)
        assertEquals(currentWeather.main.humidity, result.humidity)
        assertEquals(currentWeather.wind.speed, result.windSpeed)
    }

    @Test
    fun `getForecast success response`(): Unit = runBlocking {
        //Setup
        val expectedForecast = Forecast(
            city = City(Coord(lat, lon), "", 1, "", 1, 1, 1, 1),
            cnt = 1,
            cod = "test",
            list = listOf(),
            message = 1
        )
        val mockResponse = Response.success(expectedForecast)
        `when`(weatherApiMock.getForecast(lat, lon, APP_ID, METRIC)).thenReturn(mockResponse)

        //Call
        val result = subject.getForecast(lat, lon)

        //Verification
        assertEquals(expectedForecast.cnt, result.cnt)
        assertEquals(expectedForecast.city, result.city)
        assertEquals(expectedForecast.cod, result.cod)
        assertEquals(expectedForecast.message, result.message)
        assertEquals(expectedForecast.list, result.list)
    }
}