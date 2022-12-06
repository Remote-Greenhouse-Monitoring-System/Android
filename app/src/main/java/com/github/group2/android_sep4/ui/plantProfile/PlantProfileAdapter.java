package com.github.group2.android_sep4.ui.plantProfile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.entity.PlantProfile;


import java.util.ArrayList;

public class PlantProfileAdapter extends RecyclerView.Adapter<PlantProfileAdapter.ViewHolder> {
    private ArrayList<PlantProfile> plantProfiles;
    private ImageButton editButton, deleteButton;
    private DeletePlantProfilePopup deletePopup;
    private OnItemClickListener listener;

    PlantProfileAdapter(ArrayList<PlantProfile> plantProfiles) {
        this.plantProfiles = plantProfiles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.plant_profile_list_item, parent, false);

        initializeViews(view);

        return new ViewHolder(view);
    }

    private void initializeViews(View view) {
        editButton= view.findViewById(R.id.editPlantProfileButton);
        deleteButton= view.findViewById(R.id.deletePlantProfileButton);
        editButton.setOnClickListener(this:: editPlantProfile);
        deleteButton.setOnClickListener(this:: deletePlantProfile);
    }

    private void deletePlantProfile(View view) {
        deletePopup = new DeletePlantProfilePopup();
        deletePopup.showPopupWindow(view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private void editPlantProfile(View view) {
        Toast.makeText(view.getContext(), "Edit Plant Profile", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.plantProfileName.setText(plantProfiles.get(position).getName());
        holder.plantProfileDescription.setText(plantProfiles.get(position).getDescription());

        holder.plantProfileOptimalTemperature.setText(plantProfiles.get(position).getOptimalTemp()+"\nÂ°C");
        holder.plantProfileOptimalHumidity.setText(plantProfiles.get(position).getOptimalHumidity()+"\n%");
        holder.plantProfileOptimalCO2.setText(Math.round(plantProfiles.get(position).getOptimalCo2())+"\nppm");
        holder.plantProfileOptimalLight.setText(Math.round(plantProfiles.get(position).getOptimalLight())+"\nlux");
    }

    @Override
    public int getItemCount() {
        return plantProfiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView plantProfileName, plantProfileDescription;
        private final TextView plantProfileOptimalTemperature, plantProfileOptimalHumidity, plantProfileOptimalCO2, plantProfileOptimalLight;

        ViewHolder(View itemView) {
            super(itemView);
            plantProfileName = itemView.findViewById(R.id.plantProfileName);
            plantProfileDescription = itemView.findViewById(R.id.plantProfileDescription);
            plantProfileOptimalTemperature = itemView.findViewById(R.id.plantProfileOptimalTemperature);
            plantProfileOptimalHumidity = itemView.findViewById(R.id.plantProfileOptimalHumidity);
            plantProfileOptimalCO2 = itemView.findViewById(R.id.plantProfileOptimalCO2);
            plantProfileOptimalLight = itemView.findViewById(R.id.plantProfileOptimalLight);

            itemView.setOnClickListener(v -> {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onItemClick(plantProfiles.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(PlantProfile greenHouse);
    }
}