package eu.fiskur.floodmonitoringapi.deserializers;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import eu.fiskur.floodmonitoringapi.GSONProvider;
import eu.fiskur.floodmonitoringapi.stations.Measure;

public class MeasureDeserializer implements JsonDeserializer<Measure[]>{

    @Override
    public Measure[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (json instanceof JsonArray){
            return GSONProvider.getGSON().fromJson(json, Measure[].class);
        }else{
            Measure measure = GSONProvider.getGSON().fromJson(json, Measure.class);
            return new Measure[] { measure };
        }

    }
}
