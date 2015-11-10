package eu.fiskur.floodmonitoringapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FloodArea {
    @SerializedName("@context") String context;
    Meta meta;
    List<FloodAreaExpanded> items;

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

    public List<FloodAreaExpanded> getItems() {
        return items;
    }

    public void setItems(List<FloodAreaExpanded> items) {
        this.items = items;
    }
}
