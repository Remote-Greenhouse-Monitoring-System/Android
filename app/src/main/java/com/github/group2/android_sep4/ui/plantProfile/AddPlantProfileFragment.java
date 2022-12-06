package com.github.group2.android_sep4.ui.plantProfile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.github.group2.android_sep4.R;

public class AddPlantProfileFragment extends Fragment {
    private ImageButton backButton;
    private NavController navController;

    public AddPlantProfileFragment() {
        // Required empty public constructor
    }

    public static AddPlantProfileFragment newInstance(String param1, String param2) {
        AddPlantProfileFragment fragment = new AddPlantProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_plant_profile, container, false);
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
        backButton = view.findViewById(R.id.addPlantProfileBackButton);
        backButton.setOnClickListener(this::goBack);
        return view;
    }

    private void goBack(View view) {
        navController.popBackStack();
    }
}