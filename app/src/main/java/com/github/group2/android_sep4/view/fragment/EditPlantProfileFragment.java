package com.github.group2.android_sep4.view.fragment;

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
import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.model.Threshold;
import com.github.group2.android_sep4.viewmodel.AddPlantProfileViewModel;
import com.github.group2.android_sep4.viewmodel.UserViewModel;

public class EditPlantProfileFragment extends Fragment {

    private ImageButton backButton;
    private NavController navController;
    private EditText editPlantProfileName, editPlantProfileDescription,editPlantProfileTemp,editPlantProfileHumidity,
            editPlantProfileCO2,editPlantProfileLight,editPlantProfileTempMin,editPlantProfileTempMax,editPlantProfileCO2Min,
            editPlantProfileCO2Max,editPlantProfileHumidityMin,editPlantProfileHumidityMax;
    private Button editPlantProfileButton;
    private AddPlantProfileViewModel addPlantProfileViewModel;
    private UserViewModel userViewModel;
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
                Threshold threshold = new Threshold(maxTemp,minTemp,maxHumidity,minHumidity,maxCo2,minCo2,1,1,1,1);
                PlantProfile plantProfile = new PlantProfile(0,name,description,optimalTemp,optimalHumidity,optimalCo2,optimalLight);

                addPlantProfileViewModel.updatePlantProfile(plantProfile);
                addPlantProfileViewModel.updateThreshold(plantProfile.getId(),threshold);


            }
        });



        plantProfileFound = addPlantProfileViewModel.getPlantProfile().getValue();
        thresholdFound = addPlantProfileViewModel.getSearchedThreshold().getValue();
        if(plantProfileFound == null && thresholdFound == null)
        {

        }else
        {
            initializeFields();
        }

        return view;

    }

    private void initializeFields() {

        editPlantProfileName.setText(plantProfileFound.getName());
        editPlantProfileDescription.setText(plantProfileFound.getDescription()); ;
        editPlantProfileTemp.setText(String.valueOf(plantProfileFound.getOptimalTemp())); ;
        editPlantProfileHumidity.setText(String.valueOf(plantProfileFound.getOptimalHumidity())) ;
        editPlantProfileCO2.setText(String.valueOf(plantProfileFound.getOptimalCo2())) ;
        editPlantProfileLight.setText(String.valueOf(plantProfileFound.getOptimalLight()));
        editPlantProfileTempMin.setText(String.valueOf(thresholdFound.getTemperatureMin())) ;
        editPlantProfileTempMax.setText(String.valueOf(thresholdFound.getTemperatureMax())) ;
        editPlantProfileCO2Min.setText(String.valueOf(thresholdFound.getCo2Min())) ;
        editPlantProfileCO2Max.setText(String.valueOf(thresholdFound.getCo2Max())) ;
        editPlantProfileHumidityMin.setText(String.valueOf(thresholdFound.getHumidityMin())) ;
        editPlantProfileHumidityMax.setText(String.valueOf(thresholdFound.getMaxHumidity())) ;

    }

    private void initializeViews(View view)
    {
        addPlantProfileViewModel = new AddPlantProfileViewModel();
        userViewModel = new UserViewModel();
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

    private void goBack(View view) {
        navController.popBackStack();
    }


}