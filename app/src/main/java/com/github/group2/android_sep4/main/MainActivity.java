package com.github.group2.android_sep4.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.entity.User;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;
    private BottomNavigationView bottomNavigationView;
    NavController navController;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        initializeAllFields();
        checkIfSignedIn();
    }

    private void initializeAllFields() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.INVISIBLE);

        navController = Navigation.findNavController(this, R.id.fragment_container);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null)
            actionBar.hide();
    }

    private void checkIfSignedIn() {
        String username = preferences.getString("username", null);
        String email = preferences.getString("email", null);

        if (username != null && email != null) {
            viewModel.init(new User(username, email, null));
        }
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                navController.navigate(R.id.homeFragment);
                bottomNavigationView.setVisibility(View.VISIBLE);

                // Save for later

                preferences.edit().putString("username", user.getUsername()).apply();
                preferences.edit().putString("email", user.getEmail()).apply();
            } else {
                navController.navigate(R.id.loginFragment);
                bottomNavigationView.setVisibility(View.INVISIBLE);
                preferences.edit().putString("username", null).apply();
                preferences.edit().putString("email", null).apply();
            }
        });
    }
}