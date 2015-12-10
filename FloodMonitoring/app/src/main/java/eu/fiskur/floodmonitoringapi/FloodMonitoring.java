package eu.fiskur.floodmonitoringapi;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.List;

import eu.fiskur.floodmonitoringapi.model.FloodWarning;
import eu.fiskur.floodmonitoringapi.model.Readings;
import eu.fiskur.floodmonitoringapi.alerts.ThreeDayForecast;
import eu.fiskur.floodmonitoringapi.stations.StationDetail;
import eu.fiskur.floodmonitoringapi.stations.StationOverview;
import eu.fiskur.floodmonitoringapi.stations.StationWrapper;
import eu.fiskur.floodmonitoringapi.stations.StationsWrapper;
import eu.fiskur.floodmonitoringapi.model.Flood;
import eu.fiskur.floodmonitoringapi.utilities.FloodUtils;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

public class FloodMonitoring {
    private static final String BASE_URL = "http://environment.data.gov.uk/flood-monitoring/";
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
        Retrofit.Builder builder = new Retrofit.Builder();

        if (logOutput) {
            OkHttpClient client = new OkHttpClient();

            floodApiLogger = FloodApiLogger.getInstance();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override public void log(String message) {
                    floodApiLogger.log(message);
                }
            });

            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.interceptors().add(interceptor);

            builder.client(client);
        }

        Gson gson = GSONProvider.getRestGson();

        builder.addConverterFactory(GsonConverterFactory.create(gson));
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        builder.baseUrl(BASE_URL);

        rest = builder.build().create(FloodMonitoringRest.class);
    }

    public Observable<ThreeDayForecast> getThreeDayForecast(){
        return rest.get3DayForecast();
    }

    public Observable<ResponseBody> getDayImageBytes(int day){
        return rest.getDayImageBytes(day);
    }

    //For when using Picasso:
    public String getDayImageUrl(int day){
        return BASE_URL + "id/3dayforecast/image/" + day;
    }


    //For web view:
    //eg. https://flood-warning-information.service.gov.uk/riverlevels?lng=-1.09751&lat=53.89367
    public String getRiverLevelsUrl(double latitude, double longitude){
        return "https://flood-warning-information.service.gov.uk/riverlevels?lng=" + longitude + "&lat=" + latitude;
    }

    public Observable<List<FloodWarning>> getAllWarnings(){
        return rest.getWarnings(null, null, null, null, null);
    }

    public Observable<List<FloodWarning>> getCountyWarnings(String county){
        return rest.getWarnings(null, county, null, null, null);
    }

    public Observable<List<FloodWarning>> getWanringsMinSeverity(int minSeverity){
        return rest.getWarnings(minSeverity, null, null, null, null);
    }

    public Observable<List<FloodWarning>> getAreaWarnings(double latutide, double longitude, int distance){
        return rest.getWarnings(null, null, latutide, longitude, distance);
    }

    public Observable<List<FloodWarning>> getAreaWarnings(int minSeverity, double latutide, double longitude, int distance){
        return rest.getWarnings(minSeverity, null, latutide, longitude, distance);
    }

    //Includes latitude and longitude
    public Observable<List<FloodWarning>> getFloodArea(String floodAreaID){
        return rest.getFloodArea(floodAreaID);
    }

    public Observable<Flood> getFloodAreaFromUrl(String url){
        return rest.getFloodAreaFromUrl(url);
    }

    /*
        Stations/River Levels
     */
    public Observable<List<StationOverview>> getAllStations(){
        return rest.getStations(null, null, null, null);
    }

    public Observable<List<StationOverview>> getCountyStations(String county){
        return rest.getStations(county, null, null, null);
    }

    public Observable<List<StationOverview>> getAreaStations(double latutide, double longitude, int distance){
        return rest.getStations(null, latutide, longitude, distance);
    }

    public Observable<StationWrapper> getStation(String url){
        return rest.getStationFromUrl(url);
    }

    public Observable<Readings> getReadings(String url, int count){
        url+="/readings";
        return rest.getReadings(url, 1, count);
    }

    public Observable<Readings> getReadingsToday(String url){
        url+="/readings";
        return rest.getReadingsToday(url, 1, 1);
    }

    //end date is today
    //start date is (today - days)
    public Observable<Readings> getReadingsDays(String url, int days){
        String endDate = FloodUtils.getTodaysDate();
        String startDate = FloodUtils.getPreviousDate(days);
        url+="/readings";
        return rest.getReadingsDays(url, 1, startDate, endDate);
    }

    public Observable<ResponseBody> getRawResponse(String url){
        return rest.getRawResponse(url);
    }
}
