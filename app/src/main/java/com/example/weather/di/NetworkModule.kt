package com.example.weather.di

import com.example.weather.network.RetrofitHelper
import com.example.weather.network.WeatherApi
import com.example.weather.repository.WeatherRepository
import com.example.weather.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi)
    }

    @Provides
    fun provideRequestsApi(): WeatherApi {
        return RetrofitHelper.getInstance().create(WeatherApi::class.java)
    }
}