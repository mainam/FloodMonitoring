package eu.fiskur.floodmonitoringapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import eu.fiskur.floodmonitoringapi.deserializers.FloodWarningDeserializer;
import eu.fiskur.floodmonitoringapi.deserializers.MeasureDeserializer;
import eu.fiskur.floodmonitoringapi.deserializers.ReadingDeserializer;
import eu.fiskur.floodmonitoringapi.deserializers.StationsDeserializer;
import eu.fiskur.floodmonitoringapi.deserializers.ThreeDayDeserializer;
import eu.fiskur.floodmonitoringapi.model.FloodWarning;
import eu.fiskur.floodmonitoringapi.model.RemedialStringType;
import eu.fiskur.floodmonitoringapi.model.RemedialStringTypeAdapter;
import eu.fiskur.floodmonitoringapi.alerts.ThreeDayForecast;
import eu.fiskur.floodmonitoringapi.stations.Measure;
import eu.fiskur.floodmonitoringapi.stations.Reading;
import eu.fiskur.floodmonitoringapi.stations.StationDetail;

public class GSONProvider {

    private static Gson restGson = null;
    private static Gson vanillaGson = null;

    public static Gson getGSON(){
        if(vanillaGson == null){
            vanillaGson = new Gson();
        }
        return vanillaGson;
    }

    public static Gson getRestGson(){
        if(restGson == null){
            restGson = new GsonBuilder()
                    .registerTypeAdapter(new TypeToken<List<StationDetail>>() {}.getType(), new StationsDeserializer())
                    .registerTypeAdapter(new TypeToken<List<FloodWarning>>() {}.getType(), new FloodWarningDeserializer())
                    .registerTypeAdapter(ThreeDayForecast.class, new ThreeDayDeserializer())
                    .registerTypeAdapter(RemedialStringType.class, new RemedialStringTypeAdapter())
                    .registerTypeAdapter(Measure[].class, new MeasureDeserializer())
                    .registerTypeAdapter(Reading.class, new ReadingDeserializer()).create();
        }
        return restGson;
    }
}
