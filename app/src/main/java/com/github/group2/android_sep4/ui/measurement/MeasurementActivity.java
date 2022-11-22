package com.github.group2.android_sep4.ui.measurement;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
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


public class MeasurementActivity extends AppCompatActivity {

    private LineChart lineChart;
    private MeasurementViewModal measurementViewModel;
    private Period period;
    private MeasurementType measurementType;
    private RadioGroup radioGroupMeasurement, radioGroupPeriod;
    private RadioButton hourRadioButton, CO2RadioButton, weekRadioButton, dayRadioButton, monthRadioButton, temperatureRadioButton, humidityRadioButton, lightRadioButton;
    private LineDataSet lineDataSet;
    private String lineColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTitleBar();
        setContentView(R.layout.activity_measurement);
        initializeViews();
        initializeChart();
    }

    private void initializeViews() {

        measurementViewModel = new MeasurementViewModal();
        period = Period.HOUR;
        measurementType = MeasurementType.TEMPERATURE;
        radioGroupMeasurement = findViewById(R.id.radioGroup);
        radioGroupPeriod = findViewById(R.id.timeRadioGroup);
        hourRadioButton = findViewById(R.id.last_hour_radio_button);
        CO2RadioButton = findViewById(R.id.co2_radio_button);
        radioGroupMeasurement.check(R.id.temperature_radio_button);
        radioGroupPeriod.check(R.id.last_day_radio_button);
    }

    private void initializeChart() {
        lineChart = findViewById(R.id.lineChart);
        setupChart();
        measurementViewModel.searchAllMeasurementPerDays(1, 1);
        setMeasurementsToChart();
        lineColor = "#cc0000";
    }

    private void hideTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
    }

    private float convertDateToFloat(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            Date date1 = format.parse(date);
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

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.co2_radio_button:
                if (checked) {
                    measurementType = MeasurementType.CO2;
                    lineColor = "#000000";

                }
                break;
            case R.id.humidity_radio_button:
                if (checked) {
                    measurementType = MeasurementType.HUMIDITY;
                    lineColor = "#2986cc";
                }
                break;
            case R.id.temperature_radio_button:
                if (checked) {
                    measurementType = MeasurementType.TEMPERATURE;
                    lineColor = "#cc0000";
                }
                break;
            case R.id.light_radio_button:
                if (checked) {
                    measurementType = MeasurementType.LIGHT;
                    lineColor = "#38761d";
                }
                break;
        }
        setMeasurementsToChart();
    }

    public void onTimeRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.last_hour_radio_button:
                if (checked) {
                    period = Period.HOUR;
                    measurementViewModel.searchAllMeasurementsPerHour(1, 1);
                }
                break;

            case R.id.last_day_radio_button:
                if (checked) {
                    period = Period.DAY;
                    measurementViewModel.searchAllMeasurementPerDays(1, 1);
                }
                break;

            case R.id.last_week_radio_button:
                if (checked) {
                    period = Period.WEEK;
                    measurementViewModel.searchAllMeasurementPerDays(1, 7);
                }
                break;

            case R.id.last_month_radio_button:
                if (checked) {
                    period = Period.MONTH;
                    measurementViewModel.searchAllMeasurementPerMonth(1, 1, 1);
                }
                break;
        }
        updatePeriod();
        setMeasurementsToChart();
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

        measurementViewModel.getSearchedMeasurementList().observe(this, measurements -> {
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
}