package eu.fiskur.floodmonitoringapi.model;

import com.google.gson.annotations.SerializedName;

public class WarningItem {
    @SerializedName("@id") String id;
    String description;
    String eaAreaName;
    String eaRegionName;
    FloodAreaOverview floodArea;
    String floodAreaID;
    Boolean isTidal;
    String lcounty;
    String message;
    String severity;
    Integer severityLevel;
    String timeMessageChanged;
    String timeRaised;
    String timeSeverityChanged;

    public String getLcounty() {
        return lcounty;
    }

    public void setLcounty(String lcounty) {
        this.lcounty = lcounty;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSeverityLevel(Integer severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public FloodAreaOverview getFloodArea() {
        return floodArea;
    }

    public void setFloodArea(FloodAreaOverview floodArea) {
        this.floodArea = floodArea;
    }

    public String getFloodAreaID() {
        return floodAreaID;
    }

    public void setFloodAreaID(String floodAreaID) {
        this.floodAreaID = floodAreaID;
    }

    public Boolean getIsTidal() {
        return isTidal;
    }

    public void setIsTidal(Boolean isTidal) {
        this.isTidal = isTidal;
    }

    public String getLabelCounty(){
        return lcounty;
    }

    public void setLabelCounty(String lcounty){
        this.lcounty = lcounty;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public int getSeverityLevel() {
        return severityLevel;
    }

    public String getTimeMessageChanged() {
        return timeMessageChanged;
    }

    public void setTimeMessageChanged(String timeMessageChanged) {
        this.timeMessageChanged = timeMessageChanged;
    }

    public String getTimeRaised() {
        return timeRaised;
    }

    public void setTimeRaised(String timeRaised) {
        this.timeRaised = timeRaised;
    }

    public String getTimeSeverityChanged() {
        return timeSeverityChanged;
    }

    public void setTimeSeverityChanged(String timeSeverityChanged) {
        this.timeSeverityChanged = timeSeverityChanged;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if(id != null){
            sb.append("@id: " + id + "\n");
        }
        if(description != null){
            sb.append("Description: " + description + "\n");
        }
        if(eaAreaName != null){
            sb.append("eaAreaName: " + eaAreaName + "\n");
        }
        if(eaRegionName != null){
            sb.append("eaRegionName: " + eaRegionName + "\n");
        }
        if(floodArea != null){
            if(floodArea != null){
                sb.append("floodArea: \n" + floodArea.toString());
            }
        }
        if(floodAreaID != null){
            sb.append("floodAreaID: " + floodAreaID + "\n");
        }
        if(isTidal != null){
            sb.append("isTidal: " + isTidal + "\n");
        }
        if(lcounty != null){
            sb.append("lcounty: " + lcounty + "\n");
        }
        if(severity != null){
            sb.append("severity: " + severity + "\n");
        }
        if(severityLevel != null){
            sb.append("severityLevel: " + severityLevel + "\n");
        }
        if(timeMessageChanged != null){
            sb.append("timeMessageChanged: " + timeMessageChanged + "\n");
        }
        if(timeRaised != null){
            sb.append("timeRaised: " + timeRaised + "\n");
        }
        if(timeSeverityChanged != null){
            sb.append("timeSeverityChanged: " + timeSeverityChanged + "\n");
        }



        return sb.toString();
    }
}
