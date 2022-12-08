package com.github.group2.android_sep4.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.GreenHouse;
import com.github.group2.android_sep4.model.Measurement;
import com.github.group2.android_sep4.view.adapter.GreenHouseAdapter;
import com.github.group2.android_sep4.viewmodel.GreenHouseWithLastMeasurementModel;
import com.github.group2.android_sep4.viewmodel.HomeViewModel;
import com.github.group2.android_sep4.viewmodel.PlantProfileViewModel;
import com.github.group2.android_sep4.viewmodel.UserViewModel;
import com.github.group2.android_sep4.viewmodel.MeasurementViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    FloatingActionButton addBtn;
    RecyclerView recyclerView;
    GreenHouseAdapter adapter;
    HomeViewModel homeViewModel;
    UserViewModel userViewModel;
    MeasurementViewModel measurementViewModel;
    PlantProfileViewModel plantProfileViewModel;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initializeAllFields(view);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        measurementViewModel = new ViewModelProvider(this).get(MeasurementViewModel.class);
        plantProfileViewModel = new PlantProfileViewModel();
        plantProfileViewModel.searchPlantProfilesForUser(userViewModel.getCurrentUser().getValue().getId());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GreenHouseAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this::greenHouseClicked);

        homeViewModel.getErrorMessage().observe(getViewLifecycleOwner(), s -> {
            if (s != null) {
                FancyToast.makeText(getContext(), s, FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
            }
        });

        homeViewModel.getSuccessMessage().observe(getViewLifecycleOwner(), s -> {
            if (s != null) {
                FancyToast.makeText(getContext(), s, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
            }
        });
        initializeGreenHouses();

        return view;

    }

    private void initializeGreenHouses() {

        if (userViewModel.getCurrentUser().getValue() != null) {
            homeViewModel.searchAllGreenHouses(userViewModel.getCurrentUser().getValue().getId());
            homeViewModel.getGreenHouseList().observe(getViewLifecycleOwner(), this::updateGreenHouseList);
        }

    }

    private void greenHouseClicked(GreenHouseWithLastMeasurementModel greenHouseWithLastMeasurementModel) {
        String greenHouseName = greenHouseWithLastMeasurementModel.getGreenHouseName();
        long greenHouseId = greenHouseWithLastMeasurementModel.getGreenHouseId();

        // TODO change view with the info
        // FancyToast.makeText(getContext(), "Greenhouse name: " + greenHouseName + " Greenhouse id: " + greenHouseId, FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();

        navController.navigate(R.id.greenhouseFragment);
    }

    private void updateGreenHouseList(List<GreenHouse> greenHouses) {

        if (greenHouses == null) {
            return;
        }
        List<GreenHouseWithLastMeasurementModel> greenHouseWithLastMeasurementModels = new ArrayList<>();

        for (GreenHouse greenHouse : greenHouses) {
            long greenHouseId = greenHouse.getId();
            String greenHouseName = greenHouse.getName();
            measurementViewModel.searchLastMeasurement(greenHouseId);


            Measurement lastMeasurement = new Measurement();
            measurementViewModel.getSearchedMeasurement().observe(getViewLifecycleOwner(), measurement -> {
                if (measurement != null) {
                    lastMeasurement.setCo2(measurement.getCo2());
                    lastMeasurement.setHumidity(measurement.getHumidity());
                    lastMeasurement.setTemperature(measurement.getTemperature());
                    lastMeasurement.setLight(measurement.getLight());

                }
            });

            GreenHouseWithLastMeasurementModel greenHouseWithLastMeasurementModel = new GreenHouseWithLastMeasurementModel(greenHouseId, greenHouseName, lastMeasurement);
            greenHouseWithLastMeasurementModels.add(greenHouseWithLastMeasurementModel);
        }

        adapter.setGreenHouses(greenHouseWithLastMeasurementModels);

    }

    private void initializeAllFields(View view) {
        addBtn = view.findViewById(R.id.addBtn);
        recyclerView = view.findViewById(R.id.recyclerView);
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
    }
}
