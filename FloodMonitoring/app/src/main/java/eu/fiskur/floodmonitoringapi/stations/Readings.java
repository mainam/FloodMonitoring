package eu.fiskur.floodmonitoringapi.stations;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import eu.fiskur.floodmonitoringapi.model.Meta;
import eu.fiskur.floodmonitoringapi.stations.Reading;

public class Readings {
    @SerializedName("@context") String context;
    Meta meta;
    List<Reading> items;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Reading> getItems() {
        return items;
    }

    public void setItems(List<Reading> items) {
        this.items = items;
    }
}
