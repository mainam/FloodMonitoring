package eu.fiskur.floodmonitoringapi.stations;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StageScale {
    @SerializedName("@id") String id;
    Float datum;
    Reading highestRecent;
    Reading maxOnRecord;
    Reading minOnRecord;
    Float scaleMax;
    Float typicalRangeHigh;
    Float typicalRangeLow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getDatum() {
        return datum;
    }

    public void setDatum(Float datum) {
        this.datum = datum;
    }

    public Reading getHighestRecent() {
        return highestRecent;
    }

    public void setHighestRecent(Reading highestRecent) {
        this.highestRecent = highestRecent;
    }

    public Reading getMaxOnRecord() {
        return maxOnRecord;
    }

    public void setMaxOnRecord(Reading maxOnRecord) {
        this.maxOnRecord = maxOnRecord;
    }

    public Reading getMinOnRecord() {
        return minOnRecord;
    }

    public void setMinOnRecord(Reading minOnRecord) {
        this.minOnRecord = minOnRecord;
    }

    public Float getScaleMax() {
        return scaleMax;
    }

    public void setScaleMax(Float scaleMax) {
        this.scaleMax = scaleMax;
    }

    public Float getTypicalRangeHigh() {
        return typicalRangeHigh;
    }

    public void setTypicalRangeHigh(Float typicalRangeHigh) {
        this.typicalRangeHigh = typicalRangeHigh;
    }

    public Float getTypicalRangeLow() {
        return typicalRangeLow;
    }

    public void setTypicalRangeLow(Float typicalRangeLow) {
        this.typicalRangeLow = typicalRangeLow;
    }
}
