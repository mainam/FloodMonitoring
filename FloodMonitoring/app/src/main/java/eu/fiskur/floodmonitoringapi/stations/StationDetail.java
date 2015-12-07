package eu.fiskur.floodmonitoringapi.stations;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import eu.fiskur.floodmonitoringapi.model.RemedialStringType;

public class StationDetail {
    @SerializedName("@id") String id;
    String RLOIid;
    String catchmentName;
    String dateOpened;
    String eaAreaName;
    String eaRegionName;
    Float easting;
    RemedialStringType label;
    Float lat;
    @SerializedName("long")Float lon;
    Measure[] measures;
    Float northing;
    String notation;
    String riverName;
    StageScale stageScale;
    String stationReference;
    String town;
    //RemedialStringType type;
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

    public String getEaAreaName() {
        return eaAreaName;
    }

    public void setEaAreaName(String eaAreaName) {
        this.eaAreaName = eaAreaName;
    }

    public String getEaRegionName() {
        return eaRegionName;
    }

    public void setEaRegionName(String eaRegionName) {
        this.eaRegionName = eaRegionName;
    }

    public Float getEasting() {
        return easting;
    }

    public void setEasting(Float easting) {
        this.easting = easting;
    }

    public RemedialStringType getLabel() {
        return label;
    }

    public void setLabel(RemedialStringType label) {
        this.label = label;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public Measure[] getMeasures() {
        return measures;
    }

    public void setMeasures(Measure[] measures) {
        this.measures = measures;
    }

    public Float getNorthing() {
        return northing;
    }

    public void setNorthing(Float northing) {
        this.northing = northing;
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

    public StageScale getStageScale() {
        return stageScale;
    }

    public void setStageScale(StageScale stageScale) {
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

    /*
    public RemedialStringType getType() {
        return type;
    }

    public void setType(RemedialStringType type) {
        this.type = type;
    }
*/
    public String getWiskiID() {
        return wiskiID;
    }

    public void setWiskiID(String wiskiID) {
        this.wiskiID = wiskiID;
    }
}
