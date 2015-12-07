package eu.fiskur.floodmonitoringapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eu.fiskur.floodmonitoringapi.deserializers.MeasureDeserializer;
import eu.fiskur.floodmonitoringapi.deserializers.ReadingDeserializer;
import eu.fiskur.floodmonitoringapi.model.RemedialStringType;
import eu.fiskur.floodmonitoringapi.model.RemedialStringTypeAdapter;
import eu.fiskur.floodmonitoringapi.stations.Measure;
import eu.fiskur.floodmonitoringapi.stations.Reading;

public class GSONProvider {

    private static Gson gson = null;

    public static Gson getGSON(){
        if(gson == null){
            gson = new GsonBuilder()
            .registerTypeAdapter(RemedialStringType.class, new RemedialStringTypeAdapter())
                    .registerTypeAdapter(Measure[].class, new MeasureDeserializer())
                    .registerTypeAdapter(Reading.class, new ReadingDeserializer()).create();
        }
        return gson;
    }
}
