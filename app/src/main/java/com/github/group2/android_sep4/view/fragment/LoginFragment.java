package com.github.group2.android_sep4.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.viewmodel.UserViewModel;
import com.github.group2.android_sep4.view.adapter.LoginAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    LoginAdapter loginAdapter;
    UserViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        initializeAllFields(view);
        setupAdapter();
        initializeTab();
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), this::userObserver);
        viewModel.getError().observe(getViewLifecycleOwner(), this::errorObserver);

        return view;
    }

    private void initializeTab() {
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            if (position == 0) tab.setText(R.string.login);
            else if (position == 1) tab.setText(R.string.sign_up);
        }
        ).attach();
    }


    private void errorObserver(String s) {
        if (s != null && !s.equalsIgnoreCase("")) {
            FancyToast.makeText(getContext(), s, FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

        }
    }


    private void setupAdapter() {
        loginAdapter = new LoginAdapter(getChildFragmentManager(), getLifecycle());
        loginAdapter.addFragment(new LoginTabFragment());
        loginAdapter.addFragment(new SignUpTabFragment());
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setAdapter(loginAdapter);
    }


    private void userObserver(User user) {
        if (user != null) {

            FancyToast.makeText(getContext(), "Welcome " +user.getUsername(), FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
            // Todo swap fragment to main page
        }

    }

    private void initializeAllFields(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager2 = view.findViewById(R.id.view_pager2);
    }
}
