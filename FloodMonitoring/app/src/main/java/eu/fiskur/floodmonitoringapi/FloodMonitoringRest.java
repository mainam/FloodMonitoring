package eu.fiskur.floodmonitoringapi;

import com.squareup.okhttp.ResponseBody;

import eu.fiskur.floodmonitoringapi.model.Readings;
import eu.fiskur.floodmonitoringapi.stations.Station;
import eu.fiskur.floodmonitoringapi.stations.Stations;
import eu.fiskur.floodmonitoringapi.model.ThreeDayForecast;
import eu.fiskur.floodmonitoringapi.model.Flood;
import eu.fiskur.floodmonitoringapi.model.Floods;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.Url;
import rx.Observable;

//http://environment.data.gov.uk/flood-monitoring/doc/reference
public interface FloodMonitoringRest {

    //Flood warnings and alerts
    @GET("id/floods")
    Observable<Floods> getWarnings(
            @Query("min-severity") Integer minSeverity,
            @Query("county") String county,
            @Query("lat") Double lat,
            @Query("long") Double lon,
            @Query("dist") Integer dist
    );

    @GET("id/floodAreas/{floodAreaID}")
    Observable<Floods> getFloodArea(
            @Path("floodAreaID") String floodAreaId
    );

    @GET
    Observable<Flood> getFloodAreaFromUrl(@Url String url);

    //Measurement stations
    @GET("id/stations")
    Observable<Stations> getStations(
            @Query("county") String county,
            @Query("lat") Double lat,
            @Query("long") Double lon,
            @Query("dist") Integer dist
    );

    @GET
    Observable<Station> getStationFromUrl(@Url String url);

    @GET
    Observable<Readings> getReadings(
            @Url String url,
            @Query("_sorted") int sorted,
            @Query("_limit") int count
    );

    @GET
    Observable<Readings> getReadingsToday(
            @Url String url,
            @Query("_sorted") int sorted,
            @Query("today") int count
    );

    @GET
    Observable<Readings> getReadingsDays(
            @Url String url,
            @Query("_sorted") int sorted,
            @Query("startdate") String startDate,
            @Query("enddate") String endDate
    );

    //3 day forecast
    @GET("id/3dayforecast")
    Observable<ThreeDayForecast> get3DayForecast();

    //Base64 encoded images - day: 1, 2, or 3
    @GET("id/3dayforecast/image/{day}")
    Observable<ResponseBody> getDayImageBytes(
            @Path("day") int day);


    @GET
    Observable<ResponseBody> getRawResponse(@Url String url);

}
