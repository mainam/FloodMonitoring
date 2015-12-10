package eu.fiskur.floodmonitoringapi.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import eu.fiskur.floodmonitoringapi.GSONProvider;
import eu.fiskur.floodmonitoringapi.alerts.FloodWarning;


public class SingleFloodWarningDeserializer implements JsonDeserializer<FloodWarning> {

    @Override
    public FloodWarning deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement content = json.getAsJsonObject().get("items");
        return GSONProvider.getGSONRemedialString().fromJson(content, FloodWarning.class);
    }
}
