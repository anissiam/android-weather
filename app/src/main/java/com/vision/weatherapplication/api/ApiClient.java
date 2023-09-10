package com.vision.weatherapplication.api;

import static com.vision.weatherapplication.utils.Constants.BASE_URL;

import com.vision.weatherapplication.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    private static Retrofit retrofitPlaceHolder;
    public static Retrofit getApiClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getApiClientPlaceholder(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL_PLACE_HOLDER)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
