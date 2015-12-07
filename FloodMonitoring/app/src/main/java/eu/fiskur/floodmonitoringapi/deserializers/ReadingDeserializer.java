package eu.fiskur.floodmonitoringapi.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import eu.fiskur.floodmonitoringapi.GSONProvider;
import eu.fiskur.floodmonitoringapi.stations.Reading;


public class ReadingDeserializer implements JsonDeserializer<Reading> {
    @Override
    public Reading deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonPrimitive()){
            //String
            Reading reading = new Reading();
            reading.setId(json.getAsString());
            return reading;
        }else{
            return GSONProvider.getGSON().fromJson(json, Reading.class);
        }
    }
}
