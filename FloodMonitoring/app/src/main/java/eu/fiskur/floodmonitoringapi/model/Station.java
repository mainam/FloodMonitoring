package eu.fiskur.floodmonitoringapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Station {
    @SerializedName("@id") String id;
    String RLOIid;
    String catchmentName;
    String dateOpened;
    Float datumOffset;
    Integer eee;
    RemedialStringType label;//This field can be either a string or string[]
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

    public Float getDatumOffset(){
        return datumOffset;
    }

    public void setDatumOffset(Float datumOffset){
        this.datumOffset = datumOffset;
    }

    public Integer getEee() {
        return eee;
    }

    public void setEee(Integer eee) {
        this.eee = eee;
    }

    public RemedialStringType getLabel() {
        return label;
    }

    public void setLabel(RemedialStringType label) {
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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if(id != null){
            sb.append("@id: " + id + "\n");
        }
        if(RLOIid != null){
            sb.append("RLOIid: " + RLOIid + "\n");
        }
        if(catchmentName != null){
            sb.append("catchmentName: " + catchmentName + "\n");
        }
        if(dateOpened != null){
            sb.append("dateOpened: " + dateOpened + "\n");
        }
        if(eee != null){
            sb.append("eee: " + eee + "\n");
        }
        if(label != null){
            sb.append("label: " + label.toString() + "\n");
        }
        if(measures != null){
            //todo
            for(Measure measure : measures){
                sb.append(measure.toString() + "\n");
            }
        }
        if(nnn != null){
            sb.append("nnn: " + nnn + "\n");
        }
        if(notation != null){
            sb.append("notation: " + notation + "\n");
        }
        if(riverName != null){
            sb.append("riverName: " + riverName + "\n");
        }
        if(stageScale != null){
            sb.append("stageScale: " + stageScale + "\n");
        }
        if(stationReference != null){
            sb.append("stationReference: " + stationReference + "\n");
        }
        if(town != null){
            sb.append("town: " + town + "\n");
        }
        if(wiskiID != null){
            sb.append("wiskiID: " + wiskiID + "\n");
        }
        return sb.toString();
    }
}
