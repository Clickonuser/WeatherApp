package com.example.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weather.model.Coord
import com.example.weather.model.currentweather.CurrentWeatherResult
import com.example.weather.model.forecast.City
import com.example.weather.model.forecast.Forecast
import com.example.weather.repository.WeatherRepository
import com.example.weather.viewmodel.MainViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class MainViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val weatherRepositoryMock: WeatherRepository = mock()
    private lateinit var subject: MainViewModel

    private val lat = 1.0
    private val lon = 1.0

    @Before
    fun setup() {
        subject = MainViewModel(weatherRepositoryMock)
    }

    @Test
    fun `getCoordinates success`() = runBlocking {
        //Setup
        val city = "TestCity"
        val expectedCoordinates = Coord(lat, lon)
        `when`(weatherRepositoryMock.getCoordinates(city)).thenReturn(expectedCoordinates)

        //Call
        subject.getCoordinates(city)

        //Verification
        assertEquals(expectedCoordinates, subject.coordinatesResult.value)
    }

    @Test
    fun `getCurrentWeather success`() = runBlocking {
        //Setup
        val expectedWeather = CurrentWeatherResult(
            main = "testMain",
            description = "testDescription",
            temp = 1.0,
            pressure = 1,
            humidity = 1,
            windSpeed = 1.0
        )
        `when`(weatherRepositoryMock.getCurrentWeather(lat, lon)).thenReturn(expectedWeather)

        //Call
        subject.getCurrentWeather(lat, lon)

        //Verification
        assertEquals(expectedWeather, subject.currentWeatherResult.value)
    }

    @Test
    fun `getForecast success`() = runBlocking {
        //Setup
        val expectedForecast = Forecast(
            city = City(Coord(lat, lon), "testCountry", 1, "testName", 1, 1, 1, 1),
            cnt = 1,
            cod = "testCod",
            list = emptyList(),
            message = 1
        )
        `when`(weatherRepositoryMock.getForecast(lat, lon)).thenReturn(expectedForecast)

        //Call
        subject.getForecast(lat, lon)

        //Verification
        assertEquals(expectedForecast, subject.forecastResult.value)
    }
}