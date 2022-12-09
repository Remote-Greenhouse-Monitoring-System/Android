package com.github.group2.android_sep4.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.group2.android_sep4.R;
import com.google.android.material.textfield.TextInputLayout;

public class ForgetPasswordFragment extends Fragment {


    AppCompatButton nextButton;
    TextInputLayout emailField;

    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_passsword, container, false);
        initializeAllFields(view);



        nextButton.setOnClickListener(this::nextButtonPressed);
        return view;
    }

    private void nextButtonPressed(View view) {

        navController.navigate(R.id.changePasswordFragment);
    }

    private void initializeAllFields(View view) {

        nextButton = view.findViewById(R.id.nextButton);
        emailField = view.findViewById(R.id.forget_email);
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);

    }
}
