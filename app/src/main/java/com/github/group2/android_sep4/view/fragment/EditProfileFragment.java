package com.github.group2.android_sep4.view.fragment;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.viewmodel.UserViewModel;
import com.google.android.material.textfield.TextInputLayout;
import com.shashank.sony.fancytoastlib.FancyToast;

public class EditProfileFragment extends Fragment {

    TextInputLayout username, email, password, confirmPassword;
    AppCompatButton saveButton;
    NavController navController;
    ImageButton backButton;

    ProgressBar progressBar;
    private UserViewModel viewModel;

    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
         user = viewModel.getCurrentUser().getValue();

        initializeAllFields(view);
        saveButton.setOnClickListener(this::updateProfile);

        return view;
    }

    private void updateProfile(View view) {
        boolean validUsername = validateUsername();
        boolean validEmail = validateEmail();
        boolean validPassword = validatePassword();
        boolean validConfirmPassword = validateConfirmPassword();

        boolean isEverythingValid = validUsername && validEmail && validPassword && validConfirmPassword;
        if (!isEverythingValid) return;

        progressBar.setVisibility(View.VISIBLE);

        String username = this.username.getEditText().getText().toString();
        String email = this.email.getEditText().getText().toString();
        String password = this.password.getEditText().getText().toString();
        User user = new User(email, username, password);
        user.setId(viewModel.getCurrentUser().getValue().getId());
        viewModel.updateUser(user);

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), this::errorObserver);
        viewModel.getSuccessMessage().observe(getViewLifecycleOwner(), this::successObserver);
    }

    private void successObserver(String s) {
        if (s == null)
            return;

        progressBar.setVisibility(View.INVISIBLE);
        FancyToast.makeText(getContext(), s, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
        navController.navigate(R.id.profileFragment);
    }

    private void errorObserver(String s) {
        if (s == null) return;
        FancyToast.makeText(getContext(), s, FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
    }

    private boolean validateConfirmPassword() {
        String confirmPasswordInput = confirmPassword.getEditText().getText().toString().trim();
        if (confirmPasswordInput.isEmpty()) {
            confirmPassword.setError("Field can't be empty");
            return false;
        } else if (!confirmPasswordInput.equals(password.getEditText().getText().toString().trim())) {
            confirmPassword.setError("Passwords don't match");
            return false;
        } else {
            confirmPassword.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = password.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else if (passwordInput.length() < 8) {
            password.setError("Password must be at least 8 characters long");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String emailInput = email.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = username.getEditText().getText().toString().trim();
        if (usernameInput.isEmpty()) {
            username.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 25) {
            username.setError("Username too long");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    private void initializeAllFields(View view) {
        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        confirmPassword = view.findViewById(R.id.confirm_password);
        saveButton = view.findViewById(R.id.update_profile_button);
        progressBar = view.findViewById(R.id.progressBar);
        backButton= view.findViewById(R.id.edit_profile_back_btn);
        backButton.setOnClickListener(v -> navController.navigate(R.id.profileFragment));
        progressBar.setVisibility(View.INVISIBLE);
        username.getEditText().setText(user.getUsername());
        email.getEditText().setText(user.getEmail());

        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
    }
}
