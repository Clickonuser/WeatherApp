package com.example.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.repository.WeatherRepositoryImpl.Companion.WEATHER_TYPE_CLEAR
import com.example.weather.repository.WeatherRepositoryImpl.Companion.WEATHER_TYPE_CLOUDS
import com.example.weather.repository.WeatherRepositoryImpl.Companion.WEATHER_TYPE_RAIN
import com.example.weather.repository.WeatherRepositoryImpl.Companion.WEATHER_TYPE_SNOW
import com.example.weather.repository.WeatherRepositoryImpl.Companion.WEATHER_TYPE_THUNDERSTORM
import com.example.weather.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.currentWeatherResult.observe(viewLifecycleOwner, Observer {
            binding?.weatherMain?.text = it.main
            binding?.weatherDescription?.text = it.description
            binding?.weatherTemp?.text = it.temp.toString()
            binding?.weatherHumidityValue?.text = it.humidity.toString()
            binding?.weatherPressureValue?.text = it.pressure.toString()
            binding?.weatherWindSpeedValue?.text = it.windSpeed.toString()

            when(it.main) {
                WEATHER_TYPE_CLEAR -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.clear
                        ))
                }
                WEATHER_TYPE_CLOUDS -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.clouds
                        ))
                }
                WEATHER_TYPE_RAIN -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.rain
                        ))
                }
                WEATHER_TYPE_THUNDERSTORM -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.thunderstorm
                        ))
                }
                WEATHER_TYPE_SNOW -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.snow
                        ))
                }
                else -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.mist
                        ))
                }

            }

        })
    }


    companion object {
        fun newInstance() = WeatherFragment()
    }

}