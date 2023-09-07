package com.vision.weatherapplication.api;

import com.vision.weatherapplication.model.WeatherModel;
import com.vision.weatherapplication.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apinterface {

    @GET("weather?&units=metric&appid=" + Constants.API_KEY)
    Call<WeatherModel> getWeatherDataByCity(@Query("q") String city);

    @GET("weather?&units=metric&appid=" + Constants.API_KEY)
    Call<WeatherModel> getWeatherDataByLanLang(@Query("lat") double lat, @Query("lon") double lon);
}
