package com.github.group2.android_sep4.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.repository.GreenHouseRepository;
import com.github.group2.android_sep4.repository.implementaion.GreenHouseRepositoryImpl;
import com.github.group2.android_sep4.model.GreenHouse;

public class GreenhouseSpecificViewModel extends ViewModel
{
    private final GreenHouseRepository repository = GreenHouseRepositoryImpl.getInstance();
    // TODO: plant profile repository?

    public LiveData<GreenHouse> getSelectedGreenhouse()
    {
        return repository.getSelectedGreenhouse();
    }

    public void setSelectedGreenHouse(GreenHouse greenHouse) {
        repository.setSelectedGreenHouse(greenHouse);


    }

    public void deleteGreenhouse(long id) {
        repository.deleteGreenHouse(id);
    }
}
