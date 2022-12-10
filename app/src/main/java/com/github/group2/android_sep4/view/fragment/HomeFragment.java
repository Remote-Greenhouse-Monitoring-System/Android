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
import com.github.group2.android_sep4.view.adapter.GreenHouseAdapter;
import com.github.group2.android_sep4.model.GreenhouseWithLastMeasurementModel;
import com.github.group2.android_sep4.viewmodel.HomeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shashank.sony.fancytoastlib.FancyToast;

public class HomeFragment extends Fragment {

    FloatingActionButton addBtn;
    RecyclerView recyclerView;
    GreenHouseAdapter adapter;
    HomeViewModel viewModel;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        initializeAllFields(view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GreenHouseAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this::greenHouseClicked);

        setObservers();

        return view;
    }

    private void setObservers() {
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), s -> {
            if (s != null) {
                FancyToast.makeText(getContext(), s, FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
            }
        });

        viewModel.getSuccessMessage().observe(getViewLifecycleOwner(), s -> {
            if (s != null) {
                FancyToast.makeText(getContext(), s, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
            }
        });

        viewModel.getGreenHousesWithLastMeasurement().observe(getViewLifecycleOwner(), greenHouseWithLastMeasurementModels -> {
            if (greenHouseWithLastMeasurementModels != null) {
                adapter.setGreenHouses(greenHouseWithLastMeasurementModels);
            }
        });
    }

    private void greenHouseClicked(GreenhouseWithLastMeasurementModel greenHouseWithLastMeasurementModel) {
        viewModel.setSelectedGreenhouse(greenHouseWithLastMeasurementModel);
        navController.navigate(R.id.greenhouseFragment);
    }

    private void initializeAllFields(View view) {
        addBtn = view.findViewById(R.id.addBtn);
        recyclerView = view.findViewById(R.id.recyclerView);
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
    }
}
