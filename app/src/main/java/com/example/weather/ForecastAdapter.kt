package com.example.weather

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.ItemForecastBinding
import com.example.weather.model.forecast.ForecastResult
import com.example.weather.repository.WeatherRepositoryImpl

class ForecastAdapter(private val context: Context, private val forecastResultList: List<ForecastResult>) : RecyclerView.Adapter<ForecastAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val el = forecastResultList[position]
        holder.bindItem(el)
    }

    override fun getItemCount(): Int {
        return forecastResultList.size
    }

    class ViewHolder(private val binding: ItemForecastBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(forecast: ForecastResult) {
            binding.itemRecyclerDate.text = forecast.date
            binding.itemRecyclerTemp.text = forecast.temp
            binding.itemRecyclerDescription.text = forecast.description
            when(forecast.main) {
                WeatherRepositoryImpl.WEATHER_TYPE_CLEAR -> {
                    binding.weatherImage.setImageDrawable(ContextCompat.getDrawable(context,
                        R.drawable.clear
                    ))
                }
                WeatherRepositoryImpl.WEATHER_TYPE_CLOUDS -> {
                    binding.weatherImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.clouds))
                }
                WeatherRepositoryImpl.WEATHER_TYPE_RAIN -> {
                    binding.weatherImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.rain))
                }
                WeatherRepositoryImpl.WEATHER_TYPE_THUNDERSTORM -> {
                    binding.weatherImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.thunderstorm))
                }
                WeatherRepositoryImpl.WEATHER_TYPE_SNOW -> {
                    binding.weatherImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.snow))
                }
                else -> {
                    binding.weatherImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.mist))
                }

            }
        }
    }
}