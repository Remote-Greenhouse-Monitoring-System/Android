package com.github.group2.android_sep4.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.Measurement;
import com.github.group2.android_sep4.repository.GreenHouseRepository;
import com.github.group2.android_sep4.repository.PlantProfileRepository;
import com.github.group2.android_sep4.repository.implementaion.GreenHouseRepositoryImpl;
import com.github.group2.android_sep4.model.GreenHouseWithLastMeasurementModel;
import com.github.group2.android_sep4.repository.implementaion.PlantProfileRepositoryImpl;
import com.github.group2.android_sep4.viewmodel.PlantProfileViewModel;

public class GreenhouseSpecificViewModel extends ViewModel
{
    private final GreenHouseRepository repository = GreenHouseRepositoryImpl.getInstance();
    private final PlantProfileRepository plantProfileRepository = PlantProfileRepositoryImpl.getInstance();

    public LiveData<GreenHouseWithLastMeasurementModel> getSelectedGreenhouse()
    {
        return repository.getSelectedGreenhouse();
    }

    public void setSelectedGreenHouse(GreenHouseWithLastMeasurementModel greenHouseWithLastMeasurementModel) {
        repository.setSelectedGreenHouse(greenHouseWithLastMeasurementModel);
    }

    public void searchPlantProfilesForUser(long userId)
    {
        plantProfileRepository.searchPlantProfilesForUser(userId);
    }
}
