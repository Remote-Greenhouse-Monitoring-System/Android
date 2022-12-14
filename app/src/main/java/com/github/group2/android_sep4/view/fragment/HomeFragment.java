package com.github.group2.android_sep4.view.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.Greenhouse;
import com.github.group2.android_sep4.view.adapter.GreenhouseAdapter;
import com.github.group2.android_sep4.viewmodel.HomeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class HomeFragment extends Fragment {

    FloatingActionButton addBtn;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    GreenhouseAdapter adapter;
    HomeViewModel viewModel;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        initializeAllFields(view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GreenhouseAdapter(viewModel);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this::greenHouseClicked);
        initializeGreenHouses();

        setObservers();

        addBtn.setOnClickListener(this::addGreenhouse);

        return view;
    }

    private void setObservers() {
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), s -> {
            if (s != null) {
                progressBar.setVisibility(View.GONE);
                FancyToast.makeText(getContext(), s, FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                viewModel.resetInfo();
            }
        });

        viewModel.getSuccessMessage().observe(getViewLifecycleOwner(), s -> {
            if (s != null) {
                progressBar.setVisibility(View.GONE);
                FancyToast.makeText(getContext(), s, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                viewModel.resetInfo();
            }
        });
        viewModel.getGreenHousesWithLastMeasurement().observe(getViewLifecycleOwner(), greenHouseWithLastMeasurementModels -> {
            if (greenHouseWithLastMeasurementModels != null) {
                adapter.setGreenhouses(greenHouseWithLastMeasurementModels);
            }
        });
    }

    private void initializeGreenHouses() {

        if (viewModel.getCurrentUser().getValue() != null) {
            viewModel.searchAllGreenhousesForAUser(viewModel.getCurrentUser().getValue().getId());
            viewModel.getGreenHousesWithLastMeasurement().observe(getViewLifecycleOwner(), this::updateGreenHouseList);
        }
    }

    private void addGreenhouse(View view) {
        AlertDialog dialogBuilder = new AlertDialog.Builder(getContext()).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup_add_greenhouse, null);

        TextInputLayout inputLayout = dialogView.findViewById(R.id.greenhouseName);
        Button buttonSubmit =  dialogView.findViewById(R.id.buttonSubmit);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);
        AppCompatAutoCompleteTextView spinner = dialogView.findViewById(R.id.select_device);
        spinner.setShowSoftInputOnFocus(false);
        spinner.setCursorVisible(false);


        List<String> devices = viewModel.getDevices();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, devices);
        spinner.setAdapter(adapter);

        buttonCancel.setOnClickListener(view1 -> dialogBuilder.dismiss());

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS
                String name = inputLayout.getEditText().getText().toString().trim();
                String deviceEui = spinner.getText().toString().trim();

                if(deviceEui.isEmpty()){
                    FancyToast.makeText(getContext(), "Please select a device id", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                    return;
                }

                if (name.isEmpty()) {
                    inputLayout.setError("Name is required");
                    inputLayout.requestFocus();
                    return;
                }
                else if (name.length() > 25) {
                    inputLayout.setError("Name is too long");
                    inputLayout.requestFocus();
                    return;
                }
                else {
                    inputLayout.setError(null);
                    inputLayout.setErrorEnabled(false);

                    Greenhouse greenhouseToAdd = new Greenhouse(name, deviceEui);
                    greenhouseToAdd.setDeviceEui(deviceEui);
                    viewModel.addGreenhouse(viewModel.getCurrentUser().getValue().getId(), greenhouseToAdd);
                    dialogBuilder.dismiss();
                }
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    private void greenHouseClicked(Greenhouse greenhouse) {
        viewModel.setSelectedGreenhouse(greenhouse);
        navController.navigate(R.id.greenhouseFragment);
    }

    private void updateGreenHouseList(List<Greenhouse> greenhouses) {
        if (greenhouses == null) {
            return;
        }

        progressBar.setVisibility(View.GONE);
        if (greenhouses.size()>=2){
            addBtn.hide();
        } else {
            addBtn.show();
        }

        adapter.setGreenhouses(greenhouses);
    }

    private void initializeAllFields(View view) {
        addBtn = view.findViewById(R.id.addBtn);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
    }
}
