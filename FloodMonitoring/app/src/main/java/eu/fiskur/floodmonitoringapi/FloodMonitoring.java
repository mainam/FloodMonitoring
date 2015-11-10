package eu.fiskur.floodmonitoringapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eu.fiskur.floodmonitoringapi.model.RemedialStringType;
import eu.fiskur.floodmonitoringapi.model.RemedialStringTypeAdapter;
import eu.fiskur.floodmonitoringapi.model.Stations;
import eu.fiskur.floodmonitoringapi.model.ThreeDayForecast;
import eu.fiskur.floodmonitoringapi.model.Warnings;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import rx.Observable;

public class FloodMonitoring {
    private static final String BASE_URL = "http://environment.data.gov.uk/flood-monitoring";
    private static FloodMonitoring instance = null;
    private FloodApiLogger floodApiLogger;

    public synchronized static FloodMonitoring getInstance(){
        if(instance == null){
            instance = new FloodMonitoring();
        }
        return instance;
    }

    private FloodMonitoringRest rest;

    private FloodMonitoring(){
        buildRest(true);
    }

    public void logOutput(boolean logOutput){
        buildRest(logOutput);
    }

    private void buildRest(boolean logOutput){
        RestAdapter.Builder builder = new RestAdapter.Builder();
        if(logOutput){
            floodApiLogger = FloodApiLogger.getInstance();

            builder.setLogLevel(RestAdapter.LogLevel.FULL);
            builder.setLog(new RestAdapter.Log() {
                @Override
                public void log(String msg) {
                    floodApiLogger.log(msg);
                }
            });
            builder.setLogLevel(RestAdapter.LogLevel.FULL);
        }else{
            builder.setLogLevel(RestAdapter.LogLevel.NONE);
        }

        //There's a bug in the data.gov API where a field named 'label' in Stations sometimes returns a string, sometimes a string[]:
        Gson gson = new GsonBuilder().registerTypeAdapter(RemedialStringType.class, new RemedialStringTypeAdapter()).create();
        builder.setConverter(new GsonConverter(gson));

        builder.setEndpoint(BASE_URL);

        rest = builder.build().create(FloodMonitoringRest.class);
    }

    public Observable<ThreeDayForecast> getThreeDayForecast(){
        return rest.get3DayForecast();
    }

    public Observable<Warnings> getAllWarnings(){
        return rest.getWarnings(null, null, null, null, null);
    }

    public Observable<Warnings> getCountyWarnings(String county){
        return rest.getWarnings(null, county, null, null, null);
    }

    public Observable<Warnings> getWanringsMinSeverity(int minSeverity){
        return rest.getWarnings(minSeverity, null, null, null, null);
    }

    public Observable<Warnings> getAreaWarnings(double latutide, double longitude, int distance){
        return rest.getWarnings(null, null, latutide, longitude, distance);
    }

    public Observable<Warnings> getAreaWarnings(int minSeverity, double latutide, double longitude, int distance){
        return rest.getWarnings(minSeverity, null, latutide, longitude, distance);
    }

    public Observable<Stations> getAllStations(){
        return rest.getStations(null, null, null, null);
    }

    public Observable<Stations> getCountyStations(String county){
        return rest.getStations(county, null, null, null);
    }

    public Observable<Stations> getAreaStations(double latutide, double longitude, int distance){
        return rest.getStations(null, latutide, longitude, distance);
    }

}
