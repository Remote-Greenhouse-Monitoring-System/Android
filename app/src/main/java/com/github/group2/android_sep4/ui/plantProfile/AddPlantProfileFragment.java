package com.github.group2.android_sep4.ui.plantProfile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.entity.PlantProfile;
import com.github.group2.android_sep4.entity.Threshold;

public class AddPlantProfileFragment extends Fragment {
    private ImageButton backButton;
    private NavController navController;
    private EditText addPlantProfileName, addPlantProfileDescription,addPlantProfileTemp,addPlantProfileHumidity,
            addPlantProfileCO2,addPlantProfileTempMin,addPlantProfileTempMax,addPlantProfileCO2Min,
            addPlantProfileCO2Max,addPlantProfileHumidityMin,addPlantProfileHumidityMax;
    private Button addPlantProfileButton;
    private PlantProfileViewModel plantProfileViewModel;

    public AddPlantProfileFragment() {
        // Required empty public constructor
    }

    public static AddPlantProfileFragment newInstance(String param1, String param2) {
        AddPlantProfileFragment fragment = new AddPlantProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_plant_profile, container, false);
        initializeViews(view);
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
        backButton.setOnClickListener(this::goBack);
        addPlantProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = addPlantProfileName.getText().toString();
                String description = addPlantProfileDescription.getText().toString();
                float optimalTemp = Float.parseFloat(addPlantProfileTemp.getText().toString());
                float optimalHumidity = Float.parseFloat(addPlantProfileHumidity.getText().toString());
                float optimalCo2 = Float.parseFloat(addPlantProfileCO2.getText().toString());
                float minTemp = Float.parseFloat(addPlantProfileTempMin.getText().toString());
                float maxTemp = Float.parseFloat(addPlantProfileTempMax.getText().toString());
                float minHumidity = Float.parseFloat(addPlantProfileHumidityMin.getText().toString());
                float maxHumidity = Float.parseFloat(addPlantProfileHumidityMax.getText().toString());
                float minCo2 = Float.parseFloat(addPlantProfileCO2Min.getText().toString());
                float maxCo2 = Float.parseFloat(addPlantProfileCO2Max.getText().toString());
                Threshold threshold = new Threshold(maxTemp,minTemp,maxHumidity,minHumidity,maxCo2,minCo2,1,1);
                PlantProfile plantProfile = new PlantProfile(0,name,description,optimalTemp,optimalHumidity,optimalCo2,22);

                plantProfileViewModel.createPlantProfile(plantProfile);
                System.out.println("ON FRAGMENT -->" + plantProfile.toString());
                plantProfileViewModel.getPlantProfileById(12);
            }
        });
        return view;
    }

    private void goBack(View view) {
        navController.popBackStack();
    }

    private void initializeViews(View view)
    {
        plantProfileViewModel = new PlantProfileViewModel();
        addPlantProfileName = (EditText) view.findViewById(R.id.addPlantProfileName);
        addPlantProfileDescription = (EditText) view.findViewById(R.id.addPlantProfileDescription);
        addPlantProfileTemp = (EditText) view.findViewById(R.id.addPlantProfileTemp);
        addPlantProfileHumidity = (EditText) view.findViewById(R.id.addPlantProfileHumidity);
        addPlantProfileCO2 = (EditText) view.findViewById(R.id.addPlantProfileCO2);
        addPlantProfileTempMin = (EditText) view.findViewById(R.id.addPlantProfileTempMin);
        addPlantProfileTempMax = (EditText) view.findViewById(R.id.addPlantProfileTempMax);
        addPlantProfileCO2Min = (EditText) view.findViewById(R.id.addPlantProfileCO2Min);
        addPlantProfileCO2Max = (EditText) view.findViewById(R.id.addPlantProfileCO2Max);
        addPlantProfileHumidityMin = (EditText) view.findViewById(R.id.addPlantProfileHumidityMin);
        addPlantProfileHumidityMax = (EditText)view.findViewById(R.id.addPlantProfileHumidityMax);
        backButton = view.findViewById(R.id.addPlantProfileBackButton);
        addPlantProfileButton = (Button) view.findViewById(R.id.addPlantProfileButton);
    }
}