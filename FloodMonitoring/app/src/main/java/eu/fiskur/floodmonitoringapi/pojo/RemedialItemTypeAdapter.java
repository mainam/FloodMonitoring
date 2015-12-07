package eu.fiskur.floodmonitoringapi.pojo;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import eu.fiskur.floodmonitoringapi.model.RemedialStringType;

public class RemedialItemTypeAdapter extends TypeAdapter<RemedialItem> {
    @Override
    public void write(JsonWriter out, RemedialItem value) throws IOException {
        //Read API only - no write needed
    }

    @Override
    public RemedialItem read(JsonReader in) throws IOException {
        RemedialItem remedialItem = new RemedialItem();




        return remedialItem;
    }
}
