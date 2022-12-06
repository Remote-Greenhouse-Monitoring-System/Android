package com.github.group2.android_sep4.ui.greenhouse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.entity.Measurement;
import com.github.group2.android_sep4.ui.measurement.MeasurementType;
import com.google.android.material.card.MaterialCardView;

public class GreenhouseFragment extends Fragment {
    GreenhouseSpecificViewModel viewModel;
    TextView greenhouseName, greenhouseTemperature, greenhouseCO2, greenhouseHumidity, greenhouseLight;
    MaterialCardView clickableCard, temperatureCard, co2Card, humidityCard, lightCard;
    ImageButton backButton, deleteButton;
    NavController navController;
    DeletePopup deletePopup;
    LinearLayout activePlantProfileCard, inactivePlantProfileCard;
    Button removePlantProfileButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.greenhouse_specific_fragment, container, false);
        initializeAllFields(view);
        viewModel = new ViewModelProvider(this).get(GreenhouseSpecificViewModel.class);

        // TODO: This might crash the app -> figure out how to swap child fragments
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.placeholder, SelectGreenhouseFragment.class, null).commit();

        viewModel.getSelectedGreenhouse().observe(getViewLifecycleOwner(), greenHouseWithLastMeasurementModel -> {
            greenhouseName.setText(greenHouseWithLastMeasurementModel.getGreenHouseName());

            Measurement lastMeasurement = greenHouseWithLastMeasurementModel.getLastMeasurement();

            greenhouseTemperature.setText(getString(R.string.unit_temperature, lastMeasurement.getTemperature()));
            greenhouseCO2.setText(getString(R.string.unit_CO2, lastMeasurement.getCo2()));
            greenhouseHumidity.setText(getString(R.string.unit_humidity, lastMeasurement.getHumidity()));
            greenhouseLight.setText(getString(R.string.unit_light, lastMeasurement.getLight()));
        });

        checkActivePlantProfile();

        return view;
    }

    private void initializeAllFields(View view) {
        greenhouseName = view.findViewById(R.id.greenhouseSpecificName);
        greenhouseTemperature = view.findViewById(R.id.greenhouseTemperature);
        greenhouseCO2 = view.findViewById(R.id.greenhouseCo2);
        greenhouseHumidity = view.findViewById(R.id.greenhouseHumidity);
        greenhouseLight = view.findViewById(R.id.greenhouseLight);
        clickableCard = view.findViewById(R.id.clickableCard);
        backButton = view.findViewById(R.id.backButton);
        deleteButton = view.findViewById(R.id.deleteGreenhouse);
        activePlantProfileCard = view.findViewById(R.id.activePlantProfile);
        inactivePlantProfileCard = view.findViewById(R.id.inactivePlantProfile);
        temperatureCard = view.findViewById(R.id.latestMeasurementTemperature);
        co2Card = view.findViewById(R.id.latestMeasurementCO2);
        humidityCard = view.findViewById(R.id.latestMeasurementHumidity);
        lightCard = view.findViewById(R.id.latestMeasurementLight);
        Button removePlantProfileButton = view.findViewById(R.id.removePlantProfileButton);
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);

        backButton.setOnClickListener(this::goBack);
        deleteButton.setOnClickListener(this::deleteGreenhouse);

        setOnClickChartOpening(temperatureCard, MeasurementType.TEMPERATURE);
        setOnClickChartOpening(co2Card, MeasurementType.CO2);
        setOnClickChartOpening(humidityCard, MeasurementType.HUMIDITY);
        setOnClickChartOpening(lightCard, MeasurementType.LIGHT);
        inactivePlantProfileCard.setOnClickListener(this::goToPlantProfileList);
        removePlantProfileButton.setOnClickListener(this::removePlantProfile);
    }



    private void goToPlantProfileList(View view) {
        Bundle bundle= new Bundle();
        bundle.putInt("activeGreenhouseId", 1);
        navController.navigate(R.id.selectPlantProfileFragment, bundle);
    }

    private void deleteGreenhouse(View view) {
        deletePopup = new DeletePopup();
        deletePopup.showPopupWindow(view);
    }

    private void goBack(View view)
    {
        navController.popBackStack();
    }

    private void setOnClickChartOpening(final MaterialCardView cardView, MeasurementType measurementType) {
        cardView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("measurementType", measurementType.toString());
            navController.navigate(R.id.measurementFragment, bundle);
        });
    }

    private void checkActivePlantProfile() {
        viewModel.getActivePlantProfile().observe(getViewLifecycleOwner(), plantProfile -> {
            if (plantProfile == null) {
                activePlantProfileCard.setVisibility(View.GONE);
                inactivePlantProfileCard.setVisibility(View.VISIBLE);
            } else {
                activePlantProfileCard.setVisibility(View.VISIBLE);
                inactivePlantProfileCard.setVisibility(View.GONE);
            }
        });
    }
    private void removePlantProfile(View view) {
        viewModel.removeActivePlantProfile();
        activePlantProfileCard.setVisibility(View.GONE);
        inactivePlantProfileCard.setVisibility(View.VISIBLE);
    }
}