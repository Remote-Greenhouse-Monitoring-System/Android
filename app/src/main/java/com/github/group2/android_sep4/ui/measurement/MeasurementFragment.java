package com.github.group2.android_sep4.ui.measurement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.entity.Measurement;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class MeasurementFragment extends Fragment {

    private LineChart lineChart;
    private MeasurementViewModal measurementViewModel;
    private Period period;
    private MeasurementType measurementType;
    private RadioGroup radioGroupMeasurement, radioGroupPeriod;
    private LineDataSet lineDataSet;
    private String lineColor;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.measurement_fragment, container, false);
        initializeViews(view);
        initializeChart(view);

        radioGroupMeasurement.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.co2_radio_button:

                    measurementType = MeasurementType.CO2;
                    lineColor = "#000000";
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

        radioGroupPeriod.setOnCheckedChangeListener((group, checkedId) -> {


            switch (checkedId) {
                case R.id.last_hour_radio_button:

                    period = Period.HOUR;
                    measurementViewModel.searchMeasurement(1,12);

                    break;

                case R.id.last_day_radio_button:

                    period = Period.DAY;
                    measurementViewModel.searchMeasurement(1, 288);

                    break;

                case R.id.last_week_radio_button:

                    period = Period.WEEK;
                    measurementViewModel.searchAllMeasurementPerDays(1, 2016);

                    break;

                case R.id.last_month_radio_button:

                    period = Period.MONTH;
                    measurementViewModel.searchMeasurement(1, 4000);


                    break;
            }
            updatePeriod();
            setMeasurementsToChart();
        });
        return view;

    }


    private void initializeViews(View view) {

        measurementViewModel = new MeasurementViewModal();
        period = Period.HOUR;
        measurementType = MeasurementType.TEMPERATURE;
        radioGroupMeasurement = view.findViewById(R.id.radioGroup);
        radioGroupPeriod = view.findViewById(R.id.timeRadioGroup);
        radioGroupMeasurement.check(R.id.temperature_radio_button);
        radioGroupPeriod.check(R.id.last_day_radio_button);
    }

    private void initializeChart(View view) {
        lineChart = view.findViewById(R.id.lineChart);
        setupChart();
        measurementViewModel.searchMeasurement(1,288);
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
        lineChart.setDrawBorders(true);
        lineChart.invalidate();
        lineChart.setScaleXEnabled(true);
        lineChart.setMaxVisibleValueCount(3);
        lineChart.setPinchZoom(true);
        lineChart.setDragEnabled(true);
        lineChart.setDescription(null);
        lineChart.resetZoom();

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
                measurementViewModel.searchAllMeasurementsPerHour(1, 1);
                break;

            case DAY:
                measurementViewModel.searchAllMeasurementPerDays(1, 1);
                break;

            case WEEK:
                measurementViewModel.searchAllMeasurementPerDays(1, 7);
                break;

            case MONTH:
                measurementViewModel.searchAllMeasurementPerMonth(1, 1, 2022);
                break;
        }
    }


    private void setMeasurementsToChart() {

        measurementViewModel.getSearchedMeasurementList().observe(getViewLifecycleOwner(), measurements -> {
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
            LineData data = new LineData(dataSets);
            lineChart.setData(data);
            lineChart.invalidate();
            lineChart.notifyDataSetChanged();
            lineChart.animate();
        });
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

//    public void onRadioButtonClicked(View view) {
//        boolean checked = ((RadioButton) view).isChecked();
//        switch (view.getId()) {
//            case R.id.co2_radio_button:
//                if (checked) {
//                    measurementType = MeasurementType.CO2;
//                    lineColor = "#000000";
//                }
//                break;
//            case R.id.humidity_radio_button:
//                if (checked) {
//                    measurementType = MeasurementType.HUMIDITY;
//                    lineColor = "#2986cc";
//                }
//                break;
//            case R.id.temperature_radio_button:
//                if (checked) {
//                    measurementType = MeasurementType.TEMPERATURE;
//                    lineColor = "#cc0000";
//                }
//                break;
//            case R.id.light_radio_button:
//                if (checked) {
//                    measurementType = MeasurementType.LIGHT;
//                    lineColor = "#38761d";
//                }
//                break;
//        }
//        setMeasurementsToChart();
//    }
}