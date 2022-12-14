package com.github.group2.android_sep4.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.Greenhouse;
import com.github.group2.android_sep4.model.Measurement;

import java.util.ArrayList;
import java.util.List;

public class GreenhouseAdapter extends RecyclerView.Adapter<GreenhouseAdapter.ViewHolder> {

    private List<Greenhouse> greenhouses = new ArrayList<>();
    private OnItemClickListener listener;
    private Context context;

    public void setGreenhouses(List<Greenhouse> greenhouses) {
        this.greenhouses.clear();
        this.greenhouses.addAll(greenhouses);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public GreenhouseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.greenhouse_element, parent, false);

        context = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GreenhouseAdapter.ViewHolder holder, int position) {
        Greenhouse greenhouse = greenhouses.get(position);
        holder.name.setText(greenhouse.getName());

        Measurement lastMeasurement = greenhouse.getLastMeasurement();
        if (lastMeasurement == null
                || lastMeasurement.isAllZeros()) {
            holder.co2.setText(R.string.no_data);
            holder.temperature.setText(R.string.no_data);
            holder.humidity.setText(R.string.no_data);
            holder.light.setText(R.string.no_data);
        } else {
            holder.co2.setText(context.getString(R.string.unit_CO2, lastMeasurement.getTemperature()));
            holder.temperature.setText(context.getString(R.string.unit_temperature, lastMeasurement.getTemperature()));
            holder.humidity.setText(context.getString(R.string.unit_humidity, lastMeasurement.getTemperature()));
            holder.light.setText(lastMeasurement.getTemperature() + " lux");
        }
    }

    @Override
    public int getItemCount() {
        return greenhouses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name, temperature, humidity, co2, light;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.greenhouseName);
            temperature = itemView.findViewById(R.id.greenhouseTemperature);
            humidity = itemView.findViewById(R.id.greenhouseHumidity);
            co2 = itemView.findViewById(R.id.greenhouseCo2);
            light = itemView.findViewById(R.id.greenhouseLight);

            itemView.setOnClickListener(v -> {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onItemClick(greenhouses.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Greenhouse greenhouse);
    }
}
