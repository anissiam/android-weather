package com.vision.weatherapplication.api;

import static com.vision.weatherapplication.utils.Constants.BASE_URL;

import com.vision.weatherapplication.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    public static Retrofit getApiClient(){
        if (retrofit==null){
            retrofit =new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
