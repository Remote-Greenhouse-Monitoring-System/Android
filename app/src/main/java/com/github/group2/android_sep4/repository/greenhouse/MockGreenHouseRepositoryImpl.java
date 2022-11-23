package com.github.group2.android_sep4.repository.greenhouse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.group2.android_sep4.entity.Measurement;
import com.github.group2.android_sep4.ui.home.GreenHouseWithLastMeasurementModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MockGreenHouseRepositoryImpl implements GreenHouseRepository {


    public static GreenHouseRepository instance;
    private static Lock lock = new ReentrantLock();

    private MutableLiveData<List<GreenHouseWithLastMeasurementModel>> greenHouseList;

    private MockGreenHouseRepositoryImpl() {
        greenHouseList = new MutableLiveData<>();
    }

    public static GreenHouseRepository getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new MockGreenHouseRepositoryImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public LiveData<List<GreenHouseWithLastMeasurementModel>> getGreenHouseList() {
        return greenHouseList;
    }

    @Override
    public void searchAllGreenHouses() {
        Measurement measurement1 = new Measurement(1,1,32,25,255,25,"2020-12-12 12:12:12");
        Measurement measurement2 = new Measurement(2,2,37,35,292,45,"2020-12-12 12:12:12");
        Measurement measurement3 = new Measurement(3,3,32,25,255,25,"2020-12-12 12:12:12");

        GreenHouseWithLastMeasurementModel model = new GreenHouseWithLastMeasurementModel(1, "Greenhouse 1", measurement1);
        GreenHouseWithLastMeasurementModel model2 = new GreenHouseWithLastMeasurementModel(2, "Greenhouse 2", measurement2);
        GreenHouseWithLastMeasurementModel model3 = new GreenHouseWithLastMeasurementModel(3, "Greenhouse 3", measurement3);


        List<GreenHouseWithLastMeasurementModel> list =new ArrayList<>();
        list.add(model);
        list.add(model2);
        list.add(model3);

        greenHouseList.setValue(list);

    }
}

