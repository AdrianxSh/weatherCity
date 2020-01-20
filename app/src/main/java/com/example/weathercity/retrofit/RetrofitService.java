package com.example.weathercity.retrofit;

import com.example.weathercity.model.ModelForecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("forecast")
    Call<ModelForecast> getWeatherForecast
            (@Query("q") String cityName,
             @Query("units") String unit,
             @Query("appid") String appid);
}
