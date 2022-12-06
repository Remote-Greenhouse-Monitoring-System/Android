package com.github.group2.android_sep4.ui.greenhouse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.entity.Measurement;
import com.github.group2.android_sep4.entity.PlantProfile;
import com.github.group2.android_sep4.repository.greenhouse.GreenHouseRepository;
import com.github.group2.android_sep4.repository.greenhouse.GreenHouseRepositoryImpl;
import com.github.group2.android_sep4.ui.home.GreenHouseWithLastMeasurementModel;

public class GreenhouseSpecificViewModel extends ViewModel
{
    private final GreenHouseRepository repository = GreenHouseRepositoryImpl.getInstance();
    // TODO: plant profile repository?
    private MutableLiveData<PlantProfile> plantProfile;

    public LiveData<GreenHouseWithLastMeasurementModel> getSelectedGreenhouse()
    {
        // TODO: get from repository
        Measurement measurement1 = new Measurement(1,1,32,25,255,25,"2020-12-12 12:12:12");

        GreenHouseWithLastMeasurementModel model = new GreenHouseWithLastMeasurementModel(1, "Greenhouse 1", measurement1);

        return new LiveData<GreenHouseWithLastMeasurementModel>() {
            @Override
            protected void setValue(GreenHouseWithLastMeasurementModel value) {
                value = model;
            }
        };
    }

    public MutableLiveData<PlantProfile> getActivePlantProfile() {
        plantProfile= new MutableLiveData<>();
        PlantProfile activeProfile = new PlantProfile(1, "Potato #1", "Description 1",
                20, 25, 320, 2000);
        plantProfile.setValue(activeProfile);
        return plantProfile;
    }

    public void removeActivePlantProfile() {
        plantProfile.setValue(null);
    }
}
