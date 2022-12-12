package com.github.group2.android_sep4.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.model.Threshold;
import com.github.group2.android_sep4.viewmodel.EditPlantProfileViewModel;
import com.shashank.sony.fancytoastlib.FancyToast;


public class EditPlantProfileFragment extends Fragment {

    private ImageButton backButton;
    private NavController navController;
    private EditText editPlantProfileName, editPlantProfileDescription, editPlantProfileTempOptimal, editPlantProfileHumidityOptimal,
            editPlantProfileCO2Optimal, editPlantProfileLightOptimal, editPlantProfileTempMin, editPlantProfileTempMax, editPlantProfileCO2Min,
            editPlantProfileCO2Max, editPlantProfileHumidityMin, editPlantProfileHumidityMax;
    private Button editPlantProfileButton;
    private EditPlantProfileViewModel viewModel;
    private PlantProfile plantProfileToEdit;
    private Threshold thresHoldToEdit;


    public EditPlantProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_plant_profile, container, false);
        initializeViews(view);

        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
        backButton.setOnClickListener(this::goBack);
        editPlantProfileButton.setOnClickListener(this::editPressed);


        viewModel.getPlantProfileToEdit().observe(getViewLifecycleOwner(), this::updatePlantFields);

        return view;

    }

    private void updatePlantFields(PlantProfile plantProfile) {

        if (plantProfile != null) {
            viewModel.searchForThresholds(plantProfile.getId());

            this.plantProfileToEdit = plantProfile;
            editPlantProfileName.setText(plantProfile.getName());
            editPlantProfileDescription.setText(plantProfile.getDescription());
            ;
            editPlantProfileTempOptimal.setText(String.valueOf(plantProfile.getOptimalTemperature()));
            ;
            editPlantProfileHumidityOptimal.setText(String.valueOf(plantProfile.getOptimalHumidity()));
            editPlantProfileCO2Optimal.setText(String.valueOf(plantProfile.getOptimalCo2()));
            editPlantProfileLightOptimal.setText(String.valueOf(plantProfile.getOptimalLight()));
            viewModel.getSearchedThreshold().observe(getViewLifecycleOwner(), this::updateThresholdFields);


        }
    }

    private void updateThresholdFields(Threshold threshold) {
        if (threshold != null) {
            this.thresHoldToEdit = threshold;
            editPlantProfileTempMin.setText(String.valueOf(threshold.getTemperatureMin()));
            editPlantProfileTempMax.setText(String.valueOf(threshold.getTemperatureMax()));
            editPlantProfileCO2Min.setText(String.valueOf(threshold.getCo2Min()));
            editPlantProfileCO2Max.setText(String.valueOf(threshold.getCo2Max()));
            editPlantProfileHumidityMin.setText(String.valueOf(threshold.getHumidityMin()));
            editPlantProfileHumidityMax.setText(String.valueOf(threshold.getHumidityMax()));
        }

    }

    private void successObserver(String s) {
        if (s != null) {
            FancyToast.makeText(getContext(), s, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
            viewModel.resetMessages();
            navController.navigate(R.id.selectPlantProfileFragment);
        }
    }

    private void errorObserver(String s) {
        if (s != null) {
            FancyToast.makeText(getContext(), s, FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
            viewModel.resetMessages();

        }
    }

    private void editPressed(View view) {
        boolean validName = validateName();
        boolean validDescription = validateDescription();

        boolean validCo2 = validateCo2();
        boolean validateThresholdCo2 = validateThresholdCo2();

        boolean validHumidity = validateHumidity();
        boolean validateThresholdHumidity = validateThresholdHumidity();

        boolean validTemp = validateTemp();
        boolean validateThresholdTemp = validateThresholdTemp();

        boolean validLight = validateLight();

        boolean isEverythingValid = validName && validDescription && validCo2 && validateThresholdCo2 && validHumidity && validateThresholdHumidity && validTemp && validateThresholdTemp && validLight;

        if (!isEverythingValid) {
            return;
        }


        String name = editPlantProfileName.getText().toString();
        String description = editPlantProfileDescription.getText().toString();
        float optimalTemp = Float.parseFloat(editPlantProfileTempOptimal.getText().toString());
        float optimalHumidity = Float.parseFloat(editPlantProfileHumidityOptimal.getText().toString());
        float optimalCo2 = Float.parseFloat(editPlantProfileCO2Optimal.getText().toString());
        int optimalLight = Integer.parseInt(editPlantProfileLightOptimal.getText().toString());
        float minTemp = Float.parseFloat(editPlantProfileTempMin.getText().toString());
        float maxTemp = Float.parseFloat(editPlantProfileTempMax.getText().toString());
        float minHumidity = Float.parseFloat(editPlantProfileHumidityMin.getText().toString());
        float maxHumidity = Float.parseFloat(editPlantProfileHumidityMax.getText().toString());
        float minCo2 = Float.parseFloat(editPlantProfileCO2Min.getText().toString());
        float maxCo2 = Float.parseFloat(editPlantProfileCO2Max.getText().toString());

        Threshold threshold = new Threshold(maxTemp, minTemp, maxHumidity, minHumidity, maxCo2, minCo2);
        threshold.setId(0);
        PlantProfile plantProfile = new PlantProfile(plantProfileToEdit.getId(), name, description, optimalTemp, optimalHumidity, optimalCo2, optimalLight);

        viewModel.updatePlantProfile(plantProfile, threshold);
        viewModel.getPlantProfileError().observe(getViewLifecycleOwner(),
                this::errorObserver);
        viewModel.getPlantProfileSuccess().observe(getViewLifecycleOwner(),
                this::successObserver);

    }


    private boolean validateThresholdCo2() {
        String minCo2 = editPlantProfileCO2Min.getText().toString();
        String maxCo2 = editPlantProfileCO2Max.getText().toString();
        String optimalCo2 = editPlantProfileCO2Optimal.getText().toString();

        if (minCo2.isEmpty() || maxCo2.isEmpty() || optimalCo2.isEmpty()) {
            FancyToast.makeText(getContext(), "Please fill all required fields", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
            return false;
        }

        Float minCo2Float = Float.parseFloat(minCo2);
        Float maxCo2Float = Float.parseFloat(maxCo2);
        Float optimalCo2Float = Float.parseFloat(optimalCo2);

        if (minCo2Float > optimalCo2Float) {
            FancyToast.makeText(getContext(), "Min CO2 cannot be higher than optimal CO2", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
            return false;
        } else if (maxCo2Float < optimalCo2Float) {
            FancyToast.makeText(getContext(), "Max CO2 cannot be lower than optimal CO2", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
            return false;
        } else if (minCo2Float > maxCo2Float) {
            FancyToast.makeText(getContext(), "Min CO2 cannot be higher than max CO2", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
            return false;
        }
        return true;
    }

    private boolean validateThresholdTemp() {
        String minTemp = editPlantProfileTempMin.getText().toString();
        String maxTemp = editPlantProfileTempMax.getText().toString();
        String optimalTemp = editPlantProfileTempOptimal.getText().toString();

        if (minTemp.isEmpty()) {
            editPlantProfileTempMin.setError("Field can't be empty");
            return false;
        } else if (maxTemp.isEmpty()) {
            editPlantProfileTempMax.setError("Field can't be empty");
            return false;
        } else if (optimalTemp.isEmpty()) {
            editPlantProfileTempOptimal.setError("Field can't be empty");
            return false;
        } else {
            editPlantProfileTempMin.setError(null);
            editPlantProfileTempMax.setError(null);
            editPlantProfileTempOptimal.setError(null);
        }

        Float minTempFloat = Float.parseFloat(minTemp);
        Float maxTempFloat = Float.parseFloat(maxTemp);
        Float optimalTempFloat = Float.parseFloat(optimalTemp);

        if (minTempFloat > optimalTempFloat) {
            editPlantProfileTempMin.setError("Min temp can't be higher than optimal temp");
            return false;
        } else if (maxTempFloat < optimalTempFloat) {
            editPlantProfileTempMax.setError("Max temp can't be lower than optimal temp");
            return false;
        } else {
            editPlantProfileTempMin.setError(null);
            editPlantProfileTempMax.setError(null);
            editPlantProfileTempOptimal.setError(null);
        }

        return true;
    }

    private boolean validateThresholdHumidity() {

        String minHumidity = editPlantProfileHumidityMin.getText().toString();
        String maxHumidity = editPlantProfileHumidityMax.getText().toString();
        String optimalHumidity = editPlantProfileHumidityOptimal.getText().toString();
        if (minHumidity.isEmpty() || maxHumidity.isEmpty()) {
            FancyToast.makeText(getContext(), "Please fill all required fields", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
            return false;
        }

        Float minHumidityFloat = Float.parseFloat(minHumidity);
        Float maxHumidityFloat = Float.parseFloat(maxHumidity);
        Float optimalHumidityFloat = Float.parseFloat(optimalHumidity);

        if (minHumidityFloat > optimalHumidityFloat) {
            FancyToast.makeText(getContext(), "Min Humidity cannot be higher than optimal Humidity", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
            return false;
        } else if (maxHumidityFloat < optimalHumidityFloat) {
            FancyToast.makeText(getContext(), "Max Humidity cannot be lower than optimal Humidity", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
            return false;
        }
        return true;


    }

    private boolean validateLight() {
        String light = editPlantProfileLightOptimal.getText().toString();
        if (light.isEmpty()) {
            editPlantProfileLightOptimal.setError("Light is required");
            return false;
        } else {
            editPlantProfileLightOptimal.setError(null);
            return true;
        }
    }

    private boolean validateCo2() {
        String co2 = editPlantProfileCO2Optimal.getText().toString();
        if (co2.isEmpty()) {
            editPlantProfileCO2Optimal.setError("Field can't be empty");
            return false;
        } else {
            editPlantProfileCO2Optimal.setError(null);
            return true;
        }
    }

    private boolean validateHumidity() {
        String humidity = editPlantProfileHumidityOptimal.getText().toString();
        if (humidity.isEmpty()) {
            editPlantProfileHumidityOptimal.setError("Field can't be empty");
            return false;
        } else if (Float.parseFloat(humidity) < 0 || Float.parseFloat(humidity) > 100) {
            editPlantProfileHumidityOptimal.setError("Humidity must be between 0 and 100");
            return false;
        } else {
            editPlantProfileHumidityOptimal.setError(null);
            return true;
        }
    }

    private boolean validateTemp() {
        String temp = editPlantProfileTempOptimal.getText().toString();
        if (temp.isEmpty()) {
            editPlantProfileTempOptimal.setError("Field can't be empty");
            return false;
        } else if (Float.parseFloat(temp) < 0 || Float.parseFloat(temp) > 50) {
            editPlantProfileTempOptimal.setError("Temperature must be between 0 and 50");
            return false;
        } else {
            editPlantProfileTempOptimal.setError(null);
            return true;
        }
    }

    private boolean validateName() {
        String name = editPlantProfileName.getText().toString();
        if (name.isEmpty()) {
            editPlantProfileName.setError("Field can't be empty");
            return false;
        } else {
            editPlantProfileName.setError(null);
            return true;
        }
    }

    private boolean validateDescription() {
        String description = editPlantProfileDescription.getText().toString();
        if (description.isEmpty()) {
            editPlantProfileDescription.setError("Field can't be empty");
            return false;
        } else {
            editPlantProfileDescription.setError(null);
            return true;
        }
    }


    private void initializeViews(View view) {
        viewModel = new ViewModelProvider(this).get(EditPlantProfileViewModel.class);
        editPlantProfileName = (EditText) view.findViewById(R.id.editPlantProfileName);
        editPlantProfileDescription = (EditText) view.findViewById(R.id.editPlantProfileDescription);
        editPlantProfileTempOptimal = (EditText) view.findViewById(R.id.editPlantProfileTemp);
        editPlantProfileHumidityOptimal = (EditText) view.findViewById(R.id.editPlantProfileHumidity);
        editPlantProfileCO2Optimal = (EditText) view.findViewById(R.id.editPlantProfileCO2);
        editPlantProfileLightOptimal = (EditText) view.findViewById(R.id.editPlantProfileLight);
        editPlantProfileTempMin = (EditText) view.findViewById(R.id.editPlantProfileTempMin);
        editPlantProfileTempMax = (EditText) view.findViewById(R.id.editPlantProfileTempMax);
        editPlantProfileCO2Min = (EditText) view.findViewById(R.id.editPlantProfileCO2Min);
        editPlantProfileCO2Max = (EditText) view.findViewById(R.id.editPlantProfileCO2Max);
        editPlantProfileHumidityMin = (EditText) view.findViewById(R.id.editPlantProfileHumidityMin);
        editPlantProfileHumidityMax = (EditText) view.findViewById(R.id.editPlantProfileHumidityMax);
        backButton = view.findViewById(R.id.editPlantProfileBackButton);
        editPlantProfileButton = (Button) view.findViewById(R.id.confirmEditPlantProfileButton);
    }


    private void goBack(View view) {
        navController.popBackStack();
    }



}