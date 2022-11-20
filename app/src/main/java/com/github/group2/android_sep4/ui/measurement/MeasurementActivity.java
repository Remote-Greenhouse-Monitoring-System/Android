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
    private MeasurementViewModal measurementViewModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTitleBar();
        setContentView(R.layout.activity_measurement);
        initilizeViews();
        initializeChart();

    }

    private void initilizeViews() {
        measurementViewModal = new MeasurementViewModal();
    }

    private void initializeChart() {

        lineChart = findViewById(R.id.lineChart);

        ArrayList<Measurement> measurements = new ArrayList<>();

        measurements.add(new Measurement(1, 1, 1, 1, 1, 1, "2020-01-01 00:00:14"));
        measurements.add(new Measurement(2, 1, 30, 1, 2, 1, "2020-01-01 00:05:14"));
        measurements.add(new Measurement(3, 1, 25, 1, 3, 1, "2020-01-01 00:10:14"));
        measurements.add(new Measurement(4, 1, 26, 1, 1, 1, "2020-01-01 00:15:14"));
        measurements.add(new Measurement(5, 1, 19, 1, 8, 1, "2020-01-01 00:20:14"));
        measurements.add(new Measurement(6, 1, 20, 1, 9, 1, "2020-01-01 00:25:14"));
        measurements.add(new Measurement(7, 1, 21, 1, 10, 1, "2020-01-01 00:30:14"));
        measurements.add(new Measurement(8, 1, 22, 1, 11, 1, "2020-01-01 00:35:14"));
        measurements.add(new Measurement(9, 1, 23, 1, 12, 1, "2020-01-01 00:40:14"));
        measurements.add(new Measurement(10, 1, 24, 1, 13, 1, "2020-01-01 00:45:14"));
        measurements.add(new Measurement(11, 1, 25, 1, 14, 1, "2020-01-01 00:50:14"));

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < measurements.size(); i++) {
            values.add(new Entry(convertDateToFloat(measurements.get(i).getDateTimeAsString()), measurements.get(i).getTemperature()));
        }
        LineDataSet set1 = new LineDataSet(values, "Temperature");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data = new LineData(dataSets);

        lineChart.setData(data);
        lineChart.invalidate();

        setupChart();


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
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);

        ValueFormatter formatter = new ValueFormatter();
        xAxis.setValueFormatter(formatter);
        xAxis.setLabelCount(3);

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.co2_radio_button:
                if (checked) {
                    ArrayList<Measurement> measurements = new ArrayList<>();
                    measurements.add(new Measurement(1, 1, 1, 54, 1, 100000, "2020-01-01 00:00:14"));
                    measurements.add(new Measurement(2, 1, 30, 44, 2, 1123414, "2020-01-01 00:05:14"));
                    measurements.add(new Measurement(3, 1, 25, 42, 3, 42341, "2020-01-01 00:10:14"));
                    measurements.add(new Measurement(4, 1, 26, 100, 1, 1234, "2020-01-01 00:15:14"));
                    measurements.add(new Measurement(5, 1, 19, 90, 8, 12341, "2020-01-01 00:20:14"));
                    measurements.add(new Measurement(6, 1, 20, 89, 9, 11234, "2020-01-01 00:25:14"));
                    measurements.add(new Measurement(7, 1, 21, 23, 10, 63451, "2020-01-01 00:30:14"));
                    measurements.add(new Measurement(8, 1, 22, 11, 11, 13567, "2020-01-01 00:35:14"));
                    measurements.add(new Measurement(9, 1, 23, 25, 12, 12456, "2020-01-01 00:40:14"));
                    measurements.add(new Measurement(10, 1, 24, 88, 13, 65431, "2020-01-01 00:45:14"));
                    measurements.add(new Measurement(11, 1, 25, 20, 14, 3451, "2020-01-01 00:50:14"));

                    ArrayList<Entry> newValues = new ArrayList<>();

                    for (int i = 0; i < measurements.size(); i++) {
                        newValues.add(new Entry(convertDateToFloat(measurements.get(i).getDateTimeAsString()), measurements.get(i).getCo2()));
                    }
                    LineDataSet set1 = new LineDataSet(newValues, "CO2");
                    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                    dataSets.add(set1);
                    set1.setColor(Color.GREEN);
                    LineData data = new LineData(dataSets);

                    lineChart.setData(data);
                    lineChart.notifyDataSetChanged();
                    lineChart.invalidate();

                }
                break;
            case R.id.humidity_radio_button:
                if (checked) {
                    ArrayList<Measurement> measurements = new ArrayList<>();
                    measurements.add(new Measurement(1, 1, 1, 54, 1, 100000, "2020-01-01 00:00:14"));
                    measurements.add(new Measurement(2, 1, 30, 44, 2, 1123414, "2020-01-01 00:05:14"));
                    measurements.add(new Measurement(3, 1, 25, 42, 3, 42341, "2020-01-01 00:10:14"));
                    measurements.add(new Measurement(4, 1, 26, 100, 1, 1234, "2020-01-01 00:15:14"));
                    measurements.add(new Measurement(5, 1, 19, 90, 8, 12341, "2020-01-01 00:20:14"));
                    measurements.add(new Measurement(6, 1, 20, 89, 9, 11234, "2020-01-01 00:25:14"));
                    measurements.add(new Measurement(7, 1, 21, 23, 10, 63451, "2020-01-01 00:30:14"));
                    measurements.add(new Measurement(8, 1, 22, 11, 11, 13567, "2020-01-01 00:35:14"));
                    measurements.add(new Measurement(9, 1, 23, 25, 12, 12456, "2020-01-01 00:40:14"));
                    measurements.add(new Measurement(10, 1, 24, 88, 13, 65431, "2020-01-01 00:45:14"));
                    measurements.add(new Measurement(11, 1, 25, 20, 14, 3451, "2020-01-01 00:50:14"));

                    ArrayList<Entry> values = new ArrayList<>();

                    for (int i = 0; i < measurements.size(); i++) {
                        values.add(new Entry(convertDateToFloat(measurements.get(i).getDateTimeAsString()), measurements.get(i).getHumidity()));
                    }
                    LineDataSet set1 = new LineDataSet(values, "Humidity");
                    set1.setColor(Color.BLUE);
                    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                    dataSets.add(set1);
                    LineData data = new LineData(dataSets);

                    lineChart.setData(data);
                    lineChart.notifyDataSetChanged();
                    lineChart.invalidate();
                }
                break;
            case R.id.temperature_radio_button:
                if (checked) {
                    ArrayList<Measurement> measurements = new ArrayList<>();
                    measurements.add(new Measurement(1, 1, 1, 54, 1, 100000, "2020-01-01 00:00:14"));
                    measurements.add(new Measurement(2, 1, 30, 44, 2, 1123414, "2020-01-01 00:05:14"));
                    measurements.add(new Measurement(3, 1, 25, 42, 3, 42341, "2020-01-01 00:10:14"));
                    measurements.add(new Measurement(4, 1, 26, 100, 1, 1234, "2020-01-01 00:15:14"));
                    measurements.add(new Measurement(5, 1, 19, 90, 8, 12341, "2020-01-01 00:20:14"));
                    measurements.add(new Measurement(6, 1, 20, 89, 9, 11234, "2020-01-01 00:25:14"));
                    measurements.add(new Measurement(7, 1, 21, 23, 10, 63451, "2020-01-01 00:30:14"));
                    measurements.add(new Measurement(8, 1, 22, 11, 11, 13567, "2020-01-01 00:35:14"));
                    measurements.add(new Measurement(9, 1, 23, 25, 12, 12456, "2020-01-01 00:40:14"));
                    measurements.add(new Measurement(10, 1, 24, 88, 13, 65431, "2020-01-01 00:45:14"));
                    measurements.add(new Measurement(11, 1, 25, 20, 14, 3451, "2020-01-01 00:50:14"));

                    ArrayList<Entry> values = new ArrayList<>();

                    for (int i = 0; i < measurements.size(); i++) {
                        values.add(new Entry(convertDateToFloat(measurements.get(i).getDateTimeAsString()), measurements.get(i).getTemperature()));
                    }
                    LineDataSet set1 = new LineDataSet(values, "Temperature");
                    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                    dataSets.add(set1);
                    set1.setColor(Color.RED);
                    LineData data = new LineData(dataSets);

                    lineChart.setData(data);
                    lineChart.notifyDataSetChanged();
                    lineChart.invalidate();
                }
                break;
            case R.id.light_radio_button:
                if (checked) {
                    ArrayList<Measurement> measurements = new ArrayList<>();
                    measurements.add(new Measurement(1, 1, 1, 54, 1, 100000, "2020-01-01 00:00:14"));
                    measurements.add(new Measurement(2, 1, 30, 44, 2, 1123414, "2020-01-01 00:05:14"));
                    measurements.add(new Measurement(3, 1, 25, 42, 3, 42341, "2020-01-01 00:10:14"));
                    measurements.add(new Measurement(4, 1, 26, 100, 1, 1234, "2020-01-01 00:15:14"));
                    measurements.add(new Measurement(5, 1, 19, 90, 8, 12341, "2020-01-01 00:20:14"));
                    measurements.add(new Measurement(6, 1, 20, 89, 9, 11234, "2020-01-01 00:25:14"));
                    measurements.add(new Measurement(7, 1, 21, 23, 10, 63451, "2020-01-01 00:30:14"));
                    measurements.add(new Measurement(8, 1, 22, 11, 11, 13567, "2020-01-01 00:35:14"));
                    measurements.add(new Measurement(9, 1, 23, 25, 12, 12456, "2020-01-01 00:40:14"));
                    measurements.add(new Measurement(10, 1, 24, 88, 13, 65431, "2020-01-01 00:45:14"));
                    measurements.add(new Measurement(11, 1, 25, 20, 14, 3451, "2020-01-01 00:50:14"));

                    ArrayList<Entry> values = new ArrayList<>();

                    for (int i = 0; i < measurements.size(); i++) {
                        values.add(new Entry(convertDateToFloat(measurements.get(i).getDateTimeAsString()), measurements.get(i).getLight()));
                    }
                    LineDataSet set1 = new LineDataSet(values, "Light");
                    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                    dataSets.add(set1);
                    set1.setColor(Color.BLACK);
                    LineData data = new LineData(dataSets);

                    lineChart.setData(data);
                    lineChart.notifyDataSetChanged();
                    lineChart.invalidate();
                }
                break;
        }
    }
}