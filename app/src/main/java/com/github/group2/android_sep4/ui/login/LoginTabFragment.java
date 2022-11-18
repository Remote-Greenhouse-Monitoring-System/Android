package com.github.group2.android_sep4.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.github.group2.android_sep4.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginTabFragment extends Fragment {

    TextInputLayout emailField, passwordField;
    AppCompatButton login;
    TextView forgetPassword;
    ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_tab_fragment, container, false);

        initializeAllFields(view);
        return view;




    }

    private void initializeAllFields(View view) {
        emailField = view.findViewById(R.id.emailSignUp);
        passwordField = view.findViewById(R.id.passwordLogin);
        login = view.findViewById(R.id.loginBtn);
        forgetPassword= view.findViewById(R.id.forget_pass);
        progressBar = view.findViewById(R.id.progress_bar_login);
        progressBar.setVisibility(View.INVISIBLE);
    }


}
