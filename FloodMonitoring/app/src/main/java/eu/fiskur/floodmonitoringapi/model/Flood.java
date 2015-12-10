package eu.fiskur.floodmonitoringapi.model;

import com.google.gson.annotations.SerializedName;

import eu.fiskur.floodmonitoringapi.alerts.FloodWarning;

public class Flood {
    @SerializedName("@context") String context;
    Meta meta;
    FloodWarning items;

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

    public FloodWarning getItems() {
        return items;
    }

    public void setItems(FloodWarning items) {
        this.items = items;
    }

    public FloodWarning getFloodWarning(){
        return items;
    }
}
