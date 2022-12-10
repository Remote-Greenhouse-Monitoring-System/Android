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
import com.github.group2.android_sep4.model.Threshold;
import com.github.group2.android_sep4.view.MeasurementType;
import com.github.group2.android_sep4.view.ValueFormatter;
import com.github.group2.android_sep4.view.uielements.CustomMarkerView;
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
import java.util.List;
import java.util.Locale;


public class MeasurementFragment extends Fragment {

    private LineChart lineChart;
    private MeasurementViewModel viewModel;

    private MeasurementType measurementType = MeasurementType.TEMPERATURE;

    private RadioGroup radioGroupMeasurement, radioGroupPeriod;
    private LineDataSet lineDataSet;
    private String lineColor = "#ff0000";
    private NavController navController;
    private ImageButton backButton;
    private Measurement optimalMeasurement, minThreshold, maxThreshold;
    private LimitLine optimalLine, lowerThreshold, upperThreshold;
    private TextView greenhouseTitle;
    private PlantProfile plantProfile;
    private Threshold threshold;
    private List<Measurement> measurementList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.measurement_fragment, container, false);
        viewModel = new ViewModelProvider(this).get(MeasurementViewModel.class);

        viewModel.searchMeasurement(1, 100);
        viewModel.searchThreshold(viewModel.getActivePlantProfile().getValue().getId());

        handleBundle();
        initializeViews(view);

        setObservers();
        setInitialChartType();
        initializeChart(view);
        setOnChartTypeChangedListener();
        setOnChartPeriodChangedListener();

        return view;
    }

    private void setInitialChartType() {
        switch (measurementType) {
            case CO2:
                radioGroupMeasurement.check(R.id.co2_radio_button);
                break;
            case HUMIDITY:
                radioGroupMeasurement.check(R.id.humidity_radio_button);
                break;
            case LIGHT:
                radioGroupMeasurement.check(R.id.light_radio_button);
                break;
            default:
                radioGroupMeasurement.check(R.id.temperature_radio_button);
                break;
        }
    }

    private void setOnChartPeriodChangedListener() {
        radioGroupPeriod.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.last_hour_radio_button:
                    //viewModel.searchAllMeasurementsPerHour(1, 1);// TODO: replace with the actual greenhouse id
                    viewModel.searchMeasurement(1, 100);
                    break;
                case R.id.last_day_radio_button:
                    //viewModel.searchAllMeasurementPerDays(1, 1);
                    viewModel.searchMeasurement(1, 200);
                    break;
                case R.id.last_week_radio_button:
                    //viewModel.searchAllMeasurementPerDays(1, 7);
                    viewModel.searchMeasurement(1, 400);
                    break;
                case R.id.last_month_radio_button:
                    //viewModel.searchAllMeasurementPerMonth(1, 1, 2022);
                    viewModel.searchMeasurement(1, 800);
                    break;
            }

            setMeasurementsToChart();
        });
    }

    private void setOnChartTypeChangedListener() {
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

            setMeasurementsToChart();
        });
    }

    private void setObservers() {
        viewModel.getActivePlantProfile().observe(getViewLifecycleOwner(), plantProfile -> {
            this.plantProfile = plantProfile;

            if (plantProfile != null) {
                optimalMeasurement = new Measurement(plantProfile.getOptimalTemp(), plantProfile.getOptimalHumidity(), plantProfile.getOptimalCo2());
                setOptimalLine();
            }
        });

        viewModel.getSelectedGreenhouse().observe(getViewLifecycleOwner(), greenhouse -> greenhouseTitle.setText(greenhouse.getName()));

        viewModel.getSearchedMeasurementList().observe(getViewLifecycleOwner(), measurements -> {
            if (measurements != null) {
                measurementList = measurements;
                setMeasurementsToChart();
            }
        });

        viewModel.getSearchedThreshold().observe(getViewLifecycleOwner(), threshold -> {
            this.threshold = threshold;

            if (threshold != null) {
                minThreshold = new Measurement(threshold.getMinTemperature(), threshold.getMinHumidity(), threshold.getMinCo2());
                maxThreshold = new Measurement(threshold.getMaxTemperature(), threshold.getMaxHumidity(), threshold.getMaxCo2());
                setLimitLines();
            }
        });
    }

    private void handleBundle() {
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            String measurementTypeStringFromBundle = bundle.getString("measurementType");

            this.measurementType = MeasurementType.valueOf(measurementTypeStringFromBundle);
        }
    }

    private void initializeViews(View view) {
        radioGroupMeasurement = view.findViewById(R.id.radioGroup);
        radioGroupPeriod = view.findViewById(R.id.timeRadioGroup);
        radioGroupPeriod.check(R.id.last_day_radio_button);
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
        backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(this::goBack);
        greenhouseTitle = view.findViewById(R.id.greenhouseSpecificName);
    }

    private void initializeChart(View view) {
        lineChart = view.findViewById(R.id.lineChart);
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

    private void setMeasurementsToChart() {
        ArrayList<Entry> yValues = new ArrayList<>();

        for (Measurement measurement : measurementList) {
            yValues.add(new Entry(convertDateToFloat(measurement.getDateTimeAsString()), getSpecificMeasurement(measurement)));
        }

        lineDataSet = new LineDataSet(yValues, MeasurementType.valueOf(measurementType.name()).toString());
        lineDataSet.setFillAlpha(110);
        setAppropriateLineColor();
        lineDataSet.setColor(Color.parseColor(lineColor));
        lineDataSet.setValueTextColor(Color.BLUE);
        lineDataSet.setLineWidth(3f);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setDrawCircles(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.removeAllLimitLines();

        if (plantProfile != null) {
            initializeLimitLine(optimalLine, "#ff0000");
            yAxis.addLimitLine(optimalLine);
        }

        if (threshold != null) {
            initializeLimitLine(lowerThreshold, "#000000");
            initializeLimitLine(upperThreshold, "#000000");
            yAxis.addLimitLine(lowerThreshold);
            yAxis.addLimitLine(upperThreshold);
        }

        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate();
        lineChart.notifyDataSetChanged();
        lineChart.animate();
    }

    private void setAppropriateLineColor() {
        switch (measurementType) {
            case CO2:
                lineColor = "#964B00";
                break;
            case HUMIDITY:
                lineColor = "#2986cc";
                break;
            case TEMPERATURE:
                lineColor = "#cc0000";
                break;
            case LIGHT:
                lineColor = "#38761d";
                break;
        }
    }

    private void setLimitLines() {
        if (threshold == null)
            return;

        lowerThreshold = getLimitLine(getSpecificMeasurement(minThreshold), "Min " + getMeasurementTypeString());
        upperThreshold = getLimitLine(getSpecificMeasurement(maxThreshold), "Max " + getMeasurementTypeString());
    }

    private void setOptimalLine() {
        if (plantProfile == null)
            return;

        optimalLine = getLimitLine(getSpecificMeasurement(optimalMeasurement), "Ideal " + getMeasurementTypeString());
    }

    private String getMeasurementTypeString() {

        switch (measurementType) {
            case CO2:
                return "CO2";
            case HUMIDITY:
                return "Humidity";
            case TEMPERATURE:
                return "Temperature";
            case LIGHT:
                return "Light";
            default:
                return null;
        }
    }

    private void initializeLimitLine(LimitLine limitLine, String lineColor) {
        limitLine.setLineWidth(2f);
        limitLine.enableDashedLine(10f, 10f, 0f);
        limitLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        limitLine.setTextSize(10f);
        limitLine.setLineColor(Color.parseColor(lineColor));
        limitLine.setTextColor(Color.parseColor(lineColor));
    }

    private LimitLine getLimitLine(float value, String label) {
        return new LimitLine(value, label);
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
}