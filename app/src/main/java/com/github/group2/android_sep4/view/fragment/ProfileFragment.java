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
        return view;
    }

    private void logOut(View view) {
        viewModel.logout();
    }

    private void initializeAllFields(View view) {
        logoutButton = view.findViewById(R.id.logOutButton);
    }
}
