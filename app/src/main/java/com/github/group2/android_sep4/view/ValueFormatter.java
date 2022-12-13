package com.github.group2.android_sep4.view;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter {

    @Override
    public String getFormattedValue(float value) {
        long millis= (long) (value*1000);
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm, dd-MMM");
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
}

