package com.github.group2.android_sep4.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
import android.widget.Toast;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.view.adapter.PlantProfileAdapter;
import com.github.group2.android_sep4.viewmodel.GreenhouseSpecificViewModel;
import com.github.group2.android_sep4.viewmodel.PlantProfileViewModel;
import com.github.group2.android_sep4.viewmodel.UserViewModel;
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
    private ArrayList<PlantProfile> tempPlantProfiles = new ArrayList<>();
    private PlantProfileViewModel plantProfileViewModel;
    private UserViewModel userViewModel;
    private GreenhouseSpecificViewModel greenhouseSpecificViewModel;
    private int greenHouseId=-1;

    public SelectPlantProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_plant_profile, container, false);
        plantProfileViewModel = new ViewModelProvider(this).get(PlantProfileViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        plantProfileViewModel.searchPlantProfilesForUser(userViewModel.getCurrentUser().getValue().getId());
        greenhouseSpecificViewModel = new ViewModelProvider(this).get(GreenhouseSpecificViewModel.class);
        initializeViews(view);
        setAdapter();
        Bundle bundle = getArguments();

        if(bundle!=null){
            greenHouseId = (int) bundle.getLong("activeGreenhouseId");
        }
        checkIfGreenHouseIdIsSet();
        return view;
    }


    private void checkIfGreenHouseIdIsSet() {
        if(greenHouseId!=-1){
            plantProfileAdapter.setOnItemClickListener(this::plantProfileClicked);

        }
        else{

        }
    }

    private void initializeViews(View view) {
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);


        tempPlantProfiles.add(new PlantProfile(1, "Potato #1", "Description 1", 20, 25,320,2000));
        tempPlantProfiles.add(new PlantProfile(2, "Tomato", "Lorem ipsum dolor sit amet", 20, 25,320,10000));
        tempPlantProfiles.add(new PlantProfile(3, "Cucumber", "The quick brown fox jumps over the lazy dog", 22, 77,295,12000));
        tempPlantProfiles.add(new PlantProfile(4, "Potato #2", "Description 4", 20, 25,320,2000));
        tempPlantProfiles.add(new PlantProfile(5, "Plant Profile 5", "Description 5", 20, 25,320,2000));
        tempPlantProfiles.add(new PlantProfile(6, "Plant Profile 6", "Description 6", 20, 25,320,2000));

       // plantProfileAdapter = new PlantProfileAdapter(userPlantProfiles);

        plantProfileRecyclerView= view.findViewById(R.id.plantProfileRecyclerView);
        addPlantProfileButton = view.findViewById(R.id.addPlantProfileButton);
        addPlantProfileButton.setOnClickListener(this::goAddPlantProfile);
        plantProfileRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //plantProfileRecyclerView.setAdapter(plantProfileAdapter);
        Button buttonConfirm = view.findViewById(R.id.confirmDeleteButtonPlantProfile);
        backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(this::goBack);
    }

    public void goAddPlantProfile(View view) {
        navController.navigate(R.id.addPlantProfileFragment);
    }

    private void setAdapter() {
        plantProfileAdapter = new PlantProfileAdapter();

        plantProfileRecyclerView.setAdapter(plantProfileAdapter);


        plantProfileViewModel.getPlantProfileForUser().observe(getViewLifecycleOwner(), plantProfiles -> {
            if(plantProfiles!=null){
                plantProfileAdapter.setPlantProfiles(plantProfiles);
            }

        });

    }

    private void goBack(View view) {
        navController.navigate(R.id.homeFragment);
    }

    private void plantProfileClicked(PlantProfile plantProfile) {
        plantProfileViewModel.activatePlantProfile(plantProfile.getId(), greenhouseSpecificViewModel.getSelectedGreenhouse().getValue().getId());
        Toast.makeText(getContext(), "Plant profile set "+plantProfile.getId()+" to greenghouse"+greenhouseSpecificViewModel.getSelectedGreenhouse().getValue().getId(), Toast.LENGTH_SHORT).show();
        navController.popBackStack();
    }
}