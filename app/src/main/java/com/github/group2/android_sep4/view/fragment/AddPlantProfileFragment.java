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
import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.viewmodel.AddPlantProfileViewModel;
import com.shashank.sony.fancytoastlib.FancyToast;

public class AddPlantProfileFragment extends Fragment {

    private ImageButton backButton;
    private NavController navController;
    private EditText addPlantProfileName, addPlantProfileDescription, addPlantProfileTempOptimal, addPlantProfileHumidityOptimal,
            addPlantProfileCO2, addPlantProfileLightOptimal, addPlantProfileTempMin, addPlantProfileTempMax, addPlantProfileCO2Min,
            addPlantProfileCO2Max, addPlantProfileHumidityMin, addPlantProfileHumidityMax;
    private Button addPlantProfileButton;
    private AddPlantProfileViewModel viewModel;

    public AddPlantProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_plant_profile, container, false);
        initializeViews(view);
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
        backButton.setOnClickListener(this::goBack);

        addPlantProfileButton.setOnClickListener(v -> addPlantProfile(view));

        return view;
    }

    private void addPlantProfile(View view) {
        boolean validName = validateName();
        boolean validateDescription = validateDescription();
        boolean validateTemp = validateTemp();
        boolean validateHumidity = validateHumidity();
        boolean validateCo2 = validateCo2();
        boolean validateLight = validateLight();
        boolean validateThresholdTemp = validateThresholdTemp();
        boolean validateThresholdHumidity = validateThresholdHumidity();
        boolean validateThresholdCo2 = validateThresholdCo2();

        boolean isEverythingValid = validName && validateDescription && validateTemp && validateHumidity && validateCo2 && validateLight && validateThresholdTemp && validateThresholdHumidity && validateThresholdCo2;

        if (!isEverythingValid) {
            return;
        }

        String name = addPlantProfileName.getText().toString();
        String description = addPlantProfileDescription.getText().toString();

        float optimalTemp = Float.parseFloat(addPlantProfileTempOptimal.getText().toString());
        float optimalHumidity = Float.parseFloat(addPlantProfileHumidityOptimal.getText().toString());
        float optimalCo2 = Float.parseFloat(addPlantProfileCO2.getText().toString());
        int optimalLight = Integer.parseInt(addPlantProfileLightOptimal.getText().toString());
        float minTemp = Float.parseFloat(addPlantProfileTempMin.getText().toString());
        float maxTemp = Float.parseFloat(addPlantProfileTempMax.getText().toString());
        float minHumidity = Float.parseFloat(addPlantProfileHumidityMin.getText().toString());
        float maxHumidity = Float.parseFloat(addPlantProfileHumidityMax.getText().toString());
        float minCo2 = Float.parseFloat(addPlantProfileCO2Min.getText().toString());
        float maxCo2 = Float.parseFloat(addPlantProfileCO2Max.getText().toString());

        Threshold threshold = new Threshold(maxTemp, minTemp, maxHumidity, minHumidity, maxCo2, minCo2);
        PlantProfile plantProfile = new PlantProfile(0, name, description, optimalTemp, optimalHumidity, optimalCo2, optimalLight);
        User user = viewModel.getCurrentUser().getValue();

        if (user != null) {
            viewModel.addPlantProfile(user.getId(), plantProfile, threshold);
            viewModel.searchPlantProfilesForUser(user.getId());
        }
        FancyToast.makeText(getContext(), name + " added successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();

        goBack(view);
    }

    private boolean validateThresholdCo2() {
        String minCo2 = addPlantProfileCO2Min.getText().toString();
        String maxCo2 = addPlantProfileCO2Max.getText().toString();
        String optimalCo2 = addPlantProfileCO2.getText().toString();

        if (minCo2.isEmpty() || maxCo2.isEmpty() || optimalCo2.isEmpty()) {
            FancyToast.makeText(getContext(), "Please fill all required fields", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
            return false;
        }

        float minCo2Float = Float.parseFloat(minCo2);
        float maxCo2Float = Float.parseFloat(maxCo2);
        float optimalCo2Float = Float.parseFloat(optimalCo2);

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
        String minTemp = addPlantProfileTempMin.getText().toString();
        String maxTemp = addPlantProfileTempMax.getText().toString();
        String optimalTemp = addPlantProfileTempOptimal.getText().toString();

        if (minTemp.isEmpty()) {
            addPlantProfileTempMin.setError("Field can't be empty");
            return false;
        } else if (maxTemp.isEmpty()) {
            addPlantProfileTempMax.setError("Field can't be empty");
            return false;
        } else if (optimalTemp.isEmpty()) {
            addPlantProfileTempOptimal.setError("Field can't be empty");
            return false;
        } else {
            addPlantProfileTempMin.setError(null);
            addPlantProfileTempMax.setError(null);
            addPlantProfileTempOptimal.setError(null);
        }

        float minTempFloat = Float.parseFloat(minTemp);
        float maxTempFloat = Float.parseFloat(maxTemp);
        float optimalTempFloat = Float.parseFloat(optimalTemp);

        if (minTempFloat > optimalTempFloat) {
            addPlantProfileTempMin.setError("Min temp can't be higher than optimal temp");
            return false;
        } else if (maxTempFloat < optimalTempFloat) {
            addPlantProfileTempMax.setError("Max temp can't be lower than optimal temp");
            return false;
        } else {
            addPlantProfileTempMin.setError(null);
            addPlantProfileTempMax.setError(null);
            addPlantProfileTempOptimal.setError(null);
        }

        return true;
    }

    private boolean validateThresholdHumidity() {
        String minHumidity = addPlantProfileHumidityMin.getText().toString();
        String maxHumidity = addPlantProfileHumidityMax.getText().toString();
        String optimalHumidity = addPlantProfileHumidityOptimal.getText().toString();

        if (minHumidity.isEmpty() || maxHumidity.isEmpty()) {
            FancyToast.makeText(getContext(), "Please fill all required fields", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
            return false;
        }

        float minHumidityFloat = Float.parseFloat(minHumidity);
        float maxHumidityFloat = Float.parseFloat(maxHumidity);
        float optimalHumidityFloat = Float.parseFloat(optimalHumidity);

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
        String light = addPlantProfileLightOptimal.getText().toString();
        if (light.isEmpty()) {
            addPlantProfileLightOptimal.setError("Light is required");
            return false;
        } else {
            addPlantProfileLightOptimal.setError(null);
            return true;
        }
    }

    private boolean validateCo2() {
        String co2 = addPlantProfileCO2.getText().toString();
        if (co2.isEmpty()) {
            addPlantProfileCO2.setError("Field can't be empty");
            return false;
        } else {
            addPlantProfileCO2.setError(null);
            return true;
        }
    }

    private boolean validateHumidity() {
        String humidity = addPlantProfileHumidityOptimal.getText().toString();
        if (humidity.isEmpty()) {
            addPlantProfileHumidityOptimal.setError("Field can't be empty");
            return false;
        } else if (Float.parseFloat(humidity) < 0 || Float.parseFloat(humidity) > 100) {
            addPlantProfileHumidityOptimal.setError("Humidity must be between 0 and 100");
            return false;
        } else {
            addPlantProfileHumidityOptimal.setError(null);
            return true;
        }
    }

    private boolean validateTemp() {
        String temp = addPlantProfileTempOptimal.getText().toString();
        if (temp.isEmpty()) {
            addPlantProfileTempOptimal.setError("Field can't be empty");
            return false;
        } else if (Float.parseFloat(temp) < 0 || Float.parseFloat(temp) > 50) {
            addPlantProfileTempOptimal.setError("Temperature must be between 0 and 50");
            return false;
        } else {
            addPlantProfileTempOptimal.setError(null);
            return true;
        }
    }

    private boolean validateName() {
        String name = addPlantProfileName.getText().toString();
        if (name.isEmpty()) {
            addPlantProfileName.setError("Field can't be empty");
            return false;
        } else {
            addPlantProfileName.setError(null);
            return true;
        }
    }

    private boolean validateDescription() {
        String description = addPlantProfileDescription.getText().toString();
        if (description.isEmpty()) {
            addPlantProfileDescription.setError("Field can't be empty");
            return false;
        } else {
            addPlantProfileDescription.setError(null);
            return true;
        }
    }

    private void goBack(View view) {
        navController.popBackStack();
    }

    private void initializeViews(View view) {
        viewModel = new ViewModelProvider(this).get(AddPlantProfileViewModel.class);
        addPlantProfileName = (EditText) view.findViewById(R.id.addPlantProfileName);
        addPlantProfileDescription = (EditText) view.findViewById(R.id.addPlantProfileDescription);
        addPlantProfileTempOptimal = (EditText) view.findViewById(R.id.addPlantProfileTemp);
        addPlantProfileHumidityOptimal = (EditText) view.findViewById(R.id.addPlantProfileHumidity);
        addPlantProfileCO2 = (EditText) view.findViewById(R.id.addPlantProfileCO2);
        addPlantProfileLightOptimal = (EditText) view.findViewById(R.id.addPlantProfileLight);
        addPlantProfileTempMin = (EditText) view.findViewById(R.id.addPlantProfileTempMin);
        addPlantProfileTempMax = (EditText) view.findViewById(R.id.addPlantProfileTempMax);
        addPlantProfileCO2Min = (EditText) view.findViewById(R.id.addPlantProfileCO2Min);
        addPlantProfileCO2Max = (EditText) view.findViewById(R.id.addPlantProfileCO2Max);
        addPlantProfileHumidityMin = (EditText) view.findViewById(R.id.addPlantProfileHumidityMin);
        addPlantProfileHumidityMax = (EditText) view.findViewById(R.id.addPlantProfileHumidityMax);
        backButton = view.findViewById(R.id.addPlantProfileBackButton);
        addPlantProfileButton = (Button) view.findViewById(R.id.addPlantProfileButton);
    }
}