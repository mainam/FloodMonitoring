package eu.fiskur.floodmonitoringapi.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import eu.fiskur.floodmonitoringapi.GSONProvider;
import eu.fiskur.floodmonitoringapi.stations.StationDetail;

public class StationDetailDeserializer implements JsonDeserializer<StationDetail> {

    @Override
    public StationDetail deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement content = json.getAsJsonObject().get("items");
        return GSONProvider.getGSONRemedialString().fromJson(content, StationDetail.class);
    }
}
