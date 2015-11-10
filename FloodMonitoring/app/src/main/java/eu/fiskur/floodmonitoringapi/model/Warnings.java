package eu.fiskur.floodmonitoringapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Warnings {
    @SerializedName("@context") String context;
    Meta meta;
    List<WarningItem> items;

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

    public List<WarningItem> getItems() {
        return items;
    }

    public void setItems(List<WarningItem> items) {
        this.items = items;
    }
}
