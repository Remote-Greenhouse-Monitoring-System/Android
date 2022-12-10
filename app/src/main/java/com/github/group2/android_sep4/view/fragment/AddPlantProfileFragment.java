package com.github.group2.android_sep4.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

public class AddPlantProfileFragment extends Fragment {
    private ImageButton backButton;
    private NavController navController;
    private EditText addPlantProfileName, addPlantProfileDescription,addPlantProfileTemp,addPlantProfileHumidity,
            addPlantProfileCO2,addPlantProfileLight,addPlantProfileTempMin,addPlantProfileTempMax,addPlantProfileCO2Min,
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
                if(!checkFieldsForAdd())
                {
                    displayFieldErrors();
                    FancyToast.makeText(getContext(), "Please fill all required fields", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                }
                else
                {
                    String name = addPlantProfileName.getText().toString();
                    String description = addPlantProfileDescription.getText().toString();
                    float optimalTemp = Float.parseFloat(addPlantProfileTemp.getText().toString());
                    float optimalHumidity = Float.parseFloat(addPlantProfileHumidity.getText().toString());
                    float optimalCo2 = Float.parseFloat(addPlantProfileCO2.getText().toString());
                    int optimalLight = Integer.parseInt(addPlantProfileLight.getText().toString());
                    float minTemp = Float.parseFloat(addPlantProfileTempMin.getText().toString());
                    float maxTemp = Float.parseFloat(addPlantProfileTempMax.getText().toString());
                    float minHumidity = Float.parseFloat(addPlantProfileHumidityMin.getText().toString());
                    float maxHumidity = Float.parseFloat(addPlantProfileHumidityMax.getText().toString());
                    float minCo2 = Float.parseFloat(addPlantProfileCO2Min.getText().toString());
                    float maxCo2 = Float.parseFloat(addPlantProfileCO2Max.getText().toString());

                    Threshold threshold = new Threshold(maxTemp,minTemp,maxHumidity,minHumidity,maxCo2,minCo2,1,1);
                    PlantProfile plantProfile = new PlantProfile(0,name,description,optimalTemp,optimalHumidity,optimalCo2,optimalLight);

                    plantProfileViewModel.addPlantProfile(plantProfileViewModel.getCurrentUser().getValue().getId(),plantProfile);
                    plantProfileViewModel.updateThreshold(plantProfile.getId(),threshold);

                    FancyToast.makeText(getContext(), name +" added successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                    navController.navigate(R.id.selectPlantProfileFragment);
                }
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
        addPlantProfileLight = (EditText) view.findViewById(R.id.addPlantProfileLight);
        addPlantProfileTempMin = (EditText) view.findViewById(R.id.addPlantProfileTempMin);
        addPlantProfileTempMax = (EditText) view.findViewById(R.id.addPlantProfileTempMax);
        addPlantProfileCO2Min = (EditText) view.findViewById(R.id.addPlantProfileCO2Min);
        addPlantProfileCO2Max = (EditText) view.findViewById(R.id.addPlantProfileCO2Max);
        addPlantProfileHumidityMin = (EditText) view.findViewById(R.id.addPlantProfileHumidityMin);
        addPlantProfileHumidityMax = (EditText)view.findViewById(R.id.addPlantProfileHumidityMax);
        backButton = view.findViewById(R.id.addPlantProfileBackButton);
        addPlantProfileButton = (Button) view.findViewById(R.id.addPlantProfileButton);
    }

    private boolean checkFieldsForAdd()
    {
        boolean empty = true;

        if(addPlantProfileName.getText().toString().matches("")  && addPlantProfileDescription.getText().toString().matches("")  &&
                addPlantProfileTemp.getText().toString().matches("")  &&addPlantProfileHumidity.getText().toString().matches("")  &&
                addPlantProfileCO2.getText().toString().matches("")  &&addPlantProfileLight.getText().toString().matches("")  &&
                addPlantProfileTempMin.getText().toString().matches("")  &&addPlantProfileTempMax.getText().toString().matches("")  &&
                addPlantProfileCO2Min.getText().toString().matches("")  &&addPlantProfileCO2Max.getText().toString().matches("")  &&
                addPlantProfileHumidityMin.getText().toString().matches("")  &&addPlantProfileHumidityMax.getText().toString().matches(""))
        {
            empty = false;
        }

        return empty;
    }

    private void displayFieldErrors()
    {
        addPlantProfileName.setError("This field cannot be blank");
        addPlantProfileDescription.setError("This field cannot be blank");
        addPlantProfileTemp.setError("This field cannot be blank");
        addPlantProfileHumidity.setError("This field cannot be blank");
        addPlantProfileCO2.setError("This field cannot be blank");
        addPlantProfileLight.setError("This field cannot be blank");
        addPlantProfileTempMin.setError("This field cannot be blank");
        addPlantProfileTempMax.setError("This field cannot be blank");
        addPlantProfileCO2Min.setError("This field cannot be blank");
        addPlantProfileCO2Max.setError("This field cannot be blank");
        addPlantProfileHumidityMin.setError("This field cannot be blank");
        addPlantProfileHumidityMax.setError("This field cannot be blank");

    }
}