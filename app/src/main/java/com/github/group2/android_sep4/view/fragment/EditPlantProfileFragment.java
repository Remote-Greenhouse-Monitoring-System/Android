package com.github.group2.android_sep4.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.model.Threshold;
import com.github.group2.android_sep4.viewmodel.PlantProfileViewModel;
import com.github.group2.android_sep4.viewmodel.UserViewModel;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Observable;

public class EditPlantProfileFragment extends Fragment {

    private ImageButton backButton;
    private NavController navController;
    private EditText editPlantProfileName, editPlantProfileDescription,editPlantProfileTemp,editPlantProfileHumidity,
            editPlantProfileCO2,editPlantProfileLight,editPlantProfileTempMin,editPlantProfileTempMax,editPlantProfileCO2Min,
            editPlantProfileCO2Max,editPlantProfileHumidityMin,editPlantProfileHumidityMax;
    private Button editPlantProfileButton;
    private PlantProfileViewModel plantProfileViewModel;

    private PlantProfile plantProfileFound;
    private Threshold thresholdFound;

    public EditPlantProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_plant_profile, container, false);
        initializeViews(view);

        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
        backButton.setOnClickListener(this::goBack);
        editPlantProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!checkFieldsForEdit())
                {
                    displayFieldErrors();
                    FancyToast.makeText(getContext(), "Please fill all required fields", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                }
                else
                {
                    String name = editPlantProfileName.getText().toString();
                    String description = editPlantProfileDescription.getText().toString();
                    float optimalTemp = Float.parseFloat(editPlantProfileTemp.getText().toString());
                    float optimalHumidity = Float.parseFloat(editPlantProfileHumidity.getText().toString());
                    float optimalCo2 = Float.parseFloat(editPlantProfileCO2.getText().toString());
                    int optimalLight = Integer.parseInt(editPlantProfileLight.getText().toString());
                    float minTemp = Float.parseFloat(editPlantProfileTempMin.getText().toString());
                    float maxTemp = Float.parseFloat(editPlantProfileTempMax.getText().toString());
                    float minHumidity = Float.parseFloat(editPlantProfileHumidityMin.getText().toString());
                    float maxHumidity = Float.parseFloat(editPlantProfileHumidityMax.getText().toString());
                    float minCo2 = Float.parseFloat(editPlantProfileCO2Min.getText().toString());
                    float maxCo2 = Float.parseFloat(editPlantProfileCO2Max.getText().toString());

                    Threshold threshold = new Threshold(maxTemp,minTemp,maxHumidity,minHumidity,maxCo2,minCo2,1,1);
                    PlantProfile plantProfile = new PlantProfile(plantProfileFound.getId(),name,description,optimalTemp,optimalHumidity,optimalCo2,optimalLight);

                    plantProfileViewModel.updatePlantProfile(plantProfile);
                    plantProfileViewModel.updateThreshold(plantProfile.getId(),threshold);
                    plantProfileViewModel.searchPlantProfilesForUser(plantProfileViewModel.getCurrentUser().getValue().getId());
                    FancyToast.makeText(getContext(), name +" updated successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                    navController.navigate(R.id.selectPlantProfileFragment);
                }




            }
        });

        final Observer<PlantProfile> plantProfileObserver = new Observer<PlantProfile>() {
            @Override
            public void onChanged(PlantProfile plantProfile) {
                plantProfileFound = plantProfile;
                initializePlantFields();
            }
        };

        final Observer<Threshold> thresholdObserver = new Observer<Threshold>() {
            @Override
            public void onChanged(Threshold threshold) {
                thresholdFound = threshold;
                initializeThresholdFields();
            }
        };

        plantProfileViewModel.getPlantProfile().observe(getViewLifecycleOwner(),plantProfileObserver);
        plantProfileViewModel.getSearchedThreshold().observe(getViewLifecycleOwner(),thresholdObserver);


        return view;

    }

    private void initializePlantFields() {

        editPlantProfileName.setText(plantProfileFound.getName());
        editPlantProfileDescription.setText(plantProfileFound.getDescription()); ;
        editPlantProfileTemp.setText(String.valueOf(plantProfileFound.getOptimalTemp())); ;
        editPlantProfileHumidity.setText(String.valueOf(plantProfileFound.getOptimalHumidity())) ;
        editPlantProfileCO2.setText(String.valueOf(plantProfileFound.getOptimalCo2())) ;
        editPlantProfileLight.setText(String.valueOf(plantProfileFound.getOptimalLight()));


    }

    private void initializeThresholdFields()
    {
        editPlantProfileTempMin.setText(String.valueOf(thresholdFound.getMinTemperature())) ;
        editPlantProfileTempMax.setText(String.valueOf(thresholdFound.getMaxTemperature())) ;
        editPlantProfileCO2Min.setText(String.valueOf(thresholdFound.getMinCo2())) ;
        editPlantProfileCO2Max.setText(String.valueOf(thresholdFound.getMaxCo2())) ;
        editPlantProfileHumidityMin.setText(String.valueOf(thresholdFound.getMinHumidity())) ;
        editPlantProfileHumidityMax.setText(String.valueOf(thresholdFound.getMaxHumidity())) ;
    }

    private void initializeViews(View view)
    {
        plantProfileViewModel = new PlantProfileViewModel();
        editPlantProfileName =  (EditText) view.findViewById(R.id.editPlantProfileName);
        editPlantProfileDescription= (EditText) view.findViewById(R.id.editPlantProfileDescription);
        editPlantProfileTemp= (EditText) view.findViewById(R.id.editPlantProfileTemp);
        editPlantProfileHumidity= (EditText) view.findViewById(R.id.editPlantProfileHumidity);
        editPlantProfileCO2= (EditText) view.findViewById(R.id.editPlantProfileCO2);
        editPlantProfileLight = (EditText) view.findViewById(R.id.editPlantProfileLight);
        editPlantProfileTempMin= (EditText) view.findViewById(R.id.editPlantProfileTempMin);
        editPlantProfileTempMax= (EditText) view.findViewById(R.id.editPlantProfileTempMax);
        editPlantProfileCO2Min= (EditText) view.findViewById(R.id.editPlantProfileCO2Min);
        editPlantProfileCO2Max= (EditText) view.findViewById(R.id.editPlantProfileCO2Max);
        editPlantProfileHumidityMin= (EditText) view.findViewById(R.id.editPlantProfileHumidityMin);
        editPlantProfileHumidityMax= (EditText) view.findViewById(R.id.editPlantProfileHumidityMax);
        backButton =  view.findViewById(R.id.editPlantProfileBackButton);
        editPlantProfileButton= (Button) view.findViewById(R.id.confirmEditPlantProfileButton);
    }

    private boolean checkFieldsForEdit()
    {
        boolean empty = true;

        if(editPlantProfileName.getText().toString().matches("")  && editPlantProfileDescription.getText().toString().matches("")  &&
                editPlantProfileTemp.getText().toString().matches("")  &&editPlantProfileHumidity.getText().toString().matches("")  &&
                editPlantProfileCO2.getText().toString().matches("")  &&editPlantProfileLight.getText().toString().matches("")  &&
                editPlantProfileTempMin.getText().toString().matches("")  &&editPlantProfileTempMax.getText().toString().matches("")  &&
                editPlantProfileCO2Min.getText().toString().matches("")  &&editPlantProfileCO2Max.getText().toString().matches("")  &&
                editPlantProfileHumidityMin.getText().toString().matches("")  &&editPlantProfileHumidityMax.getText().toString().matches(""))
        {
            empty = false;
        }

        return empty;
    }
    private void goBack(View view) {
        navController.popBackStack();
    }

    private void displayFieldErrors()
    {
        editPlantProfileName.setError("This field cannot be blank");
        editPlantProfileDescription.setError("This field cannot be blank");
        editPlantProfileTemp.setError("This field cannot be blank");
        editPlantProfileHumidity.setError("This field cannot be blank");
        editPlantProfileCO2.setError("This field cannot be blank");
        editPlantProfileLight.setError("This field cannot be blank");
        editPlantProfileTempMin.setError("This field cannot be blank");
        editPlantProfileTempMax.setError("This field cannot be blank");
        editPlantProfileCO2Min.setError("This field cannot be blank");
        editPlantProfileCO2Max.setError("This field cannot be blank");
        editPlantProfileHumidityMin.setError("This field cannot be blank");
        editPlantProfileHumidityMax.setError("This field cannot be blank");

    }


}