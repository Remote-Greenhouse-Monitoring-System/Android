package com.github.group2.android_sep4.networking;

import static com.github.group2.android_sep4.repository.ServiceGenerator.API_KEY;

import com.github.group2.android_sep4.model.Measurement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


/**
 *  This is the interface for the MeasurementApi. It contains all the methods that are used to get the
 *  measurements from the server.
 */
public interface MeasurementApi {

    String route = "Measurement";

    @Headers({API_KEY})
    @GET(route+"/all/{gId}/{amount}")
    Call<List<Measurement>> getMeasurements(@Path("gId") long greenHouseId, @Path("amount") int amount);

    @Headers({API_KEY})
    @GET(route+ "last/{gId}")
    Call<Measurement> getLastMeasurement(@Path("gId") long greenHouseId);

    @Headers({API_KEY})
    @GET(route + "allPerHours/{gId}/{hours}")
    Call<List<Measurement>> getAllMeasurementsPerHour(@Path("gId") long greenHouseId, @Path("hours")int hours );

    @Headers({API_KEY})
    @GET(route + "allPerDays/{gId}/{days}")
    Call<List<Measurement>> getAllMeasurementPerDays(@Path("gId") long greenHouseId, @Path("days") int days);

    @Headers({API_KEY})
    @GET(route + "allPerMonth/{gId}/{month}/{year}")
    Call<List<Measurement>> getAllMeasurementPerMonth(@Path("gId") long greenHouseId, @Path("month")int month,  @Path("year") int year);

    @Headers({API_KEY})
    @GET(route +"allPerYear/{gId}/{year}")
    Call<List<Measurement>> getAllMeasurementPerYear(@Path("gId") long greenHouseId, @Path("year") int year);

}
