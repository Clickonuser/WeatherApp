package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.weather.network.RetrofitHelper
import com.example.weather.network.WeatherApi
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val retrofitClient = RetrofitHelper.getInstance().create(WeatherApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {

            val resultGeocoding = retrofitClient.getGeocoding("London", "1", "0e11c960184bb342907b1b942930e9c8")
            Log.d("qwerty", "Geocoding: ${resultGeocoding.body()}")

            val resultCurrentWeather = retrofitClient.getCurrentWeather(51.5073219, -0.1276474, "0e11c960184bb342907b1b942930e9c8", "metric")
            Log.d("qwerty", "Current weather: ${resultCurrentWeather.body()}")

            val resultForecast = retrofitClient.getForecast(51.5073219, -0.1276474, "0e11c960184bb342907b1b942930e9c8", "metric")
            Log.d("qwerty", "Forecast: ${resultForecast.body()}")

        }

    }
}

