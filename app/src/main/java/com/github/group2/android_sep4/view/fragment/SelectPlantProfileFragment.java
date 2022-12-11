package com.github.group2.android_sep4.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.view.adapter.PlantProfileAdapter;
import com.github.group2.android_sep4.viewmodel.SelectPlantProfileViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class SelectPlantProfileFragment extends Fragment {

    View view;
    private ImageButton backButton;
    private NavController navController;
    private RecyclerView plantProfileRecyclerView;
    private PlantProfileAdapter plantProfileAdapter;
    private FloatingActionButton addPlantProfileButton;
    private SelectPlantProfileViewModel viewModel;
    private boolean isFromSpecificGreenhouse=false;


    public SelectPlantProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_plant_profile, container, false);
        viewModel = new ViewModelProvider(this).get(SelectPlantProfileViewModel.class);

        initializeViews(view);
        setAdapter();

        Bundle bundle = getArguments();
        if (bundle!=null) {
            isFromSpecificGreenhouse = bundle.getBoolean("isFromSpecificGreenhouse");
        }

        checkIfGreenHouseIdIsSet();



        return view;
    }

    private void checkIfGreenHouseIdIsSet() {
        if(isFromSpecificGreenhouse) {
            plantProfileAdapter.setOnItemClickListener(this::plantProfileClicked);
        }
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

    private void setAdapter() {
        plantProfileAdapter = new PlantProfileAdapter();
        plantProfileRecyclerView.setAdapter(plantProfileAdapter);

        viewModel.getPlantProfilesForUser().observe(getViewLifecycleOwner(), plantProfiles -> {
            if (plantProfiles != null) {
                plantProfileAdapter.setPlantProfiles(plantProfiles);
            }
        });
    }

    private void goBack(View view) {
        navController.navigate(R.id.homeFragment);
    }

    private void plantProfileClicked(PlantProfile plantProfile) {
        viewModel.activatePlantProfile(plantProfile.getId());
        Toast.makeText(getContext(), "Plant profile set " + plantProfile.getId() + " to greenhouse " + viewModel.getSelectedGreenhouse().getValue().getId(), Toast.LENGTH_SHORT).show();
        navController.popBackStack();
    }
}