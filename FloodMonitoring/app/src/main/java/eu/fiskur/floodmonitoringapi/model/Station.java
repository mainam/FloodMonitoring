package eu.fiskur.floodmonitoringapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Station {
    @SerializedName("@id") String id;
    String RLOIid;
    String catchmentName;
    String dateOpened;
    Integer eee;
    String label;
    List<Measure> measures;
    Integer nnn;
    String notation;
    String riverName;
    String stageScale;
    String stationReference;
    String town;
    String wiskiID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRLOIid() {
        return RLOIid;
    }

    public void setRLOIid(String RLOIid) {
        this.RLOIid = RLOIid;
    }

    public String getCatchmentName() {
        return catchmentName;
    }

    public void setCatchmentName(String catchmentName) {
        this.catchmentName = catchmentName;
    }

    public String getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(String dateOpened) {
        this.dateOpened = dateOpened;
    }

    public Integer getEee() {
        return eee;
    }

    public void setEee(Integer eee) {
        this.eee = eee;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    public Integer getNnn() {
        return nnn;
    }

    public void setNnn(Integer nnn) {
        this.nnn = nnn;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public String getRiverName() {
        return riverName;
    }

    public void setRiverName(String riverName) {
        this.riverName = riverName;
    }

    public String getStageScale() {
        return stageScale;
    }

    public void setStageScale(String stageScale) {
        this.stageScale = stageScale;
    }

    public String getStationReference() {
        return stationReference;
    }

    public void setStationReference(String stationReference) {
        this.stationReference = stationReference;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getWiskiID() {
        return wiskiID;
    }

    public void setWiskiID(String wiskiID) {
        this.wiskiID = wiskiID;
    }
}
