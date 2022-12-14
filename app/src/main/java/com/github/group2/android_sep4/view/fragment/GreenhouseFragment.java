package com.github.group2.android_sep4.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.Measurement;
import com.github.group2.android_sep4.view.MeasurementType;
import com.github.group2.android_sep4.viewmodel.GreenhouseViewModel;
import com.google.android.material.card.MaterialCardView;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancytoastlib.FancyToast;

public class GreenhouseFragment extends Fragment {
    GreenhouseViewModel viewModel;
    TextView greenhouseName, greenhouseTemperature, greenhouseCO2, greenhouseHumidity, greenhouseLight, activePlantProfileName, deviceEui;
    MaterialCardView clickableCard, temperatureCard, co2Card, humidityCard, lightCard;
    ImageButton backButton, deleteButton;
    NavController navController;

    LinearLayout activePlantProfileCard, inactivePlantProfileCard;
    Button removePlantProfileButton;
    private long greenhouseId;

    public GreenhouseFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_greenhouse_specific, container, false);
        viewModel = new ViewModelProvider(this).get(GreenhouseViewModel.class);

        viewModel.searchActivatedProfile();

        setObservers();
        initializeAllFields(view);

        return view;
    }

        private void setObservers() {
            viewModel.getSelectedGreenhouse().observe(getViewLifecycleOwner(), greenhouse -> {
                greenhouseId = greenhouse.getId();
                greenhouseName.setText(greenhouse.getName());
                deviceEui.setText("Device EUI :" + greenhouse.getEui());

            Measurement lastMeasurement = greenhouse.getLastMeasurement();
            if (lastMeasurement == null
                    || lastMeasurement.isAllZeros()) {
                greenhouseTemperature.setText(R.string.no_data);
                greenhouseCO2.setText(R.string.no_data);
                greenhouseHumidity.setText(R.string.no_data);
                greenhouseLight.setText(R.string.no_data);
            } else {
                greenhouseTemperature.setText(getString(R.string.unit_temperature, lastMeasurement.getTemperature()));
                greenhouseCO2.setText(getString(R.string.unit_CO2, lastMeasurement.getCo2()));
                greenhouseHumidity.setText(getString(R.string.unit_humidity, lastMeasurement.getHumidity()));
                greenhouseLight.setText(getString(R.string.unit_light, lastMeasurement.getLight()));
            }
        });

        viewModel.getActivePlantProfile().observe(getViewLifecycleOwner(), activePlantProfile -> {
            if (activePlantProfile != null) {
                activePlantProfileName.setText(activePlantProfile.getName());
                activePlantProfileCard.setVisibility(View.VISIBLE);
                inactivePlantProfileCard.setVisibility(View.GONE);
            } else {
                activePlantProfileCard.setVisibility(View.GONE);
                inactivePlantProfileCard.setVisibility(View.VISIBLE);
            }
        });
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
        activePlantProfileCard.setVisibility(View.GONE);

        inactivePlantProfileCard = view.findViewById(R.id.inactivePlantProfile);
        deviceEui = view.findViewById(R.id.device_eui);

        temperatureCard = view.findViewById(R.id.latestMeasurementTemperature);
        co2Card = view.findViewById(R.id.latestMeasurementCO2);
        humidityCard = view.findViewById(R.id.latestMeasurementHumidity);
        lightCard = view.findViewById(R.id.latestMeasurementLight);
        removePlantProfileButton = view.findViewById(R.id.removePlantProfileButton);
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
        activePlantProfileName = view.findViewById(R.id.activePlantProfileText);

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
        Bundle bundle = new Bundle();
        bundle.putBoolean("isFromSpecificGreenhouse", true);
        navController.navigate(R.id.selectPlantProfileFragment, bundle);
    }

    private void deleteGreenhouse(View view) {
        String message = getString(R.string.delete_greenhouse_message);
        FancyAlertDialog.Builder.with(getContext())
                .setTitle("Delete greenhouse")
                .setBackgroundColorRes(R.color.palette_red)
                .setMessage(message)
                .setNegativeBtnText("Cancel")
                .setPositiveBtnBackgroundRes(R.color.palette_red)
                .setPositiveBtnText("Confirm")
                .setNegativeBtnBackgroundRes(R.color.palette_grey)
                .setAnimation(Animation.SLIDE)
                .isCancellable(true)
                .setIcon(R.drawable.ic_baseline_delete_outline_24, View.VISIBLE)
                .onPositiveClicked(dialog -> {
                    viewModel.deleteGreenhouse(greenhouseId);
                    navController.navigate(R.id.homeFragment);
                })
                .onNegativeClicked(dialog -> {
                    dialog.dismiss();
                    FancyToast.makeText(getContext(), "Cancelled", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();
                })
                .build()
                .show();
    }

    private void goBack(View view) {
        navController.navigate(R.id.homeFragment);
    }

    private void setOnClickChartOpening(final MaterialCardView cardView, MeasurementType measurementType) {
        cardView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("measurementType", measurementType.toString());
            navController.navigate(R.id.measurementFragment, bundle);
        });
    }

    private void removePlantProfile(View view) {
        viewModel.deactivatePlantProfile();
    }
}