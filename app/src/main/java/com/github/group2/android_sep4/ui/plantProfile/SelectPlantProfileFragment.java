package com.github.group2.android_sep4.ui.plantProfile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.entity.PlantProfile;

import java.util.ArrayList;


public class SelectPlantProfileFragment extends Fragment {

    View view;
    private ImageButton backButton;
    private NavController navController;
    private RecyclerView plantProfileRecyclerView;
    private PlantProfileAdapter plantProfileAdapter;

    private ArrayList<PlantProfile> tempPlantProfiles = new ArrayList<>();
    public SelectPlantProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_plant_profile, container, false);
        initializeViews(view);

        return view;
    }

    private void initializeViews(View view) {
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);


        tempPlantProfiles.add(new PlantProfile(1, "Potato #1", "Description 1", 20, 25,320,2000));
        tempPlantProfiles.add(new PlantProfile(2, "Tomato", "Lorem ipsum dolor sit amet", 20, 25,320,10000));
        tempPlantProfiles.add(new PlantProfile(3, "Cucumber", "The quick brown fox jumps over the lazy dog", 22, 77,295,12000));
        tempPlantProfiles.add(new PlantProfile(4, "Potato #2", "Description 4", 20, 25,320,2000));
        tempPlantProfiles.add(new PlantProfile(5, "Plant Profile 5", "Description 5", 20, 25,320,2000));
        tempPlantProfiles.add(new PlantProfile(6, "Plant Profile 6", "Description 6", 20, 25,320,2000));

        plantProfileAdapter = new PlantProfileAdapter(tempPlantProfiles);

        plantProfileRecyclerView= view.findViewById(R.id.plantProfileRecyclerView);

        plantProfileRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        plantProfileRecyclerView.setAdapter(plantProfileAdapter);

        backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(this::goBack);




        setAdapter();
    }

    private void setAdapter() {


    }

    private void goBack(View view) {
        navController.navigate(R.id.homeFragment);
    }


}