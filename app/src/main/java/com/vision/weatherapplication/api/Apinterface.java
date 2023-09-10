package com.vision.weatherapplication.api;

import com.vision.weatherapplication.model.Post;
import com.vision.weatherapplication.model.WeatherModel;
import com.vision.weatherapplication.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Apinterface {

    @GET("weather?&units=metric&appid=" + Constants.API_KEY)
    Call<WeatherModel> getWeatherDataByCity(@Query("q") String city);

    @GET("weather?&units=metric&appid=" + Constants.API_KEY)
    Call<WeatherModel> getWeatherDataByLanLang(@Query("lat") double lat, @Query("lon") double lon);


    @GET("posts")
    Call<List<Post>> getAllPost();

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") String id);

    @GET("posts")
    Call<List<Post>> getPostUser(@Query("userId") String id);

    @POST("posts")
    Call<Post> pushPost(@Body Post post);
}
