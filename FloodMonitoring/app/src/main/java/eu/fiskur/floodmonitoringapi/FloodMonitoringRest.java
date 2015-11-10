package eu.fiskur.floodmonitoringapi;

import eu.fiskur.floodmonitoringapi.model.FloodArea;
import eu.fiskur.floodmonitoringapi.model.Stations;
import eu.fiskur.floodmonitoringapi.model.ThreeDayForecast;
import eu.fiskur.floodmonitoringapi.model.Warnings;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

//http://environment.data.gov.uk/flood-monitoring/doc/reference
public interface FloodMonitoringRest {

    //Flood warnings and alerts
    @GET("/id/floods")
    Observable<Warnings> getWarnings(
            @Query("min-severity") Integer minSeverity,
            @Query("county") String county,
            @Query("lat") Double lat,
            @Query("long") Double lon,
            @Query("dist") Integer dist
    );

    @GET("/id/floodAreas/{floodAreaID}")
    Observable<FloodArea> getFloodArea(
            @Path("floodAreaID") String floodAreaId
    );

    //Measurement stations
    @GET("/id/stations")
    Observable<Stations> getStations(
            @Query("county") String county,
            @Query("lat") Double lat,
            @Query("long") Double lon,
            @Query("dist") Integer dist
    );

    //3 day forecast
    @GET("/id/3dayforecast")
    Observable<ThreeDayForecast> get3DayForecast();

}
