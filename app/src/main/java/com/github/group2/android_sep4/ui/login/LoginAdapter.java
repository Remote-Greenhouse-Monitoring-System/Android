package com.github.group2.android_sep4.ui.login;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class LoginAdapter extends FragmentStateAdapter {


    private List<Fragment> allFragments;

    public LoginAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        allFragments = new ArrayList<>();
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return allFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return allFragments.size();
    }

    public void addFragment(Fragment fragment) {
        allFragments.add(fragment);
    }
}
