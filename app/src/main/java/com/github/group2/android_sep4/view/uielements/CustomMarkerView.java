package com.github.group2.android_sep4.view.uielements;

import android.content.Context;
import android.widget.TextView;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.view.ValueFormatter;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;

public class CustomMarkerView extends MarkerView {

    private TextView tvContent;
    private ValueFormatter valueFormatter;
    private DecimalFormat decimalFormat= new DecimalFormat("#.#");

    public CustomMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        // this markerview only displays a textview
        tvContent = (TextView) findViewById(R.id.tvContent);
        valueFormatter=new ValueFormatter();
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText(valueFormatter.getFormattedValue(e.getX()) + ": "+decimalFormat.format(e.getY())); // set the entry-value as the display text
    }
    private MPPointF mOffset;
    @Override
    public MPPointF getOffset() {
        if(mOffset == null) {
            // center the marker horizontally and vertically
            mOffset = new MPPointF(-(getWidth() / 2), -getHeight());
        }
        return mOffset;
    }
}