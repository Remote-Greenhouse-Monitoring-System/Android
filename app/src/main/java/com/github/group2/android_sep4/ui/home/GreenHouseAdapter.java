package com.github.group2.android_sep4.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.group2.android_sep4.R;

import java.util.ArrayList;
import java.util.List;

public class GreenHouseAdapter extends RecyclerView.Adapter<GreenHouseAdapter.ViewHolder> {
    private List<GreenHouseWithLastMeasurementModel> greenHouses = new ArrayList<>();
    private OnItemClickListener listener;

    public void setGreenHouses(List<GreenHouseWithLastMeasurementModel> greenHouses) {
        this.greenHouses.clear();
        this.greenHouses.addAll(greenHouses);
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
        GreenHouseWithLastMeasurementModel greenHouse = greenHouses.get(position);
        holder.name.setText(greenHouse.getGreenHouseName());
        holder.co2.setText(greenHouse.getLastMeasurement().getCo2() + " ppm");
        holder.temperature.setText(greenHouse.getLastMeasurement().getTemperature() + " °C");
        holder.humidity.setText(greenHouse.getLastMeasurement().getHumidity() + " %");
        holder.light.setText(greenHouse.getLastMeasurement().getLight() + " lux");
    }

    @Override
    public int getItemCount() {
        return greenHouses.size();
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
                    listener.onItemClick(greenHouses.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(GreenHouseWithLastMeasurementModel greenHouse);
    }
}
