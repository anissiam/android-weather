package com.vision.weatherapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.vision.weatherapplication.R;
import com.vision.weatherapplication.adapter.ViewPagerAdapter;
import com.vision.weatherapplication.api.ApiClient;
import com.vision.weatherapplication.api.Apinterface;
import com.vision.weatherapplication.databinding.ActivityHomeBinding;
import com.vision.weatherapplication.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Apinterface apinterface = ApiClient.getApiClientPlaceholder().create(Apinterface.class);
        Post post = new Post();
        post.setTitle("hello");
        post.setBody("from java");
        post.setUserId(500);


        apinterface.pushPost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.d("asdasdd", response.body() + "::");

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
        /*apinterface.getPostUser("1").enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.d("asdasdd", response.body() + "::");

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });*/

        /*apinterface.getPost("1").enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.d("asdasdd", response.body() + "::");

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });*/
        /*apinterface.getAllPost().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.d("asdasdd", response.body() + "::");
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });*/






        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        binding.viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position==0){
                    tab.setText("Current");
                }else {
                    tab.setText("Pick");
                }
            }
        }).attach();
    }
}