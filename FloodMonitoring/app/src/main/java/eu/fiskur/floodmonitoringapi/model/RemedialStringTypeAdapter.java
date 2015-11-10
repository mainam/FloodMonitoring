package eu.fiskur.floodmonitoringapi.model;

import android.util.Log;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
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

        try {
            String label = in.nextString();
            remedialType.setLabel(label);
        }catch(IllegalStateException ioe){
            while (in.hasNext()) {
                try {
                    ArrayList<String> labels = new ArrayList<String>();
                    in.beginArray();
                    while (in.hasNext()) {
                        labels.add(in.nextString());
                    }
                    in.endArray();
                    remedialType.setLabelArray(labels.toArray(new String[labels.size()]));
                }catch(IllegalStateException ioee){
                    break;
                }
            }
        }

        return remedialType;
    }
}
