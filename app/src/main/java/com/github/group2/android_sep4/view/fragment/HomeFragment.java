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
import com.github.group2.android_sep4.view.GreenhouseSpecificViewModel;
import com.github.group2.android_sep4.view.adapter.GreenHouseAdapter;
import com.github.group2.android_sep4.model.GreenHouseWithLastMeasurementModel;
import com.github.group2.android_sep4.viewmodel.HomeViewModel;
import com.github.group2.android_sep4.viewmodel.UserViewModel;
import com.github.group2.android_sep4.viewmodel.MeasurementViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class HomeFragment extends Fragment {


    FloatingActionButton addBtn;
    RecyclerView recyclerView;
    GreenHouseAdapter adapter;
    HomeViewModel homeViewModel;
    UserViewModel userViewModel;
    MeasurementViewModel measurementViewModel;
    GreenhouseSpecificViewModel greenhouseSpecificViewModel;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initializeAllFields(view);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        measurementViewModel = new ViewModelProvider(this).get(MeasurementViewModel.class);
        greenhouseSpecificViewModel = new ViewModelProvider(this).get(GreenhouseSpecificViewModel.class);

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
            homeViewModel.searchGreenHousesWithLastMeasurement(userViewModel.getCurrentUser().getValue().getId());
            homeViewModel.getGreenHousesWWithLastMeasurement().observe(getViewLifecycleOwner(), this::updateGreenHouseList);
        }

    }

    private void greenHouseClicked(GreenHouseWithLastMeasurementModel greenHouseWithLastMeasurementModel) {

        greenhouseSpecificViewModel.setSelectedGreenHouse(greenHouseWithLastMeasurementModel);


        navController.navigate(R.id.greenhouseFragment);
    }

    private void updateGreenHouseList(List<GreenHouseWithLastMeasurementModel> greenHouses) {

        if (greenHouses == null) {
            return;
        }

        adapter.setGreenHouses(greenHouses);

    }

    private void initializeAllFields(View view) {
        addBtn = view.findViewById(R.id.addBtn);
        recyclerView = view.findViewById(R.id.recyclerView);
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
    }
}
