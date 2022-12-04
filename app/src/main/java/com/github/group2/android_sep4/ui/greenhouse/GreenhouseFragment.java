package com.github.group2.android_sep4.ui.greenhouse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.entity.Measurement;
import com.github.group2.android_sep4.ui.home.GreenHouseWithLastMeasurementModel;

public class GreenhouseFragment extends Fragment
{
    GreenhouseSpecificViewModel viewModel;
    TextView greenhouseName, greenhouseTemperature, greenhouseCO2, greenhouseHumidity, greenhouseLight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.greenhouse_specific_fragment, container, false);
        initializeAllFields(view);
        viewModel = new ViewModelProvider(this).get(GreenhouseSpecificViewModel.class);

        viewModel.getSelectedGreenhouse().observe(getViewLifecycleOwner(), new Observer<GreenHouseWithLastMeasurementModel>() {
            @Override
            public void onChanged(GreenHouseWithLastMeasurementModel greenHouseWithLastMeasurementModel) {
                greenhouseName.setText(greenHouseWithLastMeasurementModel.getGreenHouseName());

                Measurement lastMeasurement = greenHouseWithLastMeasurementModel.getLastMeasurement();

                greenhouseTemperature.setText(getString(R.string.unit_temperature, lastMeasurement.getTemperature()));
                greenhouseCO2.setText(getString(R.string.unit_CO2, lastMeasurement.getCo2()));
                greenhouseHumidity.setText(getString(R.string.unit_humidity, lastMeasurement.getHumidity()));
                greenhouseLight.setText(getString(R.string.unit_light, lastMeasurement.getLight()));
            }
        });

        return view;
    }

    private void initializeAllFields(View view)
    {
        greenhouseName = view.findViewById(R.id.greenhouseName);
        greenhouseTemperature = view.findViewById(R.id.greenhouse_temperature);
        greenhouseCO2 = view.findViewById(R.id.greenhouse_co2);
        greenhouseHumidity = view.findViewById(R.id.greenhouse_humidity);
        greenhouseLight = view.findViewById(R.id.greenhouse_light);
    }
}
