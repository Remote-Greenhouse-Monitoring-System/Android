package com.github.group2.android_sep4.view.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.view.GreenHouseWithLastMeasurementModel;
import com.github.group2.android_sep4.viewmodel.HomeViewModel;
import com.github.group2.android_sep4.view.adapter.GreenHouseAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

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
        initializeAllFields(view);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GreenHouseAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this:: greenHouseClicked);

        viewModel.searchAllGreenHouses();
        viewModel.getGreenHouseList().observe(getViewLifecycleOwner(), this::updateGreenHouseList);

        if (viewModel.getGreenHouseList().getValue().size()>=2){
            addBtn.hide();
        }
        addBtn.setOnClickListener(this::addGreenhouse);
        return view;

    }

    private void addGreenhouse(View view) {
        AlertDialog dialogBuilder = new AlertDialog.Builder(getContext()).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup_add_greenhouse, null);

        EditText editText = dialogView.findViewById(R.id.add_greenhouse);
        Button buttonSubmit =  dialogView.findViewById(R.id.buttonSubmit);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS
                //viewModel.addGreenhouse(new Greenhouse(editText.getText().toString()));
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    private void greenHouseClicked(GreenHouseWithLastMeasurementModel greenHouseWithLastMeasurementModel) {
        String greenHouseName = greenHouseWithLastMeasurementModel.getGreenHouseName();
        long greenHouseId = greenHouseWithLastMeasurementModel.getGreenHouseId();

        // TODO change view with the info
        // FancyToast.makeText(getContext(), "Greenhouse name: " + greenHouseName + " Greenhouse id: " + greenHouseId, FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();

        navController.navigate(R.id.greenhouseFragment);
    }

    private void updateGreenHouseList(List<GreenHouseWithLastMeasurementModel> greenHouseWithLastMeasurementModels) {
        adapter.setGreenHouses(greenHouseWithLastMeasurementModels);
    }

    private void initializeAllFields(View view) {
        addBtn = view.findViewById(R.id.addBtn);
        recyclerView = view.findViewById(R.id.recyclerView);
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
    }
}
