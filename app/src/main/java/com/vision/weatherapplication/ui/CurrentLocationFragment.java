package com.vision.weatherapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vision.weatherapplication.api.Apinterface;
import com.vision.weatherapplication.api.ApiClient;
import com.vision.weatherapplication.databinding.FragmentCurrentLocationBinding;
import com.vision.weatherapplication.model.WeatherModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentLocationFragment extends Fragment {
    private FragmentCurrentLocationBinding binding;

    public CurrentLocationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCurrentLocationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Apinterface apInterface = ApiClient.getApiClient().create(Apinterface.class);
        Call<WeatherModel> call = apInterface.getWeatherDataByLanLang(31.2708465, 34.2583693);
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                WeatherModel model = response.body();
                int temp = model.getMain().getTemp().intValue();
                int cond = model.getWeather().get(0).getId();
                String icon = getIcon(cond);
                binding.tvTemp.setText(temp + "°" + icon);

                String massage = getMessage(temp);
                binding.tvDesc.setText(massage);

            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                Log.d("TAG", t.getMessage());

            }
        });

    }

    String getIcon(int condition) {
        if (condition < 300) {
            return "🌩";
        } else if (condition < 400) {
            return "🌧";
        } else if (condition < 600) {
            return "☔️";
        } else if (condition < 700) {
            return "☃️";
        } else if (condition < 800) {
            return "🌫";
        } else if (condition == 800) {
            return "☀️";
        } else if (condition <= 804) {
            return "☁️";
        } else {
            return "🤷‍";
        }
    }

    String getMessage(int temp) {
        if (temp > 25) {
            return "It\'s 🍦 time";
        } else if (temp > 20) {
            return "Time for shorts and 👕";
        } else if (temp < 10) {
            return "'You\'ll need 🧣 and 🧤'";
        } else {
            return "Bring a 🧥 just in case";
        }
    }
}