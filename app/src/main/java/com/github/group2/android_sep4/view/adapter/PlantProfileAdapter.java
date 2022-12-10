package com.github.group2.android_sep4.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.view.activity.MainActivity;
import com.github.group2.android_sep4.view.fragment.SelectPlantProfileFragment;
import com.github.group2.android_sep4.view.uielements.DeletePlantProfilePopup;
import com.github.group2.android_sep4.viewmodel.PlantProfileViewModel;


import java.util.ArrayList;
import java.util.List;

public class PlantProfileAdapter extends RecyclerView.Adapter<PlantProfileAdapter.ViewHolder> {

    private ArrayList<PlantProfile> plantProfiles;
    private ImageButton editButton, deleteButton;
    private DeletePlantProfilePopup deletePopup;
    private long plantId;
    private boolean isConfirmed;
    private NavController navController;
    private AppCompatActivity activity;
    private PlantProfileViewModel plantProfileViewModel;



    public PlantProfileAdapter(ArrayList<PlantProfile> plantProfiles) {
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
        activity = (AppCompatActivity) view.getContext();
        navController = Navigation.findNavController(activity,R.id.fragment_container);
        plantProfileViewModel = new PlantProfileViewModel();
    }

    private void deletePlantProfile(View view, long id) {
        deletePopup = new DeletePlantProfilePopup();
        deletePopup.showPopupWindow(view,id);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.plantProfileName.setText(plantProfiles.get(position).getName());
        holder.plantProfileDescription.setText(plantProfiles.get(position).getDescription());

        holder.plantProfileOptimalTemperature.setText(plantProfiles.get(position).getOptimalTemp()+"\n°C");
        holder.plantProfileOptimalHumidity.setText(plantProfiles.get(position).getOptimalHumidity()+"\n%");
        holder.plantProfileOptimalCO2.setText(Math.round(plantProfiles.get(position).getOptimalCo2())+"\nppm");
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("DELETE",plantProfiles.get(position).getName());
                deletePlantProfile(v,plantProfiles.get(position).getId());
            }
        });
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("EDIT",plantProfiles.get(position).getName());
                plantProfileViewModel.searchPlantProfile(plantProfiles.get(position).getId());
                plantProfileViewModel.searchThreshold(plantProfiles.get(position).getId());
                navController.navigate(R.id.editPlantProfileFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plantProfiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView plantProfileName, plantProfileDescription;
        private TextView plantProfileOptimalTemperature, plantProfileOptimalHumidity, plantProfileOptimalCO2, plantProfileOptimalLight;
        private ImageButton deleteButton,editButton;


        ViewHolder(View itemView) {
            super(itemView);
            plantProfileName = itemView.findViewById(R.id.plantProfileName);
            plantProfileDescription = itemView.findViewById(R.id.plantProfileDescription);
            plantProfileOptimalTemperature = itemView.findViewById(R.id.plantProfileOptimalTemperature);
            plantProfileOptimalHumidity = itemView.findViewById(R.id.plantProfileOptimalHumidity);
            plantProfileOptimalCO2 = itemView.findViewById(R.id.plantProfileOptimalCO2);
            plantProfileOptimalLight = itemView.findViewById(R.id.plantProfileOptimalLight);
            deleteButton= itemView.findViewById(R.id.deletePlantProfileButton);
            editButton = itemView.findViewById(R.id.editPlantProfileButton);
        }
    }
}

