package com.github.group2.android_sep4.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.Greenhouse;

import java.util.ArrayList;
import java.util.List;

public class GreenHouseAdapter extends RecyclerView.Adapter<GreenHouseAdapter.ViewHolder> {
    private List<Greenhouse> greenhouses = new ArrayList<>();
    private OnItemClickListener listener;

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
    public GreenHouseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.greenhouse_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GreenHouseAdapter.ViewHolder holder, int position) {
        Greenhouse greenhouse = greenhouses.get(position);
        holder.name.setText(greenhouse.getName());

        if (greenhouse.getLastMeasurement() != null) {
            holder.co2.setText(greenhouse.getLastMeasurement().getCo2() + " ppm");
            holder.temperature.setText(greenhouse.getLastMeasurement().getTemperature() + " °C");
            holder.humidity.setText(greenhouse.getLastMeasurement().getHumidity() + " %");
            holder.light.setText(greenhouse.getLastMeasurement().getLight() + " lux");
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
