package com.github.group2.android_sep4.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.github.group2.android_sep4.R;

public class MainActivity extends AppCompatActivity {



    private MainActivityViewModel viewModel;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeAllFields();
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        checkIfSignedIn();
    }

    private void initializeAllFields() {
        navController = Navigation.findNavController(this, R.id.fragment_container);



    }

    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user ->{
            if (user==null){
                displayLogin();
            }

        });

    }

    private void displayLogin() {
        navController.navigate(R.id.loginFragment);

    }
}