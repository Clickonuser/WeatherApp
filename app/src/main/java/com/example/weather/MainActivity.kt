package com.example.weather

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.weather.network.RetrofitHelper
import com.example.weather.network.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var locationLabel: TextView
    private lateinit var currentWeatherLabel: TextView
    private lateinit var forecastLabel: TextView

    private val retrofitClient = RetrofitHelper.getInstance().create(WeatherApi::class.java)

    private val appId = "0e11c960184bb342907b1b942930e9c8"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationLabel = findViewById(R.id.locationLabel)
        currentWeatherLabel = findViewById(R.id.currentWeatherLabel)
        forecastLabel = findViewById(R.id.forecastLabel)

        lifecycleScope.launch(Dispatchers.IO) {

            val geocodingResult = retrofitClient.getGeocoding("London", "1", appId)
            val latResult = geocodingResult.body()?.first()?.lat ?: 0.0
            val lonResult = geocodingResult.body()?.first()?.lon ?: 0.0

            val currentWeatherResult = retrofitClient.getCurrentWeather(latResult, lonResult, appId, "metric")

            val forecastResult = retrofitClient.getForecast(latResult, lonResult, appId, "metric")

            // UI updates
            withContext(Dispatchers.Main) {

                locationLabel.text = "coordinates: $latResult $lonResult"
                currentWeatherLabel.text =
                    "Current temp: ${currentWeatherResult.body()?.main?.temp.toString()}"
                forecastLabel.text =
                    "forecast for ${forecastResult.body()?.list?.get(15)?.dt_txt.toString()} : ${
                        forecastResult.body()?.list?.get(15)?.main?.temp.toString()
                    }"

            }

        }
    }
}

