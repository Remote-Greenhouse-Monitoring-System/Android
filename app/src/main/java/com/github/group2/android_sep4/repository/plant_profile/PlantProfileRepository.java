package com.github.group2.android_sep4.repository.plant_profile;

import com.github.group2.android_sep4.entity.PlantProfile;

import java.util.List;

public interface PlantProfileRepository {

    PlantProfile createPlantProfile(PlantProfile plantProfile);
    void removePlantProfile(long id);
    PlantProfile updatePlantProfile(PlantProfile plantProfile);
    List<PlantProfile> getPremadePlantProfiles(int pageNumber, int pageSize);
    PlantProfile getPlantProfileById(long id);
    void activatePlantProfile(long id);
}
