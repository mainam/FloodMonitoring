package eu.fiskur.floodmonitoringapi.pojo;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import eu.fiskur.floodmonitoringapi.model.FloodWarning;
import timber.log.Timber;

public class RemedialFloodWarningAdapter extends TypeAdapter<RemedialFloodWarning> {
    @Override
    public void write(JsonWriter out, RemedialFloodWarning value) throws IOException {
        //Read only API
    }

    @Override
    public RemedialFloodWarning read(JsonReader in) throws IOException {
        RemedialFloodWarning floodWarning = new RemedialFloodWarning();

        Timber.plant(new Timber.DebugTree());

        Gson gson = new Gson();

        if (in.peek() == JsonToken.BEGIN_OBJECT) {
            Timber.d("in.peek() == JsonToken.BEGIN_OBJECT");
            
            FloodWarning warningObj = gson.fromJson(in, FloodWarning.class);
            List<FloodWarning> warningList = new ArrayList<>();
            warningList.add(warningObj);
            floodWarning.setFloodWarnings(warningList);
        } else if (in.peek() == JsonToken.BEGIN_ARRAY) {
            Timber.d("in.peek() == JsonToken.BEGIN_ARRAY");
            Type floodListType = new TypeToken<ArrayList<FloodWarning>>() {}.getType();
            List<FloodWarning> warningList = gson.fromJson(in, floodListType);
            floodWarning.setFloodWarnings(warningList);
        }

        return floodWarning;
    }
}
