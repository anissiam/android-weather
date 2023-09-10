package com.vision.weatherapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vision.weatherapplication.R;
import com.vision.weatherapplication.api.ApiClient;
import com.vision.weatherapplication.api.Apinterface;
import com.vision.weatherapplication.databinding.FragmentPickLocationBinding;
import com.vision.weatherapplication.model.WeatherModel;
import com.vision.weatherapplication.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PickLocationFragment extends Fragment {
    private FragmentPickLocationBinding binding;

    public PickLocationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPickLocationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Apinterface apInterface = ApiClient.getApiClient().create(Apinterface.class);
                Call<WeatherModel> call = apInterface.getWeatherDataByCity(binding.edtCity.getText().toString());
                call.enqueue(new Callback<WeatherModel>() {
                    @Override
                    public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                        if (response.body() != null) {
                            WeatherModel model = response.body();
                            binding.tvData.setText("Temp:" + model.getMain().getTemp()+"\n" +
                                    "Wind:" + model.getWind().getSpeed());
                        } else {
                            binding.tvData.setText("There is no weather data");
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherModel> call, Throwable t) {

                    }
                });
            }
        });


    }
}