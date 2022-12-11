package com.github.group2.android_sep4.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.viewmodel.UserViewModel;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancytoastlib.FancyToast;


public class ProfileFragment extends Fragment {


    AppCompatButton logoutButton, deleteAccountButton;
    LinearLayoutCompat editProfileButton;

    TextView emailTextView, usernameTextView;
    private UserViewModel viewModel;
    NavController navController;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        initializeAllFields(view);
        logoutButton.setOnClickListener(this::logOut);
        deleteAccountButton.setOnClickListener(this::deleteAccount);
        editProfileButton.setOnClickListener(this::editProfile);
        viewModel.getSuccessMessage().observe(getViewLifecycleOwner(), this::showToast);
        return view;
    }

    private void editProfile(View view) {
        navController.navigate(R.id.editProfileFragment);
    }

    private void showToast(String message) {
        if (message == null) return;
        FancyToast.makeText(getContext(), message, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
    }
    private void logOut(View view) {
        viewModel.logout();
    }

    private void initializeAllFields(View view) {

        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
        logoutButton = view.findViewById(R.id.log_out);
        deleteAccountButton = view.findViewById(R.id.delete_account);
        emailTextView = view.findViewById(R.id.profile_email);
        usernameTextView = view.findViewById(R.id.profile_name);
        editProfileButton = view.findViewById(R.id.edit_profile);


        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            if (user == null) return;
            emailTextView.setText(user.getEmail());
            usernameTextView.setText(user.getUsername());
        });


    }

//    private void updateAccount(View view) {
//        if (username.getText().equals("")) {
//            username.setError("Username cannot be empty");
//            return;
//        }
//        if (email.getText().equals("")) {
//            email.setError("Email cannot be empty");
//            return;
//        }
//        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
//            email.setError("Please enter a valid email address");
//            return;
//        }
//        if (password.getText().equals("")) {
//            password.setError("Password cannot be empty");
//            return;
//        }
//        if (!viewModel.getCurrentUser().getValue().getPassword().equals(password.getText().toString())) {
//            if (password.getText().length() < 8) {
//                password.setError("Password must be at least 8 characters long");
//                return;
//            }
//            if (confirmPassword.getText() == null) {
//                confirmPassword.setError("Please confirm your password");
//                return;
//            }
//            if (!confirmPassword.getText().toString().equals(password.getText().toString())) {
//                confirmPassword.setError("Passwords do not match");
//                return;
//            }
//        }
//
//        String username = this.username.getText().toString();
//        String email = this.email.getText().toString();
//        String password = this.password.getText().toString();
//
//        User user = new User(email,username,  password);
//        viewModel.updateUser(user);
//        Toast.makeText(getContext(), "Account updated", Toast.LENGTH_SHORT).show();
//    }



    private void deleteAccount(View view) {
        String message = getString(R.string.deleteAccountMessage);
        FancyAlertDialog.Builder.with(getContext())
                .setTitle("Delete Profile")
                .setBackgroundColorRes(R.color.palette_red)
                .setMessage(message)
                .setNegativeBtnText("Cancel")
                .setPositiveBtnBackgroundRes(R.color.palette_red)
                .setPositiveBtnText("Confirm")
                .setNegativeBtnBackgroundRes(R.color.palette_grey)
                .setAnimation(Animation.SLIDE)
                .isCancellable(true)
                .setIcon(R.drawable.ic_baseline_delete_outline_24, View.VISIBLE)
                .onPositiveClicked(dialog -> {
                    viewModel.deleteUser(viewModel.getCurrentUser().getValue().getId());
//                    FancyToast.makeText(getContext(), "Account D=deleted", FancyToast.LENGTH_LONG, FancyToast.INFO, false).show();
                })
                .onNegativeClicked(dialog -> {
                    dialog.dismiss();
                    FancyToast.makeText(getContext(), "Cancelled", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();

                })
                .build()
                .show();
    }


}
