package com.example.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.ItemForecastBinding
import com.example.weather.model.forecast.ForecastResult

class ForecastAdapter(private val forecastResultList: List<ForecastResult>) : RecyclerView.Adapter<ForecastAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val el = forecastResultList[position]
        holder.bindItem(el)
    }

    override fun getItemCount(): Int {
        return forecastResultList.size
    }

    class ViewHolder(private val binding: ItemForecastBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(forecast: ForecastResult) {
            binding.itemRecyclerDate.text = forecast.date
            binding.itemRecyclerTemp.text = forecast.temp
            binding.itemRecyclerDescription.text = forecast.description
        }
    }
}