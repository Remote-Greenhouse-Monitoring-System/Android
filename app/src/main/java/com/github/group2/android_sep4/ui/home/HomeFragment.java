package com.github.group2.android_sep4.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.ui.greenhouse.GreenhouseFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class HomeFragment extends Fragment {


    FloatingActionButton addBtn;
    RecyclerView recyclerView;
    GreenHouseAdapter adapter;
    HomeViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initializeAllFields(view);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GreenHouseAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this:: greenHouseClicked);

        viewModel.searchAllGreenHouses();
        viewModel.getGreenHouseList().observe(getViewLifecycleOwner(), this::updateGreenHouseList);
        return view;

    }

    private void greenHouseClicked(GreenHouseWithLastMeasurementModel greenHouseWithLastMeasurementModel) {
        String greenHouseName = greenHouseWithLastMeasurementModel.getGreenHouseName();
        long greenHouseId = greenHouseWithLastMeasurementModel.getGreenHouseId();

        // TODO change view with the info
        FancyToast.makeText(getContext(), "Greenhouse name: " + greenHouseName + " Greenhouse id: " + greenHouseId, FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
    }

    private void updateGreenHouseList(List<GreenHouseWithLastMeasurementModel> greenHouseWithLastMeasurementModels) {
        adapter.setGreenHouses(greenHouseWithLastMeasurementModels);
    }

    private void initializeAllFields(View view) {
        addBtn = view.findViewById(R.id.addBtn);
        recyclerView = view.findViewById(R.id.recyclerView);
    }
}
