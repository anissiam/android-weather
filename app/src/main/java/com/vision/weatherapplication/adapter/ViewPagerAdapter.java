package com.vision.weatherapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.vision.weatherapplication.ui.CurrentLocationFragment;
import com.vision.weatherapplication.ui.PickLocationFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position==0){
            return new CurrentLocationFragment();
        }else {
            return new PickLocationFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
