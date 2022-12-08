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
import androidx.lifecycle.ViewModelProvider;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.entity.User;
import com.google.android.material.textfield.TextInputLayout;

public class LoginTabFragment extends Fragment {

    TextInputLayout emailField, passwordField;
    AppCompatButton login;
    TextView forgetPassword;
    ProgressBar progressBar;

    UserViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_tab_fragment, container, false);
        initializeAllFields(view);
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        login.setOnClickListener(this::loginPressed);
        forgetPassword.setOnClickListener(this::forgetPasswordPressed);
        viewModel.getError().observe(getViewLifecycleOwner(), this::errorObserver);
        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), this::userObserver);
        return view;
    }


    private void userObserver(User user) {
        if (user !=null){
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void errorObserver(String s) {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void loginPressed(View view) {

        boolean isValidEmail = validateEmail();
        boolean isValidPassword = validatePassword();
        boolean isEverythingValid = isValidEmail && isValidPassword;
        String email = emailField.getEditText().getText().toString().trim();
        String password = passwordField.getEditText().getText().toString().trim();
        if (isEverythingValid){
            progressBar.setVisibility(View.VISIBLE);
            viewModel.login(email, password);
        }


    }

    private boolean validatePassword() {
        String password = passwordField.getEditText().getText().toString().trim();

        if (password.isEmpty()){
            passwordField.setError(getString(R.string.empty_password));
            return false;
        }
        else if (password.length()<8){
            passwordField.setError("Password must contain more than 8 chars");
            return false;
        }
        else{
            passwordField.setError(null);
            passwordField.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateEmail() {
        String email = emailField.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.isEmpty()){
            emailField.setError(getString(R.string.empty_email));
            return false;
        }
        else if (!email.matches(checkEmail)){
            emailField.setError(getString(R.string.invalid_email));
            return false;

        }
        else{
            emailField.setError(null);
            emailField.setErrorEnabled(false);
            return true;
        }


    }


    private void initializeAllFields(View view) {
        emailField = view.findViewById(R.id.emailLogin);
        passwordField = view.findViewById(R.id.passwordLogin);
        login = view.findViewById(R.id.loginBtn);
        forgetPassword= view.findViewById(R.id.forget_pass);
        progressBar = view.findViewById(R.id.progress_bar_login);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void forgetPasswordPressed(View view) {

    }


}
