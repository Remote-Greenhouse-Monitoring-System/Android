package com.github.group2.android_sep4.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.viewmodel.MainActivityViewModel;
import com.github.group2.android_sep4.model.User;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    private BottomNavigationView bottomNavigationView;
    NavController navController;
    SharedPreferences preferences;

    MutableLiveData<String> token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        token = new MutableLiveData<>();

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("MainActivity", "Fetching FCM registration token failed", task.getException());
                return;
            }
            token.setValue(task.getResult());
            Log.d("MainActivity", token.getValue());
        });

        preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        checkIfSignedIn();
        setContentView(R.layout.activity_main);
        initializeAllFields();
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
        long uID = preferences.getLong("uID", -1);

        if (username != null && email != null && uID != -1) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setId(uID);

            viewModel.init(user);
        }

        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                navController.navigate(R.id.homeFragment);
                bottomNavigationView.setVisibility(View.VISIBLE);
                registerNotificationToken(user);

                // Save for later
                preferences.edit().putString("username", user.getUsername()).apply();
                preferences.edit().putString("email", user.getEmail()).apply();
                preferences.edit().putLong("uID", user.getId()).apply();
            } else {
                navController.navigate(R.id.loginFragment);
                bottomNavigationView.setVisibility(View.INVISIBLE);
                preferences.edit().putString("username", null).apply();
                preferences.edit().putString("email", null).apply();
                preferences.edit().putLong("uID", -1).apply();
            }
        });
    }

    private void registerNotificationToken(User user) {
       token.observe(this, tokenForNotification -> {
           if (tokenForNotification != null) {
               Log.i("token", "Token: " + tokenForNotification);
               viewModel.registerToken(user.getId(), tokenForNotification);
           }
       });
    }
}