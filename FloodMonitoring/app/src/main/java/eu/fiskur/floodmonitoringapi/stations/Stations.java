package eu.fiskur.floodmonitoringapi.stations;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import eu.fiskur.floodmonitoringapi.model.Meta;

public class Stations {
    @SerializedName("@context") String context;
    Meta meta;
    List<StationOverview> items;

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

    public List<StationOverview> getItems() {
        return items;
    }

    public void setItems(List<StationOverview> items) {
        this.items = items;
    }
}
