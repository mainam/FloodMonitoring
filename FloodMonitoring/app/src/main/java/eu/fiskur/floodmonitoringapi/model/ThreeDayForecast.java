package eu.fiskur.floodmonitoringapi.model;

import com.google.gson.annotations.SerializedName;

public class ThreeDayForecast {
    @SerializedName("@context") String context;
    Meta meta;
    ThreeDayForecastObj items;

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

    public ThreeDayForecastObj get3DayForecast() {
        return items;
    }

    public void setItems(ThreeDayForecastObj items) {
        this.items = items;
    }
}
