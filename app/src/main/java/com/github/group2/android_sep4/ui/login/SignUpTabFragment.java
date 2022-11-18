package com.github.group2.android_sep4.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.entity.User;
import com.google.android.material.textfield.TextInputLayout;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpTabFragment extends Fragment {

    TextInputLayout emailField, passwordField, usernameField, confirmPasswordField;
    AppCompatButton signUp;
    ProgressBar progressBar;
    private UserViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up_tab_fragment, container, false);
        initializeAllFields(view);


        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), this::userObserver);
        viewModel.getError().observe(getViewLifecycleOwner(), this::errorObserver);


        signUp.setOnClickListener(this::signUpPressed);

        return view;

    }

    private void errorObserver(String s) {
        if (s != null){
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void userObserver(User user) {
        if (user !=null){
            progressBar.setVisibility(View.INVISIBLE);
        }

    }



    private void signUpPressed(View view) {
        boolean isAppropriateEmail = validateEmail();
        boolean isAppropriatePassword = validatePassword();
        boolean isAppropriateUsername = validateUsername();

        boolean isEverythingValid = isAppropriateEmail && isAppropriatePassword && isAppropriateUsername;

        if (!isEverythingValid) return;

        String email = emailField.getEditText().getText().toString().trim();
        String password = passwordField.getEditText().getText().toString().trim();
        String username = usernameField.getEditText().getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);

        FancyToast.makeText(getContext(), "Sent to view model", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
        viewModel.signUp(username, email, password);
    }

    private boolean validateUsername() {
        String username = usernameField.getEditText().getText().toString().trim();

        if (username.isEmpty()) {
            usernameField.setError("Username cannot be empty");
            return false;
        } else if (username.length() < 3) {
            usernameField.setError("Username must contain more than 3 chars");
            return false;
        } else {
            usernameField.setError(null);
            usernameField.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String password = passwordField.getEditText().getText().toString().trim();
        String confirmPassword = confirmPasswordField.getEditText().getText().toString().trim();

        if (password.isEmpty()) {
            passwordField.setError("Password cannot be empty");
            return false;
        }
        else if (password.length() < 8) {
            passwordField.setError("Password must contain more than 8 chars");
            return false;

        } else if (!confirmPassword.equals(password)){
            confirmPasswordField.setError("Passwords don't match");
            return false;
        }



        else {
            passwordField.setError(null);
            passwordField.setErrorEnabled(false);

            confirmPasswordField.setError(null);
            confirmPasswordField.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateEmail() {
        String email = emailField.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.isEmpty()) {
            emailField.setError("Email cannot be empty");
            return false;
        } else if (!email.matches(checkEmail)) {
            emailField.setError("Invalid email!");
            return false;

        } else {
            emailField.setError(null);
            emailField.setErrorEnabled(false);
            return true;
        }
    }

    private void initializeAllFields(View view) {


        emailField = view.findViewById(R.id.emailSignUp);
        passwordField = view.findViewById(R.id.passwordSignUp);
        usernameField = view.findViewById(R.id.usernameSignUp);
        confirmPasswordField = view.findViewById(R.id.confirmPasswordSignUp);
        signUp = view.findViewById(R.id.signUpBtn);
        progressBar = view.findViewById(R.id.progressBarSignUp);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
