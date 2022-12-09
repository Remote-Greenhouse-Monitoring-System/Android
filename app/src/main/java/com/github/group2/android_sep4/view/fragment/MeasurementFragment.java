package com.github.group2.android_sep4.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.Measurement;
import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.view.MeasurementType;
import com.github.group2.android_sep4.view.Period;
import com.github.group2.android_sep4.view.ValueFormatter;
import com.github.group2.android_sep4.view.uielements.CustomMarkerView;
import com.github.group2.android_sep4.viewmodel.GreenhouseViewModel;
import com.github.group2.android_sep4.viewmodel.PlantProfileViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.group2.android_sep4.viewmodel.MeasurementViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class MeasurementFragment extends Fragment {

    private LineChart lineChart;
    private MeasurementViewModel viewModel;
    private Period period;
    private MeasurementType measurementType = MeasurementType.TEMPERATURE;
    private RadioGroup radioGroupMeasurement, radioGroupPeriod;
    private LineDataSet lineDataSet;
    private String lineColor;
    private NavController navController;
    private ImageButton backButton;
    private Measurement optimalMeasurement, minThreshold, maxThreshold;
    private LimitLine limitLine, lowerThreshold, upperThreshold;
    private GreenhouseViewModel greenhouseViewModel;
    private PlantProfileViewModel plantProfileViewModel;
    private TextView greenhouseTitle;
    private long selectedGreenhouseId = 0L;
    private PlantProfile plantProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.measurement_fragment, container, false);
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            String measurementTypeStringFromBundle = bundle.getString("measurementType");
            selectedGreenhouseId = bundle.getLong("greenhouseId");

            MeasurementType measurementTypeFromBundle = MeasurementType.valueOf(measurementTypeStringFromBundle);
            this.measurementType = measurementTypeFromBundle;

            switch (measurementTypeFromBundle) {
                case CO2:
                    radioGroupMeasurement.check(R.id.co2_radio_button);
                    lineColor = "#964B00";
                    break;
                case HUMIDITY:
                    radioGroupMeasurement.check(R.id.humidity_radio_button);
                    lineColor = "#2986cc";
                    break;
                case LIGHT:
                    radioGroupMeasurement.check(R.id.light_radio_button);
                    lineColor = "#38761d";
                    break;
                default:
                    radioGroupMeasurement.check(R.id.temperature_radio_button);
                    lineColor = "#cc0000";
                    break;
            }
        }

        plantProfileViewModel.getActivePlantProfile(selectedGreenhouseId).observe(getViewLifecycleOwner(), plantProfile -> {
            this.plantProfile = plantProfile;
        });

        greenhouseViewModel = new ViewModelProvider(this).get(GreenhouseViewModel.class);
        plantProfileViewModel = new ViewModelProvider(this).get(PlantProfileViewModel.class);
        initializeViews(view);
        initializeChart(view);

        setMeasurementsToChart();

        radioGroupMeasurement.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.co2_radio_button:
                    measurementType = MeasurementType.CO2;
                    lineColor = "#964B00";
                    break;
                case R.id.humidity_radio_button:
                    measurementType = MeasurementType.HUMIDITY;
                    lineColor = "#2986cc";
                    break;
                case R.id.temperature_radio_button:
                    measurementType = MeasurementType.TEMPERATURE;
                    lineColor = "#cc0000";
                    break;
                case R.id.light_radio_button:
                    measurementType = MeasurementType.LIGHT;
                    lineColor = "#38761d";
                    break;
            }

            setLimitLines(measurementType);
            setMeasurementsToChart();
        });

        radioGroupPeriod.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.last_hour_radio_button:
                    period = Period.HOUR;
                    viewModel.searchMeasurement(1, 12);
                    break;
                case R.id.last_day_radio_button:
                    period = Period.DAY;
                    viewModel.searchMeasurement(1, 288);
                    break;
                case R.id.last_week_radio_button:
                    period = Period.WEEK;
                    viewModel.searchAllMeasurementPerDays(1, 2016);
                    break;
                case R.id.last_month_radio_button:
                    period = Period.MONTH;
                    viewModel.searchMeasurement(1, 4000);
                    break;
            }

            updatePeriod();
            setMeasurementsToChart();
        });

        return view;
    }

    private void setLimitLines(MeasurementType measurementType) {
        switch (measurementType) {
            case CO2:
                limitLine = getLimitLine(optimalMeasurement.getCo2(), "Ideal CO2");
                lowerThreshold = getLimitLine(minThreshold.getCo2(), "Min CO2");
                upperThreshold = getLimitLine(maxThreshold.getCo2(), "Max CO2");
                break;
            case HUMIDITY:
                limitLine = getLimitLine(optimalMeasurement.getHumidity(), "Ideal Humidity");
                lowerThreshold = getLimitLine(minThreshold.getHumidity(), "Min Humidity");
                upperThreshold = getLimitLine(maxThreshold.getHumidity(), "Max Humidity");
                break;
            case TEMPERATURE:
                limitLine = getLimitLine(optimalMeasurement.getTemperature(), "Ideal Temperature");
                lowerThreshold = getLimitLine(minThreshold.getTemperature(), "Min Temperature");
                upperThreshold = getLimitLine(maxThreshold.getTemperature(), "Max Temperature");
                break;
            case LIGHT:
                limitLine = getLimitLine(optimalMeasurement.getLight(), "Ideal Light");
                lowerThreshold = getLimitLine(minThreshold.getLight(), "Min Light");
                upperThreshold = getLimitLine(maxThreshold.getLight(), "Max Light");
                break;
        }
    }

    @NonNull
    private LimitLine getLimitLine(float value, String label) {
        return new LimitLine(value, label);
    }

    private void initializeViews(View view) {
        viewModel = new MeasurementViewModel();
        period = Period.HOUR;

        checkForPlantProfile();

        if (measurementType == null)
            measurementType = MeasurementType.TEMPERATURE;

        radioGroupMeasurement = view.findViewById(R.id.radioGroup);
        radioGroupPeriod = view.findViewById(R.id.timeRadioGroup);
        radioGroupPeriod.check(R.id.last_day_radio_button);
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
        backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(this::goBack);
        greenhouseTitle = view.findViewById(R.id.greenhouseSpecificName);
        greenhouseTitle.setText(greenhouseViewModel.getSelectedGreenhouse().getValue().getName());
    }

    private void initializeChart(View view) {

        lineChart = view.findViewById(R.id.lineChart);
        setupChart();
        viewModel.searchMeasurement(1, 288);
        setMeasurementsToChart();
        lineColor = "#cc0000";
    }

    private float convertDateToFloat(String date) {
        String newDate;
        newDate = date.replace("T", " ");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        try {
            Date date1 = format.parse(newDate);
            return date1.getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void setupChart() {
        CustomMarkerView mv = new CustomMarkerView(getContext(), R.layout.custom_view_marker);
        lineChart.setDrawBorders(true);
        lineChart.invalidate();
        lineChart.setScaleXEnabled(true);
        lineChart.setMaxVisibleValueCount(3);
        lineChart.setPinchZoom(true);
        lineChart.setDragEnabled(true);
        lineChart.setDescription(null);
        lineChart.resetZoom();
        lineChart.setMarker(mv);

        /*if (plantProfileViewModel.getActivatedPlantProfile().getValue() != null)
            setLimitLines(MeasurementType.TEMPERATURE);*/

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);

        YAxis yAxis = lineChart.getAxisRight();
        yAxis.setEnabled(false);

        ValueFormatter formatter = new ValueFormatter();
        xAxis.setValueFormatter(formatter);
        xAxis.setLabelCount(3);
    }

    private void updatePeriod() {
        switch (period) {
            case HOUR:
                viewModel.searchAllMeasurementsPerHour(1, 1);
                break;
            case DAY:
                viewModel.searchAllMeasurementPerDays(1, 1);
                break;
            case WEEK:
                viewModel.searchAllMeasurementPerDays(1, 7);
                break;
            case MONTH:
                viewModel.searchAllMeasurementPerMonth(1, 1, 2022);
                break;
        }
    }

    private void setMeasurementsToChart() {
        viewModel.getSearchedMeasurementList().observe(getViewLifecycleOwner(), measurements -> {
            ArrayList<Entry> yValues = new ArrayList<>();
            for (Measurement measurement : measurements) {
                yValues.add(new Entry(convertDateToFloat(measurement.getDateTimeAsString()), getSpecificMeasurement(measurement)));
            }

            lineDataSet = new LineDataSet(yValues, MeasurementType.valueOf(measurementType.name()).toString());
            lineDataSet.setFillAlpha(110);
            lineDataSet.setColor(Color.parseColor(lineColor));
            lineDataSet.setValueTextColor(Color.BLUE);
            lineDataSet.setLineWidth(3f);
            lineDataSet.setValueTextSize(10f);
            lineDataSet.setDrawCircles(false);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDataSet);
            YAxis yAxis = lineChart.getAxisLeft();
            yAxis.removeAllLimitLines();

            /*if (plantProfileViewModel.getActivatedPlantProfile().getValue() != null) {
                setLimitLine(limitLine, "#ff0000");
                setLimitLine(lowerThreshold, "#000000");
                setLimitLine(upperThreshold, "#000000");
                yAxis.addLimitLine(limitLine);
                yAxis.addLimitLine(lowerThreshold);
                yAxis.addLimitLine(upperThreshold);
            }*/

            LineData data = new LineData(dataSets);
            lineChart.setData(data);
            lineChart.invalidate();
            lineChart.notifyDataSetChanged();
            lineChart.animate();
        });
    }

    private void setLimitLine(LimitLine limitLine, String lineColor) {
        limitLine.setLineWidth(2f);
        limitLine.enableDashedLine(10f, 10f, 0f);
        limitLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        limitLine.setTextSize(10f);
        limitLine.setLineColor(Color.parseColor(lineColor));
        limitLine.setTextColor(Color.parseColor(lineColor));
    }

    private float getSpecificMeasurement(Measurement measurement) {
        float value;
        switch (measurementType) {
            case CO2:
                value = measurement.getCo2();
                break;
            case HUMIDITY:
                value = measurement.getHumidity();
                break;
            case TEMPERATURE:
                value = measurement.getTemperature();
                break;
            case LIGHT:
                value = measurement.getLight();
                break;
            default:
                value = 0;
        }
        return value;
    }

    private void goBack(View view) {
        navController.navigate(R.id.greenhouseFragment);
    }

    private void checkForPlantProfile() {
        /*plantProfileViewModel.getActivatedPlantProfile().observe(getViewLifecycleOwner(), plantProfile -> {
            if (plantProfile != null) {
                System.out.println(plantProfile);

                Threshold threshold = plantProfile.getThreshold();
                if (threshold != null) {
                    System.out.println(threshold);
                    minThreshold = new Measurement(threshold.getMinTemperature(), threshold.getMinHumidity(), threshold.getMinCo2());
                    maxThreshold = new Measurement(threshold.getMaxTemperature(), threshold.getMaxHumidity(), threshold.getMaxCo2());
                }

                optimalMeasurement = new Measurement(plantProfile.getOptimalTemp(), plantProfile.getOptimalHumidity(), plantProfile.getOptimalCo2());
            }
        });*/
    }
}