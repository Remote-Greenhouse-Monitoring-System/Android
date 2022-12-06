package com.github.group2.android_sep4.repository.threshold;

import androidx.lifecycle.LiveData;

import com.github.group2.android_sep4.entity.Threshold;

public interface ThresholdRepository {


    void searchThreshold( long plantProfileId);
    LiveData<Threshold> getSearchedThreshold();

    void updateThreshold( long plantProfileId, Threshold threshold);

    LiveData<String> getErrorMessage();
    LiveData<String> getSuccessMessage();

}
