package com.vision.weatherapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.vision.weatherapplication.R;
import com.vision.weatherapplication.adapter.ViewPagerAdapter;
import com.vision.weatherapplication.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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