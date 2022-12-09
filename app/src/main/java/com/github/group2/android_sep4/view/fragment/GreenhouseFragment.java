package com.github.group2.android_sep4.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.Measurement;
import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.view.uielements.DeletePopup;
import com.github.group2.android_sep4.viewmodel.GreenhouseSpecificViewModel;
import com.github.group2.android_sep4.view.MeasurementType;
import com.github.group2.android_sep4.viewmodel.PlantProfileViewModel;
import com.google.android.material.card.MaterialCardView;

public class GreenhouseFragment extends Fragment
{
    GreenhouseSpecificViewModel viewModel;
    PlantProfileViewModel plantProfileViewModel;
    TextView greenhouseName, greenhouseTemperature, greenhouseCO2, greenhouseHumidity, greenhouseLight, activePlantProfileName;
    MaterialCardView clickableCard, temperatureCard, co2Card, humidityCard, lightCard;
    ImageButton backButton, deleteButton;
    NavController navController;
    DeletePopup deletePopup;
    LinearLayout activePlantProfileCard, inactivePlantProfileCard;
    Button removePlantProfileButton;
    private PlantProfile plantProfile;
    long greenhouseId;

    public GreenhouseFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.greenhouse_specific_fragment, container, false);

        viewModel = new ViewModelProvider(this).get(GreenhouseSpecificViewModel.class);
        plantProfileViewModel= new ViewModelProvider(this).get(PlantProfileViewModel.class);


        viewModel.getSelectedGreenhouse().observe(getViewLifecycleOwner(), greenHouseWithLastMeasurementModel -> {
            greenhouseName.setText(greenHouseWithLastMeasurementModel.getName());

            Measurement lastMeasurement = greenHouseWithLastMeasurementModel.getLastMeasurement();

            greenhouseTemperature.setText(getString(R.string.unit_temperature, lastMeasurement.getTemperature()));
            greenhouseCO2.setText(getString(R.string.unit_CO2, lastMeasurement.getCo2()));
            greenhouseHumidity.setText(getString(R.string.unit_humidity, lastMeasurement.getHumidity()));
            greenhouseLight.setText(getString( R.string.unit_light, lastMeasurement.getLight()));
        });
        initializeAllFields(view);
        checkActivePlantProfile();
        return view;
    }

    private void initializeAllFields(View view)
    {
        greenhouseName = view.findViewById(R.id.greenhouseSpecificName);
        greenhouseTemperature = view.findViewById(R.id.greenhouseTemperature);
        greenhouseCO2 = view.findViewById(R.id.greenhouseCo2);
        greenhouseHumidity = view.findViewById(R.id.greenhouseHumidity);
        greenhouseLight = view.findViewById(R.id.greenhouseLight);
        clickableCard = view.findViewById(R.id.clickableCard);
        backButton = view.findViewById(R.id.backButton);
        deleteButton = view.findViewById(R.id.deleteGreenhouse);
        activePlantProfileCard = view.findViewById(R.id.activePlantProfile);
        activePlantProfileCard.setVisibility(View.GONE);

        inactivePlantProfileCard = view.findViewById(R.id.inactivePlantProfile);

        temperatureCard = view.findViewById(R.id.latestMeasurementTemperature);
        co2Card = view.findViewById(R.id.latestMeasurementCO2);
        humidityCard = view.findViewById(R.id.latestMeasurementHumidity);
        lightCard = view.findViewById(R.id.latestMeasurementLight);
        removePlantProfileButton = view.findViewById(R.id.removePlantProfileButton);
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
        activePlantProfileName = view.findViewById(R.id.activePlantProfileText);

//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            greenhouseId = bundle.getInt("greenhouseId");
//            viewModel.setGreenhouseId(viewModel.getSelectedGreenhouse().getValue().getId());
//        }

        greenhouseId=viewModel.getSelectedGreenhouse().getValue().getId();

        backButton.setOnClickListener(this::goBack);
        deleteButton.setOnClickListener(this::deleteGreenhouse);

        setOnClickChartOpening(temperatureCard, MeasurementType.TEMPERATURE);
        setOnClickChartOpening(co2Card, MeasurementType.CO2);
        setOnClickChartOpening(humidityCard, MeasurementType.HUMIDITY);
        setOnClickChartOpening(lightCard, MeasurementType.LIGHT);
        inactivePlantProfileCard.setOnClickListener(this::goToPlantProfileList);
        removePlantProfileButton.setOnClickListener(this::removePlantProfile);
       plantProfile=plantProfileViewModel.getActivatedPlantProfile().getValue();
    }

    private void goToPlantProfileList(View view) {
        Bundle bundle= new Bundle();
        bundle.putLong("activeGreenhouseId", viewModel.getSelectedGreenhouse().getValue().getId());
        Toast.makeText(getContext(), "Greenhouse id: " + viewModel.getSelectedGreenhouse().getValue().getId(), Toast.LENGTH_SHORT).show();
        navController.navigate(R.id.selectPlantProfileFragment, bundle);
    }

    private void deleteGreenhouse(View view)
    {
        deletePopup = new DeletePopup();
        deletePopup.showPopupWindow(view);
    }

    private void goBack(View view)
    {
        navController.popBackStack();
    }

    private void setOnClickChartOpening(final MaterialCardView cardView, MeasurementType measurementType)
    {
        cardView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("measurementType", measurementType.toString());
            navController.navigate(R.id.measurementFragment, bundle);
        });
    }
    private void checkActivePlantProfile()
    {
        viewModel.getActivePlantProfile(greenhouseId).observe(getViewLifecycleOwner(), plantProfile -> {
            if (plantProfile == null || plantProfile.getId() == 0 ) {
                activePlantProfileCard.setVisibility(View.GONE);
                inactivePlantProfileCard.setVisibility(View.VISIBLE);

            } else {
                activePlantProfileCard.setVisibility(View.VISIBLE);
                inactivePlantProfileCard.setVisibility(View.GONE);
                activePlantProfileName.setText(plantProfile.getName());
            }
        });
    }

    private void removePlantProfile(View view) {
        viewModel.removeActivePlantProfile();
        plantProfileViewModel.deactivatePlantProfile(viewModel.getSelectedGreenhouse().getValue().getId());

        activePlantProfileCard.setVisibility(View.GONE);
        inactivePlantProfileCard.setVisibility(View.VISIBLE);
    }
}