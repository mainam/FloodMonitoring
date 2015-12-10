package eu.fiskur.floodmonitoringapi.deserializers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

public class RemedialStringTypeAdapter extends TypeAdapter<RemedialStringType> {
    @Override
    public void write(JsonWriter out, RemedialStringType value) throws IOException {
        //API is read only, no write operations needed
    }

    @Override
    public RemedialStringType read(JsonReader in) throws IOException {
        RemedialStringType remedialType = new RemedialStringType();

        if(in.peek() == JsonToken.BEGIN_ARRAY){
            ArrayList<String> labels = new ArrayList<String>();
            in.beginArray();
            while (in.hasNext()) {
                labels.add(in.nextString());
            }
            in.endArray();
            remedialType.setLabelArray(labels.toArray(new String[labels.size()]));
        }else if(in.peek() == JsonToken.STRING){
            String label = in.nextString();
            remedialType.setLabel(label);
        }

        return remedialType;
    }
}
