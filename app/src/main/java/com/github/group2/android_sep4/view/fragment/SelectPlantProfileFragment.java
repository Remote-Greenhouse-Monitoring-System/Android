package com.github.group2.android_sep4.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.view.adapter.PlantProfileAdapter;
import com.github.group2.android_sep4.viewmodel.PlantProfileViewModel;
import com.github.group2.android_sep4.viewmodel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class SelectPlantProfileFragment extends Fragment {

    private ImageButton backButton;
    private NavController navController;
    private RecyclerView plantProfileRecyclerView;
    private PlantProfileAdapter plantProfileAdapter;
    private FloatingActionButton addPlantProfileButton;
    private ArrayList<PlantProfile> userPlantProfiles = new ArrayList<>();
    private PlantProfileViewModel plantProfileViewModel;

    public SelectPlantProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_plant_profile, container, false);
        plantProfileViewModel = new PlantProfileViewModel();
        plantProfileViewModel.searchPlantProfilesForUser(plantProfileViewModel.getCurrentUser().getValue().getId());
        initializeViews(view);
        final Observer<List<PlantProfile>> plantProfilesObserver = new Observer<List<PlantProfile>>() {
            @Override
            public void onChanged(List<PlantProfile> plantProfiles) {
                userPlantProfiles.clear();
                userPlantProfiles.addAll(plantProfiles);
                plantProfileAdapter = new PlantProfileAdapter(userPlantProfiles);
                plantProfileRecyclerView.setAdapter(plantProfileAdapter);
            }
        };
        plantProfileViewModel.getPlantProfileForUser().observe(getViewLifecycleOwner(),plantProfilesObserver);

        return view;
    }

    private void initializeViews(View view) {
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
        plantProfileRecyclerView= view.findViewById(R.id.plantProfileRecyclerView);
        addPlantProfileButton = view.findViewById(R.id.addPlantProfileButton);
        addPlantProfileButton.setOnClickListener(this::goAddPlantProfile);
        plantProfileRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(this::goBack);
    }

    public void goAddPlantProfile(View view) {
        navController.navigate(R.id.addPlantProfileFragment);
    }



    private void goBack(View view) {
        navController.navigate(R.id.homeFragment);
    }

}