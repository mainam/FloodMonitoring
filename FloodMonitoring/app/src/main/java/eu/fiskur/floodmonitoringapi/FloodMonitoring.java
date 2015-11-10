package eu.fiskur.floodmonitoringapi;

import com.github.filosganga.geogson.gson.GeometryAdapterFactory;
import com.github.filosganga.geogson.model.Geometry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import eu.fiskur.floodmonitoringapi.model.FloodArea;
import eu.fiskur.floodmonitoringapi.model.RemedialStringType;
import eu.fiskur.floodmonitoringapi.model.RemedialStringTypeAdapter;
import eu.fiskur.floodmonitoringapi.model.Stations;
import eu.fiskur.floodmonitoringapi.model.ThreeDayForecast;
import eu.fiskur.floodmonitoringapi.model.Warnings;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

//TODO - move to Retrofit 2: http://inthecheesefactory.com/blog/retrofit-2.0/en
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
        Retrofit.Builder builder = new Retrofit.Builder();

        //http://stackoverflow.com/a/33256827
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

        //There's a bug in the data.gov API where a field named 'label' in Stations sometimes returns a string, sometimes a string[]:
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(RemedialStringType.class, new RemedialStringTypeAdapter())
                .registerTypeAdapterFactory(new GeometryAdapterFactory())//https://github.com/filosganga/geogson
                .create();
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        builder.baseUrl(BASE_URL);

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

    public Observable<FloodArea> getFloodArea(String floodAreaID){
        return rest.getFloodArea(floodAreaID);
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

    public Observable<Geometry> getGeoJSON(String url){
        return null;//todo hateaos in retrofit?
    }

}
