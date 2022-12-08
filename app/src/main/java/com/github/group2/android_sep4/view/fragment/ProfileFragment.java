package com.github.group2.android_sep4.view.fragment;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.view.uielements.DeleteAccountPopup;
import com.github.group2.android_sep4.viewmodel.UserViewModel;
import com.shashank.sony.fancytoastlib.FancyToast;


public class ProfileFragment extends Fragment {


    ImageButton logoutButton;
    UserViewModel viewModel;
    private Button updateButton, deleteAccountButton;
    private EditText username, email, password, confirmPassword;
    DeleteAccountPopup deleteAccountPopup;
    NavController navController;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        initializeAllFields(view);
        logoutButton.setOnClickListener(this::logOut);
        viewModel.getSuccesMessage().observe(getViewLifecycleOwner(), this::showToast);
        return view;
    }

    private void showToast(String message) {
       FancyToast.makeText(getContext(), message, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
    }
    private void logOut(View view) {
        viewModel.logout();
    }

    private void initializeAllFields(View view) {

        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
        logoutButton = view.findViewById(R.id.logOutButton);
        updateButton = view.findViewById(R.id.updateAccountButton);
        deleteAccountButton = view.findViewById(R.id.deleteAccountButton);
        username = view.findViewById(R.id.updateNameEditText);
        email = view.findViewById(R.id.updateEmailEditText);
        password = view.findViewById(R.id.updatePasswordEditText);
        confirmPassword = view.findViewById(R.id.updateRepeatPasswordEditText);

        if (viewModel.getCurrentUser() != null) {
            username.setText(viewModel.getCurrentUser().getValue().getUsername());
            email.setText(viewModel.getCurrentUser().getValue().getEmail());
            password.setText(viewModel.getCurrentUser().getValue().getPassword());
        }

        deleteAccountButton.setOnClickListener(this::deleteAccount);
        updateButton.setOnClickListener(this::updateAccount);
    }

    private void updateAccount(View view) {

        if (username.getText().equals("")) {
            username.setError("Username cannot be empty");
            return;
        }
        if (email.getText().equals("")) {
            email.setError("Email cannot be empty");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
            email.setError("Please enter a valid email address");
            return;
        }
        if (password.getText().equals("")) {
            password.setError("Password cannot be empty");
            return;
        }
        if (!viewModel.getCurrentUser().getValue().getPassword().equals(password.getText().toString())) {
            if (password.getText().length() < 8) {
                password.setError("Password must be at least 8 characters long");
                return;
            }
            if (confirmPassword.getText() == null) {
                confirmPassword.setError("Please confirm your password");
                return;
            }
            if (!confirmPassword.getText().toString().equals(password.getText().toString())) {
                confirmPassword.setError("Passwords do not match");
                return;
            }
        }


        String username = this.username.getText().toString();
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();
        viewModel.updateUser(username, email, password);
        Toast.makeText(getContext(), "Account updated", Toast.LENGTH_SHORT).show();


    }


    private void deleteAccount(View view) {



        deleteAccountPopup = new DeleteAccountPopup();
        deleteAccountPopup.showPopupWindow(view, viewModel);


      //  viewModel.deleteUser(viewModel.getCurrentUser().getValue().getId());
    }


}
