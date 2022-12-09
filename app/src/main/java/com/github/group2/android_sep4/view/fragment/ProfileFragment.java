package com.github.group2.android_sep4.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.viewmodel.UserViewModel;
import com.shashank.sony.fancytoastlib.FancyToast;

public class ProfileFragment extends Fragment {


    Button logoutButton;
    UserViewModel viewModel;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        initializeAllFields(view);
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        logoutButton.setOnClickListener(this ::logOut);

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), this::showErrorMessage);
        viewModel.getSuccessMessage().observe(getViewLifecycleOwner(), this::showSuccessMessage);

        return view;
    }

    private void showSuccessMessage(String s) {
        if (s ==null) return;
        FancyToast.makeText(getContext(), s, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
    }

    private void showErrorMessage(String s) {
        if (s ==null) return;
        FancyToast.makeText(getContext(), s, FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
    }



    private void logOut(View view) {
        viewModel.logout();
    }

    private void initializeAllFields(View view) {
        logoutButton = view.findViewById(R.id.logOutButton);

    }
}
