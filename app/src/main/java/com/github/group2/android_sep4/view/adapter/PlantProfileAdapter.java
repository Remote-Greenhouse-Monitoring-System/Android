package com.github.group2.android_sep4.view.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.viewmodel.AddPlantProfileViewModel;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

public class PlantProfileAdapter extends RecyclerView.Adapter<PlantProfileAdapter.ViewHolder> {

    private List<PlantProfile> plantProfiles;
    private NavController navController;
    private AddPlantProfileViewModel addPlantProfileViewModel;
    private OnItemClickListener listener;




    public PlantProfileAdapter() {
        this.plantProfiles = new ArrayList<>();
    }


    public void setPlantProfiles(List<PlantProfile> plantProfiles) {
        this.plantProfiles = plantProfiles;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        navController = Navigation.findNavController(parent);
        addPlantProfileViewModel = new ViewModelProvider((AppCompatActivity) parent.getContext()).get(AddPlantProfileViewModel.class);

        View view = inflater.inflate(R.layout.plant_profile_list_item, parent, false);
        return new ViewHolder(view);
    }



    private void deletePlantProfile( long id) {
       addPlantProfileViewModel.deletePlantProfile(id);


    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }



    @Override
    public void onBindViewHolder(@NonNull PlantProfileAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.plantProfileName.setText(plantProfiles.get(position).getName());
        holder.plantProfileDescription.setText(plantProfiles.get(position).getDescription());

        holder.plantProfileOptimalTemperature.setText(plantProfiles.get(position).getOptimalTemperature() + "\n°C");
        holder.plantProfileOptimalHumidity.setText(plantProfiles.get(position).getOptimalHumidity() + "\n%");
        holder.plantProfileOptimalCO2.setText(Math.round(plantProfiles.get(position).getOptimalCo2()) + "\nppm");
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Are you sure you want to delete this plant profile?";
                    FancyAlertDialog.Builder.with(holder.itemView.getContext())
                            .setTitle("Delete Plant Profile")
                            .setBackgroundColorRes(R.color.palette_red)
                            .setMessage(message)
                            .setNegativeBtnText("Cancel")
                            .setPositiveBtnBackgroundRes(R.color.palette_red)
                            .setPositiveBtnText("Confirm")
                            .setNegativeBtnBackgroundRes(R.color.palette_grey)
                            .setAnimation(Animation.SLIDE)
                            .isCancellable(true)
                            .setIcon(R.drawable.ic_baseline_delete_outline_24, View.VISIBLE)
                            .onPositiveClicked(dialog -> {
                                deletePlantProfile(plantProfiles.get(position).getId());
                                FancyToast.makeText(holder.itemView.getContext(), "Plant profile deleted", FancyToast.LENGTH_LONG, FancyToast.INFO, false).show();
                            })
                            .onNegativeClicked(dialog -> {
                                dialog.dismiss();
                                FancyToast.makeText(holder.itemView.getContext(), "Cancelled", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();

                            })
                            .build()
                            .show();


//                navController.navigate(R.id.homeFragment);
            }
        });
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editBtnPressed(position);
            }
        });

    }


    private void editBtnPressed(int position) {

//        addPlantProfileViewModel.searchPlantProfile(plantProfiles.get(position).getId());

        addPlantProfileViewModel.setPlantProfileToEdit(plantProfiles.get(position));
        navController.navigate(R.id.editPlantProfileFragment);
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

