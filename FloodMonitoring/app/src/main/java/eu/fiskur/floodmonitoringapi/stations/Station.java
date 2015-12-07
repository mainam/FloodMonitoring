package eu.fiskur.floodmonitoringapi.stations;

import com.google.gson.annotations.SerializedName;

import eu.fiskur.floodmonitoringapi.model.Meta;

public class Station {
    @SerializedName("@context") String context;
    Meta meta;
    StationDetail items;

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

    public StationDetail getItems() {
        return items;
    }

    public void setItems(StationDetail items) {
        this.items = items;
    }

    public StationDetail getStation(){
        return items;
    }
}
