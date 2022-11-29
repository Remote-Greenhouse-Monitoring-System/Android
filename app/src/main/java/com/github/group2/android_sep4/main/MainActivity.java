package com.github.group2.android_sep4.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.entity.User;
import com.github.group2.android_sep4.ui.measurement.MeasurementFragment;

public class MainActivity extends AppCompatActivity {



    private MainActivityViewModel viewModel;
    NavController navController;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  //day



        preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
setContentView(R.layout.activity_main);

      initializeAllFields();
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
       checkIfSignedIn();
    }

    private void initializeAllFields() {
        navController = Navigation.findNavController(this, R.id.fragment_container);
        navController.navigate(R.id.measurementFragment);



    }

    private void checkIfSignedIn() {

        String username = preferences.getString("username",null);
        String email = preferences.getString("email",null);

        if (username !=null && email !=null){
            User userToSave = new User(email, username, null);
            viewModel.init(userToSave);

        }

        viewModel.getCurrentUser().observe(this, user ->{
            if (user==null){
                displayLogin();
            }
            else{
                // Saving these details on device to not login literally everytime...
                preferences.edit().putString("username", user.getUsername()).apply();
                preferences.edit().putString("email", user.getEmail()).apply();
            }

        });

    }

    private void displayLogin() {
        navController.navigate(R.id.loginFragment);

    }
}