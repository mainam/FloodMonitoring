package eu.fiskur.floodmonitoringapi.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import eu.fiskur.floodmonitoringapi.GSONProvider;
import eu.fiskur.floodmonitoringapi.alerts.ThreeDayForecast;

public class ThreeDayDeserializer implements JsonDeserializer<ThreeDayForecast>{

    @Override
    public ThreeDayForecast deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement content = json.getAsJsonObject().get("items");
        return GSONProvider.getGSON().fromJson(content, ThreeDayForecast.class);
    }
}
