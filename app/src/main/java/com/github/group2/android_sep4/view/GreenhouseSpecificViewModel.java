package com.github.group2.android_sep4.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.Measurement;
import com.github.group2.android_sep4.repository.GreenHouseRepository;
import com.github.group2.android_sep4.repository.implementaion.GreenHouseRepositoryImpl;
import com.github.group2.android_sep4.viewmodel.GreenHouseWithLastMeasurementModel;

public class GreenhouseSpecificViewModel extends ViewModel
{
    private final GreenHouseRepository repository = GreenHouseRepositoryImpl.getInstance();
    // TODO: plant profile repository?

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
}
