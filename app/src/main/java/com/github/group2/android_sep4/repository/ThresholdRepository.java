package com.github.group2.android_sep4.repository;

import androidx.lifecycle.LiveData;

import com.github.group2.android_sep4.model.Threshold;

public interface ThresholdRepository {


    void searchThreshold( long plantProfileId);
    LiveData<Threshold> getSearchedThreshold();

    void updateThreshold( long plantProfileId, Threshold threshold);

    LiveData<String> getErrorMessage();
    LiveData<String> getSuccessMessage();

}
